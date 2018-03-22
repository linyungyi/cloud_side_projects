/**
 * 
 */
package com.cht.dto;

/**
 * @author alberltin
 *
 */
public class UserAuthDTO extends BaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long d;
	private String userid;
	private String devid;
	private String sessid;
	private String addr;
	private String action;
	private long logout_timeon;
	private String act;
	
	public UserAuthDTO(){
		super();
	}

	/**
	 * @return the act
	 */
	public String getAct() {
		return act;
	}

	/**
	 * @param act the act to set
	 */
	public void setAct(String act) {
		this.act = act;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}

	/**
	 * @return the d
	 */
	public long getD() {
		return d;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(long d) {
		this.d = d;
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
	 * @return the logout_timeon
	 */
	public long getLogout_timeon() {
		return logout_timeon;
	}

	/**
	 * @param logout_timeon the logout_timeon to set
	 */
	public void setLogout_timeon(long logout_timeon) {
		this.logout_timeon = logout_timeon;
	}

	/**
	 * @return the sessid
	 */
	public String getSessid() {
		return sessid;
	}

	/**
	 * @param sessid the sessid to set
	 */
	public void setSessid(String sessid) {
		this.sessid = sessid;
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
        		//"UserAuthDTO begin*********** \n"+
        		"HostAddress.[" + super.HostAddress + "] \n" +
                "Action.[" + this.action + "] \n" +
                "Act.[" + this.act + "] \n" +
                "Addr.[" + this.addr + "] \n" +
                "D.[" + this.d + "] \n" +
                "Devid.[" + this.devid + "] \n" +
                "SessId.[" + this.sessid + "] \n" +
                "UserId.[" + this.userid + "] \n" +
                "logout_Timeon.[" + this.logout_timeon + "] \n" ;
                //"UserAuthDTO end************* \n" ;
        
        return result;
    }	
}
