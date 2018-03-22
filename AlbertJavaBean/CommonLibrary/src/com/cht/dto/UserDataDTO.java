/**
 * 
 */
package com.cht.dto;

//import java.io.Serializable;
/*import java.util.Collection;

import com.macromedia.flashcast.item.FeedItemCollection;*/

/**
 * @author alberltin
 *
 */
public class UserDataDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FeedItemCollectionDTO ficDto; 
	//private String HostAddress;
	private String devid;
	private boolean error;
	private String feedid;
	private boolean outbound;
	private String requestid;
	private String sessid;
	private int size;
	private long timeStart;
	private long tsreq;
	private String useraddr;
	private String userid;
	public UserDataDTO(){
		super();
	}
	/*public Collection getColl() {
		return feedItems;
	}
	public void setColl(FeedItemCollection coll) {
		//this.feedItems = coll;
	}*/
	public String getDevid() {
		return devid;
	}
	public void setDevid(String devid) {
		this.devid = devid;
	}
	public boolean getError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getFeedid() {
		return feedid;
	}
	public void setFeedid(String feedid) {
		this.feedid = feedid;
	}
	public boolean getOutbound() {
		return outbound;
	}
	public void setOutbound(boolean outbound) {
		this.outbound = outbound;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	public String getSessid() {
		return sessid;
	}
	public void setSessid(String sessid) {
		this.sessid = sessid;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}
	public long getTsreq() {
		return tsreq;
	}
	public void setTsreq(long tsreq) {
		this.tsreq = tsreq;
	}
	public String getUseraddr() {
		return useraddr;
	}
	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
    public String toString() {
        //"Coll.[" + coll + "] \n" +        
        String result = 
        		//"UserDataDTO begin*********** \n"+
        		"HostAddress.[" + super.HostAddress + "] \n" +
        		"Devid.[" + devid + "] \n" +
                "Error.[" + error + "] \n" +
                "Feedid.[" + feedid + "] \n" +
                "Outbound.[" + outbound + "] \n" +
                "Requestid.[" + requestid + "] \n" +
                "SessionId.[" + sessid + "] \n" +
                "Size.[" + size + "] \n" +
                "TimeStart.[" + timeStart + "] \n" +
                "Tsreq.[" + tsreq + "] \n" +
                "Useraddr.[" + useraddr + "] \n" +
                "UserId.[" + userid + "]. \n" ;
        if(ficDto!=null){
    		result+="FeedItemCollection.{ " + ficDto.toString() + " }. \n" ;
            //"UserDataDTO end************* \n" ;
        }
        
        return result;
    }
	public FeedItemCollectionDTO getFicDto() {
		return ficDto;
	}
	public void setFicDto(FeedItemCollectionDTO ficDto) {
		this.ficDto = ficDto;
	}

}
