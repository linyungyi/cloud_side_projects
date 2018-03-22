package com.my.log.db;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import com.my.log.util.Log4j;

public class bmsmsCombiner extends Reducer<Text, Text, Text, Text> {
	Logger logger = new Log4j(this.getClass().getName()).getLogger();
 	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
 		logger.info("start ummsrCombiner");
		Text result = new Text();
		for (Text val : values) {
			result.append(val.getBytes(), 0, val.getBytes().length);
		}
		context.write(key, result);

	}
}
