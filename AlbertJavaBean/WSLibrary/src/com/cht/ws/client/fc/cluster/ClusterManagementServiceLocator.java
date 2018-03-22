/**
 * ClusterManagementServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.cluster;

public class ClusterManagementServiceLocator extends org.apache.axis.client.Service implements com.cht.ws.client.fc.cluster.ClusterManagementService {

    public ClusterManagementServiceLocator() {
    }


    public ClusterManagementServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ClusterManagementServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ClusterManagement
    private java.lang.String ClusterManagement_address = "http://localhost:9001/feedserver/admin/services/ClusterManagement";

    public java.lang.String getClusterManagementAddress() {
        return ClusterManagement_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ClusterManagementWSDDServiceName = "ClusterManagement";

    public java.lang.String getClusterManagementWSDDServiceName() {
        return ClusterManagementWSDDServiceName;
    }

    public void setClusterManagementWSDDServiceName(java.lang.String name) {
        ClusterManagementWSDDServiceName = name;
    }

    public com.cht.ws.client.fc.cluster.ClusterManagement_PortType getClusterManagement() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ClusterManagement_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getClusterManagement(endpoint);
    }

    public com.cht.ws.client.fc.cluster.ClusterManagement_PortType getClusterManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cht.ws.client.fc.cluster.ClusterManagementSoapBindingStub _stub = new com.cht.ws.client.fc.cluster.ClusterManagementSoapBindingStub(portAddress, this);
            _stub.setPortName(getClusterManagementWSDDServiceName());
            _stub.setUsername("weblogic");
            _stub.setPassword("weblogic");
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setClusterManagementEndpointAddress(java.lang.String address) {
        ClusterManagement_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cht.ws.client.fc.cluster.ClusterManagement_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cht.ws.client.fc.cluster.ClusterManagementSoapBindingStub _stub = new com.cht.ws.client.fc.cluster.ClusterManagementSoapBindingStub(new java.net.URL(ClusterManagement_address), this);
                _stub.setPortName(getClusterManagementWSDDServiceName());
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
        if ("ClusterManagement".equals(inputPortName)) {
            return getClusterManagement();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:ClusterManagement:axis:management:server:flashcast:macromedia:com", "ClusterManagementService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:ClusterManagement:axis:management:server:flashcast:macromedia:com", "ClusterManagement"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ClusterManagement".equals(portName)) {
            setClusterManagementEndpointAddress(address);
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
