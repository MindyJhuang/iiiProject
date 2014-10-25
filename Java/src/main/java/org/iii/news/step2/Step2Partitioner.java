package org.iii.news.step2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

@SuppressWarnings("rawtypes")
public class Step2Partitioner extends HashPartitioner<WritableComparable,Writable> {

	Text partitionKey=new Text();
	
	public int getPartition(WritableComparable key, Writable value, int numReduceTasks){
		String originalKey=key.toString();
		String partitionKeyStr=originalKey.substring(0,originalKey.indexOf(","));
		partitionKey.set(partitionKeyStr);
		return super.getPartition(partitionKey, value, numReduceTasks);
	}
}
