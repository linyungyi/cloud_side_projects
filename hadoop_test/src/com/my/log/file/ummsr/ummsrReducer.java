package com.my.log.file.ummsr;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import com.my.log.util.Log4j;

public class ummsrReducer extends Reducer<Text, Text, Text, Text> {
	Logger logger = new Log4j(this.getClass().getName()).getLogger();
   private static final String utf8 = "UTF-8";   
    /*private static final byte[] tagstart;   
    static {   
        try {   
        	tagstart="<reduce>".getBytes(utf8);   
        } catch (UnsupportedEncodingException uee) {   
            throw new IllegalArgumentException("can't find " + utf8 + " encoding");   
        }   
    }   */
    private static final byte[] tagend;   
    static {   
        try {   
        	tagend ="<br>".getBytes(utf8);   
        } catch (UnsupportedEncodingException uee) {   
            throw new IllegalArgumentException("can't find " + utf8 + " encoding");   
        }   
    }   
 	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		Text result = new Text();
		int index=0;
		for (Text val : values) {
			index++;
			//result.append(tagstart,0,tagstart.length);
			result.append(val.getBytes(), 0, val.getLength());
			result.append(tagend,0,tagend.length);
		}
		logger.warn(key+":-->"+index);
		context.write(key, result);

	}
}
