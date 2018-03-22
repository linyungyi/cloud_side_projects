/**
 * AuthoringWebInterfaces_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.internal;

public interface AuthoringWebInterfaces_PortType extends java.rmi.Remote {
    public java.lang.String listChannels() throws java.rmi.RemoteException, com.cht.ws.client.fc.internal.ManagementException;
    public java.lang.String createChannel(java.lang.String channelName) throws java.rmi.RemoteException, com.cht.ws.client.fc.internal.ManagementException;
    public java.lang.String importChannel(java.lang.String channelName, java.lang.String clientTypeName, java.lang.String deviceTypeName) throws java.rmi.RemoteException, com.cht.ws.client.fc.internal.ManagementException;
    public java.lang.String deleteChannel(java.lang.String channelName) throws java.rmi.RemoteException, com.cht.ws.client.fc.internal.ManagementException;
    public java.lang.String pollChannel(java.lang.String channelName) throws java.rmi.RemoteException, com.cht.ws.client.fc.internal.ManagementException;
    public java.lang.String addDevice(java.lang.String deviceId, java.lang.String clientTypeName, java.lang.String deviceTypeName) throws java.rmi.RemoteException, com.cht.ws.client.fc.internal.ManagementException;
    public java.lang.String listDevices() throws java.rmi.RemoteException, com.cht.ws.client.fc.internal.ManagementException;
    public java.lang.String removeDevice(java.lang.String deviceId) throws java.rmi.RemoteException, com.cht.ws.client.fc.internal.ManagementException;
    public java.lang.String retrieveDataSourceLog(java.lang.String channelName) throws java.rmi.RemoteException, com.cht.ws.client.fc.internal.ManagementException;
}
