/**
 * 
 */
package com.cht.dto;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * @author alberltin
 *
 */
public class BaseDTO implements Serializable {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected String HostAddress;
	
	public BaseDTO(){
		try{
			InetAddress addr = InetAddress.getLocalHost();
			HostAddress=addr.getHostAddress().toString();
		}catch(Exception e){
			HostAddress="0.0.0.0";
		}
	}

	/**
	 * @return the hostAddress
	 */
	public String getHostAddress() {
		return HostAddress;
	}

	/**
	 * @param hostAddress the hostAddress to set
	 */
	public void setHostAddress(String hostAddress) {
		HostAddress = hostAddress;
	}
}
