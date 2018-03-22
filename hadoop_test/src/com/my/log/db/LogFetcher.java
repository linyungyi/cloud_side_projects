package com.my.log.db;

import java.util.Calendar;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;




public class LogFetcher {

	public static void main(String[] args) throws Exception {
		Calendar rightNow = Calendar.getInstance();
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();

		System.out.println("start LogFetcher");

		Job job = new Job(conf, "LogFetcher");
	    job.setJarByClass(LogFetcher.class);
	    
	    job.setMapperClass(logMapper.class);
	    job.setReducerClass(logReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(Text.class);
	    String folder="/"+rightNow.get(Calendar.MONTH)+ ""+rightNow.get(Calendar.DAY_OF_MONTH)+ ""+rightNow.get(Calendar.HOUR_OF_DAY)+""+ rightNow.get(Calendar.MINUTE);
	    //job.setOutputFormatClass(AlphabetOutputFormat.class);//設置輸出格式
	    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));   
        FileOutputFormat.setOutputPath(job, new Path("BM"+folder));   
	    job.waitForCompletion(true);
        
        Job SQLjob = new Job(conf, "LogFetcher");
        SQLjob.setJarByClass(LogFetcher.class);
        
        SQLjob.setMapperClass(SqlMapper.class);
        SQLjob.setReducerClass(SqlReducer.class);
        SQLjob.setOutputKeyClass(Text.class);
        SQLjob.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(SQLjob, new Path("BM"+folder));   
        FileOutputFormat.setOutputPath(SQLjob, new Path("BM"+folder+"/result"));   
       
        System.exit(SQLjob.waitForCompletion(true) ? 0 : 1);   
	}

}
