package com.my.log.db;

import java.text.ParseException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import com.my.log.util.AccessLogParser;
import com.my.log.util.Log4j;


public class logMapper extends Mapper<Object, Text, Text, Text> {

	Logger logger = new Log4j(this.getClass().getName()).getLogger();
	public void map(Object key, Text value, Context context) {

		AccessLogParser log;
		//logger.debug("start ummsrMapper");
		try {
			log = new AccessLogParser(value.toString());
			//logger.warn(value.toString());
			logger.warn(log.getMsgid());
			logger.warn(log.getMsgbody());
			if(log.getMsgid()!=null){
				context.write(new Text(log.getMsgid()), new Text(log.getMsgbody()));
			}else{
				//logger.debug("BM Mapper"+value.toString());
			}
		} catch (ParseException e) {
			logger.warn("BM Mapper"+e.toString(),e);
		} catch (Exception e) {
			logger.warn("BM Mapper"+e.toString(),e);
		}

	}

}
