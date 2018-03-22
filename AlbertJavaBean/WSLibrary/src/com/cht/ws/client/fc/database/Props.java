/**
 * Props.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.database;

public class Props  implements java.io.Serializable {
    private java.lang.Object[] propertyEntries;

    private java.lang.Object[] propertyKeys;

    public Props() {
    }

    public Props(
           java.lang.Object[] propertyEntries,
           java.lang.Object[] propertyKeys) {
           this.propertyEntries = propertyEntries;
           this.propertyKeys = propertyKeys;
    }


    /**
     * Gets the propertyEntries value for this Props.
     * 
     * @return propertyEntries
     */
    public java.lang.Object[] getPropertyEntries() {
        return propertyEntries;
    }


    /**
     * Sets the propertyEntries value for this Props.
     * 
     * @param propertyEntries
     */
    public void setPropertyEntries(java.lang.Object[] propertyEntries) {
        this.propertyEntries = propertyEntries;
    }


    /**
     * Gets the propertyKeys value for this Props.
     * 
     * @return propertyKeys
     */
    public java.lang.Object[] getPropertyKeys() {
        return propertyKeys;
    }


    /**
     * Sets the propertyKeys value for this Props.
     * 
     * @param propertyKeys
     */
    public void setPropertyKeys(java.lang.Object[] propertyKeys) {
        this.propertyKeys = propertyKeys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Props)) return false;
        Props other = (Props) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.propertyEntries==null && other.getPropertyEntries()==null) || 
             (this.propertyEntries!=null &&
              java.util.Arrays.equals(this.propertyEntries, other.getPropertyEntries()))) &&
            ((this.propertyKeys==null && other.getPropertyKeys()==null) || 
             (this.propertyKeys!=null &&
              java.util.Arrays.equals(this.propertyKeys, other.getPropertyKeys())));
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
        if (getPropertyEntries() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPropertyEntries());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPropertyEntries(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPropertyKeys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPropertyKeys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPropertyKeys(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Props.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://util.macromedia.com", "Props"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyEntries");
        elemField.setXmlName(new javax.xml.namespace.QName("", "propertyEntries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyKeys");
        elemField.setXmlName(new javax.xml.namespace.QName("", "propertyKeys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
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
