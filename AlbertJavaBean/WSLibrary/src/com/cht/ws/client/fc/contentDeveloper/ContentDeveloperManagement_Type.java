/**
 * ContentDeveloperManagement_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.contentDeveloper;

public class ContentDeveloperManagement_Type  implements java.io.Serializable {
    private java.lang.String deviceAndClientTypes;

    public ContentDeveloperManagement_Type() {
    }

    public ContentDeveloperManagement_Type(
           java.lang.String deviceAndClientTypes) {
           this.deviceAndClientTypes = deviceAndClientTypes;
    }


    /**
     * Gets the deviceAndClientTypes value for this ContentDeveloperManagement_Type.
     * 
     * @return deviceAndClientTypes
     */
    public java.lang.String getDeviceAndClientTypes() {
        return deviceAndClientTypes;
    }


    /**
     * Sets the deviceAndClientTypes value for this ContentDeveloperManagement_Type.
     * 
     * @param deviceAndClientTypes
     */
    public void setDeviceAndClientTypes(java.lang.String deviceAndClientTypes) {
        this.deviceAndClientTypes = deviceAndClientTypes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentDeveloperManagement_Type)) return false;
        ContentDeveloperManagement_Type other = (ContentDeveloperManagement_Type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.deviceAndClientTypes==null && other.getDeviceAndClientTypes()==null) || 
             (this.deviceAndClientTypes!=null &&
              this.deviceAndClientTypes.equals(other.getDeviceAndClientTypes())));
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
        if (getDeviceAndClientTypes() != null) {
            _hashCode += getDeviceAndClientTypes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContentDeveloperManagement_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ContentDeveloperManagement:axis:management:server:flashcast:macromedia:com", "ContentDeveloperManagement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deviceAndClientTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deviceAndClientTypes"));
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
