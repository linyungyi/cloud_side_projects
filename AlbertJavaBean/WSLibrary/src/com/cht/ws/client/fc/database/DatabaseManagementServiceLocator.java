/**
 * DatabaseManagementServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.database;

public class DatabaseManagementServiceLocator extends org.apache.axis.client.Service implements com.cht.ws.client.fc.database.DatabaseManagementService {

    public DatabaseManagementServiceLocator() {
    }


    public DatabaseManagementServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DatabaseManagementServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DatabaseManagement
    private java.lang.String DatabaseManagement_address = "http://localhost:9001/feedserver/admin/services/DatabaseManagement";

    public java.lang.String getDatabaseManagementAddress() {
        return DatabaseManagement_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DatabaseManagementWSDDServiceName = "DatabaseManagement";

    public java.lang.String getDatabaseManagementWSDDServiceName() {
        return DatabaseManagementWSDDServiceName;
    }

    public void setDatabaseManagementWSDDServiceName(java.lang.String name) {
        DatabaseManagementWSDDServiceName = name;
    }

    public com.cht.ws.client.fc.database.DatabaseManagement_PortType getDatabaseManagement() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DatabaseManagement_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDatabaseManagement(endpoint);
    }

    public com.cht.ws.client.fc.database.DatabaseManagement_PortType getDatabaseManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cht.ws.client.fc.database.DatabaseManagementSoapBindingStub _stub = new com.cht.ws.client.fc.database.DatabaseManagementSoapBindingStub(portAddress, this);
            _stub.setPortName(getDatabaseManagementWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDatabaseManagementEndpointAddress(java.lang.String address) {
        DatabaseManagement_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cht.ws.client.fc.database.DatabaseManagement_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cht.ws.client.fc.database.DatabaseManagementSoapBindingStub _stub = new com.cht.ws.client.fc.database.DatabaseManagementSoapBindingStub(new java.net.URL(DatabaseManagement_address), this);
                _stub.setPortName(getDatabaseManagementWSDDServiceName());
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
        if ("DatabaseManagement".equals(inputPortName)) {
            return getDatabaseManagement();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:DatabaseManagement:axis:management:server:flashcast:macromedia:com", "DatabaseManagementService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:DatabaseManagement:axis:management:server:flashcast:macromedia:com", "DatabaseManagement"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DatabaseManagement".equals(portName)) {
            setDatabaseManagementEndpointAddress(address);
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
