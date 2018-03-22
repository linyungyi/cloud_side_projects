package com.my.log.db;

import java.io.IOException;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;


import com.my.log.util.BatchToDB;
import com.my.log.util.Log4j;


public class SqlReducer extends Reducer<Text, Text, Text, Text> {
	Logger logger = new Log4j(this.getClass().getName()).getLogger();


	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		BatchToDB btdb=new BatchToDB();

		try {

	        
			logger.warn("start SqlReducer:"+key);
			btdb.write(key, values);
			logger.warn("finish SqlReducer:"+key);
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);

		} finally {

		}

	}

}
