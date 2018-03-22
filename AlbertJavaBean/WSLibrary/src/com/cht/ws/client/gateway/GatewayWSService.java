/**
 * GatewayWSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.gateway;

public interface GatewayWSService extends javax.xml.rpc.Service {
    public java.lang.String getGatewayWSSoapPortAddress();

    public com.cht.ws.client.gateway.GatewayWS getGatewayWSSoapPort() throws javax.xml.rpc.ServiceException;

    public com.cht.ws.client.gateway.GatewayWS getGatewayWSSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
