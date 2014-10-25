package org.iii.news.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class step1Reducer extends Reducer<Text, Text, Text, IntWritable> {
	
	Text outputKey=new Text();
	IntWritable outputValue=new IntWritable();
	ArrayList<String> wordlist =  new ArrayList<String>();
	ArrayList<String> words =  new ArrayList<String>();
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		Configuration conf = new Configuration();
		conf.set("fs.default.name","hdfs://s-hadoopA:9000");
		FileSystem fs = FileSystem.get(conf);
		InputStream is = fs.open(new Path("/keywords/dic.txt"));
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(is));
        String str="";
        while ((str = reader1.readLine())!=null){
            String token []= str.split(" ");
            wordlist.add(token[0]);
        }
        reader1.close(); 		
	}

	@Override
	protected void reduce(Text entry, Iterable<Text> value, Context context)
			throws IOException, InterruptedException {
		Iterator<Text> content=value.iterator();
        String sentence = "";
        int result=0;
        while(content.hasNext()){
        	sentence = sentence+content.next().toString();         	 	
		}

    	for (String keywords:wordlist) {
    		for (int screen = 0; screen < sentence.length(); screen++) {
                result = sentence.indexOf(keywords, result + keywords.length() * screen);
                if (result == -1) {
                    break;
                }
                words.add(keywords);
            } 
		}
    	
    	for (String word:words) {
    		outputKey.set(entry+","+word);
            outputValue.set(1);
            context.write(outputKey,outputValue);
    	}    	       
        words.clear();
	}
	
	
	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
	}
	
}
