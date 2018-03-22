/**
 * DatabaseManagement_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.database;

public class DatabaseManagement_Type  implements java.io.Serializable {
    private int adminCount;

    private int clientCount;

    private int subscriberCount;

    private java.lang.String subscriptionCountPerFeedApp;

    public DatabaseManagement_Type() {
    }

    public DatabaseManagement_Type(
           int adminCount,
           int clientCount,
           int subscriberCount,
           java.lang.String subscriptionCountPerFeedApp) {
           this.adminCount = adminCount;
           this.clientCount = clientCount;
           this.subscriberCount = subscriberCount;
           this.subscriptionCountPerFeedApp = subscriptionCountPerFeedApp;
    }


    /**
     * Gets the adminCount value for this DatabaseManagement_Type.
     * 
     * @return adminCount
     */
    public int getAdminCount() {
        return adminCount;
    }


    /**
     * Sets the adminCount value for this DatabaseManagement_Type.
     * 
     * @param adminCount
     */
    public void setAdminCount(int adminCount) {
        this.adminCount = adminCount;
    }


    /**
     * Gets the clientCount value for this DatabaseManagement_Type.
     * 
     * @return clientCount
     */
    public int getClientCount() {
        return clientCount;
    }


    /**
     * Sets the clientCount value for this DatabaseManagement_Type.
     * 
     * @param clientCount
     */
    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }


    /**
     * Gets the subscriberCount value for this DatabaseManagement_Type.
     * 
     * @return subscriberCount
     */
    public int getSubscriberCount() {
        return subscriberCount;
    }


    /**
     * Sets the subscriberCount value for this DatabaseManagement_Type.
     * 
     * @param subscriberCount
     */
    public void setSubscriberCount(int subscriberCount) {
        this.subscriberCount = subscriberCount;
    }


    /**
     * Gets the subscriptionCountPerFeedApp value for this DatabaseManagement_Type.
     * 
     * @return subscriptionCountPerFeedApp
     */
    public java.lang.String getSubscriptionCountPerFeedApp() {
        return subscriptionCountPerFeedApp;
    }


    /**
     * Sets the subscriptionCountPerFeedApp value for this DatabaseManagement_Type.
     * 
     * @param subscriptionCountPerFeedApp
     */
    public void setSubscriptionCountPerFeedApp(java.lang.String subscriptionCountPerFeedApp) {
        this.subscriptionCountPerFeedApp = subscriptionCountPerFeedApp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatabaseManagement_Type)) return false;
        DatabaseManagement_Type other = (DatabaseManagement_Type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.adminCount == other.getAdminCount() &&
            this.clientCount == other.getClientCount() &&
            this.subscriberCount == other.getSubscriberCount() &&
            ((this.subscriptionCountPerFeedApp==null && other.getSubscriptionCountPerFeedApp()==null) || 
             (this.subscriptionCountPerFeedApp!=null &&
              this.subscriptionCountPerFeedApp.equals(other.getSubscriptionCountPerFeedApp())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getAdminCount();
        _hashCode += getClientCount();
        _hashCode += getSubscriberCount();
        if (getSubscriptionCountPerFeedApp() != null) {
            _hashCode += getSubscriptionCountPerFeedApp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatabaseManagement_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DatabaseManagement:axis:management:server:flashcast:macromedia:com", "DatabaseManagement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adminCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adminCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subscriberCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriptionCountPerFeedApp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subscriptionCountPerFeedApp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
