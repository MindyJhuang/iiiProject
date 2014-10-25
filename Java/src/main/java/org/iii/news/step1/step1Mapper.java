package org.iii.news.step1;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class step1Mapper extends Mapper<LongWritable, Text, Text, Text> {

	Text outputKey=new Text();
	Text outputValue=new Text();
	
	@Override
	protected void setup(Context context){
	}
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		try{
			String line=value.toString().trim();
			String tokens[]=line.split("\t");
			
			String id = tokens[0];
			//String date = tokens[1];
			String content =  tokens[3];
			//String catalog = tokens[2];

			outputKey.set(id);		
			outputValue.set(content);
			
			context.write(outputKey, outputValue);
		}catch(Exception e){
			//ignore
		}
	}
	
	@Override
	protected void cleanup(Context context){
	}	
	
}
