package com.cht.dto.ws;

import java.io.Serializable;
import java.util.Collection;

public class ListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection subscriptions;
	private Collection acls;
	private Collection feedapps;
	private Collection categorys;
	private Collection pkgsubscriptions;
	private String type;
	public Collection getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Collection subscriptions) {
		this.subscriptions = subscriptions;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the acls
	 */
	public Collection getAcls() {
		return acls;
	}

	/**
	 * @param acls the acls to set
	 */
	public void setAcls(Collection acls) {
		this.acls = acls;
	}

	/**
	 * @return the feedapps
	 */
	public Collection getFeedapps() {
		return feedapps;
	}

	/**
	 * @param feedapps the feedapps to set
	 */
	public void setFeedapps(Collection feedapps) {
		this.feedapps = feedapps;
	}

	/**
	 * @return the pkgsubscriptions
	 */
	public Collection getPkgsubscriptions() {
		return pkgsubscriptions;
	}

	/**
	 * @param pkgsubscriptions the pkgsubscriptions to set
	 */
	public void setPkgsubscriptions(Collection pkgsubscriptions) {
		this.pkgsubscriptions = pkgsubscriptions;
	}

	public Collection getCategorys() {
		return categorys;
	}

	public void setCategorys(Collection categorys) {
		this.categorys = categorys;
	}

}
