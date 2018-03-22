/**
 * AuthServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.authentication;

public interface AuthServiceService extends javax.xml.rpc.Service {
    public java.lang.String getAuthenticationManagementAddress();

    public com.cht.ws.client.fc.authentication.AuthService getAuthenticationManagement() throws javax.xml.rpc.ServiceException;

    public com.cht.ws.client.fc.authentication.AuthService getAuthenticationManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
