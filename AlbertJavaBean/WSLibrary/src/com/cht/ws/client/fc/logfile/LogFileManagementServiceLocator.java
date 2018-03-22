/**
 * LogFileManagementServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.logfile;

public class LogFileManagementServiceLocator extends org.apache.axis.client.Service implements com.cht.ws.client.fc.logfile.LogFileManagementService {

    public LogFileManagementServiceLocator() {
    }


    public LogFileManagementServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LogFileManagementServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LogFileManagement
    private java.lang.String LogFileManagement_address = "http://localhost:9001/feedserver/admin/services/LogFileManagement";

    public java.lang.String getLogFileManagementAddress() {
        return LogFileManagement_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LogFileManagementWSDDServiceName = "LogFileManagement";

    public java.lang.String getLogFileManagementWSDDServiceName() {
        return LogFileManagementWSDDServiceName;
    }

    public void setLogFileManagementWSDDServiceName(java.lang.String name) {
        LogFileManagementWSDDServiceName = name;
    }

    public com.cht.ws.client.fc.logfile.LogFileManagement_PortType getLogFileManagement() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LogFileManagement_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLogFileManagement(endpoint);
    }

    public com.cht.ws.client.fc.logfile.LogFileManagement_PortType getLogFileManagement(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cht.ws.client.fc.logfile.LogFileManagementSoapBindingStub _stub = new com.cht.ws.client.fc.logfile.LogFileManagementSoapBindingStub(portAddress, this);
            _stub.setPortName(getLogFileManagementWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLogFileManagementEndpointAddress(java.lang.String address) {
        LogFileManagement_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cht.ws.client.fc.logfile.LogFileManagement_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cht.ws.client.fc.logfile.LogFileManagementSoapBindingStub _stub = new com.cht.ws.client.fc.logfile.LogFileManagementSoapBindingStub(new java.net.URL(LogFileManagement_address), this);
                _stub.setPortName(getLogFileManagementWSDDServiceName());
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
        if ("LogFileManagement".equals(inputPortName)) {
            return getLogFileManagement();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:LogFileManagement:axis:management:server:flashcast:macromedia:com", "LogFileManagementService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:LogFileManagement:axis:management:server:flashcast:macromedia:com", "LogFileManagement"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LogFileManagement".equals(portName)) {
            setLogFileManagementEndpointAddress(address);
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
