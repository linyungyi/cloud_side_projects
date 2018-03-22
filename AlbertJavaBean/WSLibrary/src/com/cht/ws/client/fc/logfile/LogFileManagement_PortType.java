/**
 * LogFileManagement_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.logfile;

public interface LogFileManagement_PortType extends java.rmi.Remote {
    public java.lang.String[] getLogFiles(java.lang.String appender, java.util.Calendar from, java.util.Calendar to) throws java.rmi.RemoteException, com.cht.ws.client.fc.logfile.ManagementException;
}
