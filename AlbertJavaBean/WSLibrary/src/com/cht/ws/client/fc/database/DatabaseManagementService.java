/**
 * DatabaseManagementService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.database;

public interface DatabaseManagementService extends javax.xml.rpc.Service {
    public java.lang.String getDatabaseManagementAddress();

    public com.cht.ws.client.fc.database.DatabaseManagement_PortType getDatabaseManagement() throws javax.xml.rpc.ServiceException;

    public com.cht.ws.client.fc.database.DatabaseManagement_PortType getDatabaseManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
