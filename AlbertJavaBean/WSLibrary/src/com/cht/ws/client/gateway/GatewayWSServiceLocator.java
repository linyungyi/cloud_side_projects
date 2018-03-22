/**
 * GatewayWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.gateway;

public class GatewayWSServiceLocator extends org.apache.axis.client.Service implements com.cht.ws.client.gateway.GatewayWSService {

    public GatewayWSServiceLocator() {
    }


    public GatewayWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GatewayWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GatewayWSSoapPort
    private java.lang.String GatewayWSSoapPort_address = "http://localhost:7001/Gateway_WS/GatewayWS";

    public java.lang.String getGatewayWSSoapPortAddress() {
        return GatewayWSSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GatewayWSSoapPortWSDDServiceName = "GatewayWSSoapPort";

    public java.lang.String getGatewayWSSoapPortWSDDServiceName() {
        return GatewayWSSoapPortWSDDServiceName;
    }

    public void setGatewayWSSoapPortWSDDServiceName(java.lang.String name) {
        GatewayWSSoapPortWSDDServiceName = name;
    }

    public com.cht.ws.client.gateway.GatewayWS getGatewayWSSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GatewayWSSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGatewayWSSoapPort(endpoint);
    }

    public com.cht.ws.client.gateway.GatewayWS getGatewayWSSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cht.ws.client.gateway.GatewayWSServiceSoapBindingStub _stub = new com.cht.ws.client.gateway.GatewayWSServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getGatewayWSSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGatewayWSSoapPortEndpointAddress(java.lang.String address) {
        GatewayWSSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cht.ws.client.gateway.GatewayWS.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cht.ws.client.gateway.GatewayWSServiceSoapBindingStub _stub = new com.cht.ws.client.gateway.GatewayWSServiceSoapBindingStub(new java.net.URL(GatewayWSSoapPort_address), this);
                _stub.setPortName(getGatewayWSSoapPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GatewayWSSoapPort".equals(inputPortName)) {
            return getGatewayWSSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://com/cht/ws", "GatewayWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://com/cht/ws", "GatewayWSSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GatewayWSSoapPort".equals(portName)) {
            setGatewayWSSoapPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
