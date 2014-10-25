package org.iii.news.step2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class step2Reducer extends Reducer<Text, IntWritable, Text, NullWritable> {
	
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
	}

	@Override
	protected void reduce(Text entry, Iterable<IntWritable> value, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable val : value){
			sum += val.get();
		}
		context.write(entry, NullWritable.get());
	}
	
	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
	}
	
}
