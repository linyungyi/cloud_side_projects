package com.my.log.db;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import com.my.log.util.AccessLogParser;
import com.my.log.util.Log4j;
import com.my.log.util.LogAnalyzeExtrator;

public class logReducer extends Reducer<Text, Text, Text, Text> {
	Logger logger = new Log4j(this.getClass().getName()).getLogger();
	private static final String utf8 = "UTF-8";
	/*
	 * private static final byte[] tagstart; static { try { tagstart =
	 * "<reduce>".getBytes(utf8); } catch (UnsupportedEncodingException uee) {
	 * throw new IllegalArgumentException("can't find " + utf8 + " encoding"); }
	 * }
	 */
	private static final byte[] tagend;
	static {
		try {
			tagend = "<br>".getBytes(utf8);
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalArgumentException("can't find " + utf8
					+ " encoding");
		}
	}

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		// logger.info("start ummsrReducer");
		Text result = new Text();
		try {
			AccessLogParser log;
			String msgType="";
			for (Text val : values) {
				// result.append(tagstart,0,tagstart.length);
				log = new AccessLogParser(val.toString());
				if(log.getMsgtype()!=null){
					msgType=log.getMsgtype();
				}
				result.append(val.getBytes(), 0, val.getLength());
				result.append(tagend, 0, tagend.length);
			}
			LogAnalyzeExtrator lae = new LogAnalyzeExtrator(result.toString());
			String sql=null;
			logger.warn("key:"+key+":"+msgType);
			if (msgType.indexOf("brightmail")!=-1) {
				logger.warn(key+":"+result.toString());
				if (lae.getFromAddress() != null || lae.getToAddress() != null) {
					sql = "insert into bm_sms_log(msg_id,from_address,to_address,timestamp,msg_body) values('"
							+ key
							+ "','"
							+ lae.getFromAddress()
							+ "','"
							+ lae.getToAddress()
							+ "',STR_TO_DATE('"
							+ lae.getDate()
							+ "','%Y %b %d %T'),'"
							+ result.toString() + "')";
					context.write(key, new Text(sql));
					logger.warn(key+":"+sql);
				}
			}else if (msgType.equalsIgnoreCase("smtp-mx-msr")) {
				logger.warn(key+":"+result.toString());
				if (lae.getFromAddress() != null || lae.getToAddress() != null) {
					sql = "insert into ummsr_log(msg_id,from_address,to_address,timestamp,msg_body) values('"
							+ key
							+ "','"
							+ lae.getFromAddress()
							+ "','"
							+ lae.getToAddress()
							+ "',STR_TO_DATE('"
							+ lae.getDate()
							+ "','%Y %b %d %T'),'"
							+ result.toString() + "')";
					context.write(key, new Text(sql));
					logger.warn(key+":"+sql);
				}
			}else if (msgType.equalsIgnoreCase("Webmail")) {
				logger.warn(key+":"+result.toString());
				if (lae.getUsernameAddress() != null ) {
					sql = "insert into service_ap_log(msg_id,from_address,to_address,timestamp,msg_body) values('"
							+ key
							+ "','"
							+ lae.getUsernameAddress()
							+ "','',STR_TO_DATE('"
							+ lae.getDate()
							+ "','%Y %b %d %T'),'"
							+ result.toString() + "')";
					context.write(key, new Text(sql));
					logger.warn(key+":"+sql);
				}
			}else if (msgType.equalsIgnoreCase("sg4001-smtp-msr")) {
				logger.warn(key+":"+result.toString());
				if (lae.getFromAddress() != null || lae.getToAddress() != null) {
					sql = "insert into msr_webmail_log(msg_id,from_address,to_address,timestamp,msg_body) values('"
							+ key
							+ "','"
							+ lae.getFromAddress()
							+ "','"
							+ lae.getToAddress()
							+ "',STR_TO_DATE('"
							+ lae.getDate()
							+ "','%Y %b %d %T'),'"
							+ result.toString() + "')";
					context.write(key, new Text(sql));
					logger.warn(key+":"+sql);
				}
			}else if (msgType.equalsIgnoreCase("smtp-mx")||msgType.equalsIgnoreCase("pim-mx")) {
				logger.warn(key+":"+result.toString());
				if (lae.getFromAddress() != null || lae.getToAddress() != null) {
					sql = "insert into smtp_mail_log(msg_id,from_address,to_address,timestamp,msg_body) values('"
							+ key
							+ "','"
							+ lae.getFromAddress()
							+ "','"
							+ lae.getToAddress()
							+ "',STR_TO_DATE('"
							+ lae.getDate()
							+ "','%Y %b %d %T'),'"
							+ result.toString() + "')";
					context.write(key, new Text(sql));
					logger.warn(key+":"+sql);
				}
			}else if (msgType.equalsIgnoreCase("INFO")) {
				logger.warn("result:"+key+":"+result.toString());
				if (lae.getMailAddress() != null ) {
					sql = "insert into dispatcher_log(msg_id,from_address,to_address,timestamp,msg_body) values('"
							+ key
							+ "','','"
							+ lae.getMailAddress()
							+ "',STR_TO_DATE('"
							+ lae.getADDate()
							+ "','%Y-%m-%d %T'),'"
							+ result.toString() + "')";
					context.write(key, new Text(sql));
					logger.warn("sql:"+key+":"+sql);
				}
			}
			if(sql!=null)
				context.write(key, new Text(sql));
		} catch (Exception e) {
			logger.warn(result.toString(), e);

		} finally {
		}

	}

}
