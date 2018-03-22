package com.my.log.file.ummsr;

import java.util.Calendar;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;

import com.my.log.util.MultipleOutputFormat;

public class ummsrLogFetcher {

	public static class AlphabetOutputFormat extends MultipleOutputFormat<Text, Text> {   
        @Override  
        protected String generateFileNameForKeyValue(Text key, Text value, Configuration conf) {   
            /*char c = key.toString().toLowerCase().charAt(0);   
            if (c >= 'a' && c <= 'z') {   
                return c + ".txt";   
            }   
            return "other.txt";*/
        	String keyvalue=key.toString();
        	int length=keyvalue.length();
       	return keyvalue.substring(length-4) + ".txt";
        }   
    }
	public static void main(String[] args) throws Exception {

		Calendar rightNow = Calendar.getInstance();
	    String folder="/"+rightNow.get(Calendar.MONTH)+ ""+rightNow.get(Calendar.DAY_OF_MONTH)+ ""+rightNow.get(Calendar.HOUR_OF_DAY)+""+ rightNow.get(Calendar.MINUTE);
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		System.out.println("start ummsrLogFetcher:"+folder);
	    Job job = new Job(conf, "LogFetcher");
	    job.setJarByClass(ummsrLogFetcher.class);
	    job.setMapperClass(ummsrMapper.class);

	    //job.setCombinerClass(ummsrCombiner.class);
	    //job.setCombinerClass(ummsrReducer.class);
	    job.setReducerClass(ummsrReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);

	    job.setOutputFormatClass(AlphabetOutputFormat.class);//設置輸出格式
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));   
        FileOutputFormat.setOutputPath(job, new Path("UMMSR"+folder));   
        System.exit(job.waitForCompletion(true) ? 0 : 1);   
	}

}
