/**
 * 
 */
package com.cht.dto;

/**
 * @author alberltin
 *
 */
public class UserErrorDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String devid;
	private int error;
	private String feedappid;
	private long time;
	private String userid;
	public UserErrorDTO() {
		super();
		// 
	}
	/**
	 * @return the devid
	 */
	public String getDevid() {
		return devid;
	}
	/**
	 * @param devid the devid to set
	 */
	public void setDevid(String devid) {
		this.devid = devid;
	}
	/**
	 * @return the error
	 */
	public int getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(int error) {
		this.error = error;
	}
	/**
	 * @return the feedappid
	 */
	public String getFeedappid() {
		return feedappid;
	}
	/**
	 * @param feedappid the feedappid to set
	 */
	public void setFeedappid(String feedappid) {
		this.feedappid = feedappid;
	}
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
    public String toString() {
        
        String result = 
        		//"UserErrorDTO begin*********** \n"+
        		"HostAddress.[" + super.HostAddress + "] \n" +
        		"Devid.[" + this.devid + "] \n" +
                "Error.[" + this.error + "] \n" +
                "Feedappid.[" + this.feedappid + "] \n" +
                "Time.[" + this.time + "] \n" +
                "UserId.[" + this.userid + "] \n" ;
                //"UserErrorDTO end************* \n" ;
        
        return result;
    }

}
