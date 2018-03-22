/**
 * ClusterManagement_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.cluster;

public interface ClusterManagement_PortType extends java.rmi.Remote {
    public java.lang.String setProperty(java.lang.String propname, java.lang.String propvalue) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getProperty(java.lang.String propname) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public com.cht.ws.client.fc.cluster.ClusterManagement_Type getInstance() throws java.rmi.RemoteException;
    public java.lang.String getDebugLevel(java.lang.String loggerName) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String setDebugLevel(java.lang.String debugLevel, java.lang.String loggerName) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String listProperties() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String signalDevice(java.lang.String deviceid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String signalDeviceFeedApp(java.lang.String feedappid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceLoadAll(java.lang.String types) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceStartAll(java.lang.String types) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceStopAll(java.lang.String types) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceUnloadAll(java.lang.String types) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceLoad(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceUnload(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceStart(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceStop(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceReloadAll(java.lang.String types) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getActiveAdminCount() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getActiveUserCount() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String logoutUser(java.lang.String userid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String logoutDevice(java.lang.String deviceid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String logoutUserAll() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public int getServerCount() throws java.rmi.RemoteException;
    public java.lang.String servicesCount(java.lang.String type) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceDescribe(java.lang.String id, boolean dss) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceDescribeCollection(java.lang.String id, boolean dss) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceDescribePreference(java.lang.String id, boolean dss) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String describeAdminSession(java.lang.String adminid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String servicesList(java.lang.String type, boolean detail, boolean dss) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String serviceReload(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String setContextLogLevel(java.lang.String contextType, java.lang.String contextName, java.lang.String logLevel) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String logoutUserSession(java.lang.String sessionid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getClientNetworkStats() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getDatasourceNetworkStats() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getCacheHitsPerClass() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getCacheMissesPerClass() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getCacheEvictionsPerClass() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getCacheInfo() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String describeDeviceSession(java.lang.String deviceid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String describeUserSession(java.lang.String userid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String describeUserThrottle(java.lang.String userid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String describeServer() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getSystemStats() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getAdminSessionStats() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getUserSessionStats() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String getContextLogLevel(java.lang.String contextType, java.lang.String contextName) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String listContextLogLevel(java.lang.String contextType) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String clearContextLogLevel(java.lang.String contextType, java.lang.String contextName) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String logoutAdmin(java.lang.String adminid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String logoutAdminSession(java.lang.String sessionid) throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
    public java.lang.String logoutAdminAll() throws java.rmi.RemoteException, com.cht.ws.client.fc.cluster.ManagementException;
}
