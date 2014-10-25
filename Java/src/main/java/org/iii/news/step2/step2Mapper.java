package org.iii.news.step2;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class step2Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	Text outputKey=new Text();
	IntWritable outputValue=new IntWritable();
	
	@Override
	protected void setup(Context context){
	}
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		try{
			String line = value.toString();			
		
			outputKey.set(line.split("\t")[0]);		
			outputValue.set(1);
			
			context.write(outputKey, outputValue);
		}catch(Exception e){
			//ignore
		}
	}
	
	@Override
	protected void cleanup(Context context){
	}	
	
}
