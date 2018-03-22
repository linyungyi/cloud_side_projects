/**
 * ClusterManagementService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.cluster;

public interface ClusterManagementService extends javax.xml.rpc.Service {
    public java.lang.String getClusterManagementAddress();

    public com.cht.ws.client.fc.cluster.ClusterManagement_PortType getClusterManagement() throws javax.xml.rpc.ServiceException;

    public com.cht.ws.client.fc.cluster.ClusterManagement_PortType getClusterManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
