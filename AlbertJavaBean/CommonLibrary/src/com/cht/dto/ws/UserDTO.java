package com.cht.dto.ws;

import java.io.Serializable;
import java.util.Collection;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private long groupModify;
	private long creation;
	private Collection userGroups;
	private Collection devices;
	
	public long getCreation() {
		return creation;
	}
	public void setCreation(long creation) {
		this.creation = creation;
	}
	public Collection getDevices() {
		return devices;
	}
	public void setDevices(Collection devices) {
		this.devices = devices;
	}
	public long getGroupModify() {
		return groupModify;
	}
	public void setGroupModify(long groupModify) {
		this.groupModify = groupModify;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(Collection userGroups) {
		this.userGroups = userGroups;
	}
}
