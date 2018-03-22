/**
 * 
 */
package com.cht.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alberltin
 *
 */
public class FlashcastTimeInMillisHelper {
	private long nTime;
	public FlashcastTimeInMillisHelper(){
		nTime=System.currentTimeMillis();
	}
	public FlashcastTimeInMillisHelper(long t){
		nTime=t;
	}
	
	/**
	 * @return the nTime
	 */
	public long getNTime() {
		return nTime;
	}
	/**
	 * @param time the nTime to set
	 */
	public void setNTime(long time) {
		nTime = time;
	}
	public String getDateTime(){
		String res;
		Date utilDate = new Date(nTime); 
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
        res = df.format(utilDate);         
		return res;
	}
	public String getDateTime(long time){
		String res;
		nTime=time;
		Date utilDate = new Date(nTime); 
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
        res = df.format(utilDate);         
		return res;
	}	
}
