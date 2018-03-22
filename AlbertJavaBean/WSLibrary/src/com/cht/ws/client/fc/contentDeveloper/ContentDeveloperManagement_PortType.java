/**
 * ContentDeveloperManagement_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.contentDeveloper;

public interface ContentDeveloperManagement_PortType extends java.rmi.Remote {
    public com.cht.ws.client.fc.contentDeveloper.ContentDeveloperManagement_Type getInstance() throws java.rmi.RemoteException;
    public java.lang.String getDataSource(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public boolean removeDataSource(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public void fault() throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String removeFeedApp(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String getFeedApp(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String listContent() throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String createRSSDataSource(java.lang.String name, java.lang.String desc, int pollInterval, java.lang.String url, int numItems, int timeToLive, java.lang.String feedGuid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public boolean updateRSSDataSource(java.lang.String guid, java.lang.String name, java.lang.String desc, int pollInterval, java.lang.String url, int numItems, int timeToLive, java.lang.String feedGuid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String createLDSPDataSource(java.lang.String name, java.lang.String desc, int pollInterval, boolean prefSensitive, java.lang.String feedGuid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public boolean updateLDSPDataSource(java.lang.String guid, java.lang.String name, java.lang.String desc, int pollInterval, boolean prefSensitive, java.lang.String feedGuid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String modifyLDSPQueriesAndReget(java.lang.String guid, boolean shouldRemove, java.lang.String typeRemove, java.lang.String nameRemove, boolean shouldAdd, java.lang.String type, java.lang.String name, java.lang.String path, java.lang.String idParam, int maxIds, int maxLen) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public boolean createLDSPQuery(java.lang.String guid, java.lang.String type, java.lang.String name, java.lang.String path, java.lang.String idParam, int maxIds, int maxLen) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public boolean removeLDSPQuery(java.lang.String guid, java.lang.String type, java.lang.String name) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String createStaticDataSource(java.lang.String name, java.lang.String desc, int pollInterval, java.lang.String feedGuid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public boolean updateStaticDataSource(java.lang.String guid, java.lang.String name, java.lang.String desc, int pollInterval, java.lang.String feedGuid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String getFeedState(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String getDataSourceLog(java.lang.String guid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String createFeedApp(java.lang.String name, java.lang.String desc, java.lang.String version, java.lang.String longDesc, java.lang.String feedGuid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public boolean updateFeedApp(java.lang.String feedAppGuid, java.lang.String name, java.lang.String desc, java.lang.String version, java.lang.String longDesc, java.lang.String feedGuid) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public boolean removeFCA(java.lang.String guid, java.lang.String clientTypeName, java.lang.String deviceTypeName) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public boolean removeFCA(java.lang.String guid, java.lang.String clientTypeName, java.lang.String deviceTypeName, java.lang.String itemId) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public void removePref(java.lang.String guid, java.lang.String clientTypeName, java.lang.String deviceTypeName) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String getPref(java.lang.String guid, java.lang.String clientTypeName, java.lang.String deviceTypeName) throws java.rmi.RemoteException, com.cht.ws.client.fc.contentDeveloper.ManagementException;
    public java.lang.String getDeviceAndClientTypes() throws java.rmi.RemoteException;
}
