package org.iii.news.step3;



import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;



public class Step3Reducer extends Reducer<Text, IntWritable, Text, NullWritable> {

	Text outputKey = new Text();
	
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
			String message = entry.toString() + "," + sum;
			outputKey.set(message);
			context.write(outputKey, NullWritable.get());		
	}
	
	
	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
	}
	
}
