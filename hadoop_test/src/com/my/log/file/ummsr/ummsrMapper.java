package com.my.log.file.ummsr;

import java.text.ParseException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import com.my.log.util.AccessLogParser;
import com.my.log.util.Log4j;

public class ummsrMapper extends Mapper<Object, Text, Text, Text> {

	Logger logger = new Log4j(this.getClass().getName()).getLogger();

	public void map(Object key, Text value, Context context) {

		AccessLogParser log;
		//logger.warn("start ummsrMapper");
		try {
			log = new AccessLogParser(value.toString());
			if (log.getMsgid() != null) {
				logger.warn("start ummsrMapper:"+log.getMsgid());
				context.write(new Text(log.getMsgid()), new Text(log
						.getMsgbody()));
			}
		} catch (ParseException e) {
			logger.warn("ummsrMapper:" + e.toString(), e);
		} catch (Exception e) {
			logger.warn("ummsrMapper:" + e.toString(), e);
		}

	}

}
