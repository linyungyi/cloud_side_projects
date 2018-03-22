package com.cht.dto.ws;

import java.io.Serializable;

public class SubscriptionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String feedApp;
	private String name;
	private String deviceId;
	private long startTime;
	private String startDay;
	private boolean activate;
	private boolean trial;
	private boolean expired;
	private boolean cancelled;
	private boolean charged;
	private String price;
	private String currency;
	private String stateInfo;
	private String error;
	private String subType;
	
	public boolean isActivate() {
		return activate;
	}
	public void setActivate(boolean activate) {
		this.activate = activate;
	}
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	public boolean isCharged() {
		return charged;
	}
	public void setCharged(boolean charged) {
		this.charged = charged;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public String getFeedApp() {
		return feedApp;
	}
	public void setFeedApp(String feedApp) {
		this.feedApp = feedApp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public boolean isTrial() {
		return trial;
	}
	public void setTrial(boolean trial) {
		this.trial = trial;
	}
}
