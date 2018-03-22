package com.my.log.db;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import com.my.log.util.Log4j;


public class SqlMapper extends Mapper<Object, Text, Text, Text> {

	Logger logger = new Log4j(this.getClass().getName()).getLogger();
	public void map(Object key, Text value, Context context) {

		try {
			String text=value.toString();
			int index=text.indexOf("insert");
			if(index!=-1){
			String token=text.substring(0,index).trim();
			String tokenvalue=text.substring(index).trim();
			//logger.warn(token+":["+token.substring(token.length()-4,token.length())+"]");
			context.write(new Text(token.substring(token.length()-4,token.length())),new Text( tokenvalue));
			}else{
				
			}
		} catch (Exception e) {
			logger.warn("BM Mapper"+e.toString(),e);
		}

	}

}
