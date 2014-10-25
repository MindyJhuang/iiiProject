package org.iii.news.step3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Step3Driver {
	
	
	public int execute(Path inputPath, Path outputPath){

		boolean success=false;
		try {
			FileSystem fs=FileSystem.get(new Configuration());
			fs.delete(outputPath, true);
			
			Job job = Job.getInstance(new Configuration());
			job.setInputFormatClass(TextInputFormat.class);
			FileInputFormat.setInputPaths(job, inputPath);
			job.setMapperClass(Step3Mapper.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);

			job.setReducerClass(Step3Reducer.class);
			job.setOutputFormatClass(TextOutputFormat.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(NullWritable.class);
			FileOutputFormat.setOutputPath(job, outputPath);
			job.setNumReduceTasks(1);
			job.setJarByClass(job.getMapperClass());
			success = job.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
			success=false;
		}
		if(success){
			System.out.println("Job finished succesfully");
			return 0;
		}else{
			System.err.println("Job failed");
			return -1;
		}
		
	}
	public static void main(String args[]){
		Path inputPath=null;
		Path outputPath=null;
		
		try{
			String input=args[0];
			String output=args[1];
			inputPath=new Path(input);
			outputPath=new Path(output);
		}catch(Exception e){
			System.out.println("Usage: "+Step3Driver.class.getSimpleName()+" [input] [output]");
		}
		
		int exitCode=new Step3Driver().execute(inputPath,outputPath);
		System.exit(exitCode);
	}
}
