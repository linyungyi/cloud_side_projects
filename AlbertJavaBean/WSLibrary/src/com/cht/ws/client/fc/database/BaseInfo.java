/**
 * BaseInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.database;

public abstract class BaseInfo  implements java.io.Serializable {
    private java.lang.String cacheKey;

    private java.lang.String cacheName;

    private long creation;

    private long entityVersion;

    private java.lang.String id;

    private boolean mutable;

    private java.lang.String name;

    private boolean stale;

    private long ttl;

    public BaseInfo() {
    }

    public BaseInfo(
           java.lang.String cacheKey,
           java.lang.String cacheName,
           long creation,
           long entityVersion,
           java.lang.String id,
           boolean mutable,
           java.lang.String name,
           boolean stale,
           long ttl) {
           this.cacheKey = cacheKey;
           this.cacheName = cacheName;
           this.creation = creation;
           this.entityVersion = entityVersion;
           this.id = id;
           this.mutable = mutable;
           this.name = name;
           this.stale = stale;
           this.ttl = ttl;
    }


    /**
     * Gets the cacheKey value for this BaseInfo.
     * 
     * @return cacheKey
     */
    public java.lang.String getCacheKey() {
        return cacheKey;
    }


    /**
     * Sets the cacheKey value for this BaseInfo.
     * 
     * @param cacheKey
     */
    public void setCacheKey(java.lang.String cacheKey) {
        this.cacheKey = cacheKey;
    }


    /**
     * Gets the cacheName value for this BaseInfo.
     * 
     * @return cacheName
     */
    public java.lang.String getCacheName() {
        return cacheName;
    }


    /**
     * Sets the cacheName value for this BaseInfo.
     * 
     * @param cacheName
     */
    public void setCacheName(java.lang.String cacheName) {
        this.cacheName = cacheName;
    }


    /**
     * Gets the creation value for this BaseInfo.
     * 
     * @return creation
     */
    public long getCreation() {
        return creation;
    }


    /**
     * Sets the creation value for this BaseInfo.
     * 
     * @param creation
     */
    public void setCreation(long creation) {
        this.creation = creation;
    }


    /**
     * Gets the entityVersion value for this BaseInfo.
     * 
     * @return entityVersion
     */
    public long getEntityVersion() {
        return entityVersion;
    }


    /**
     * Sets the entityVersion value for this BaseInfo.
     * 
     * @param entityVersion
     */
    public void setEntityVersion(long entityVersion) {
        this.entityVersion = entityVersion;
    }


    /**
     * Gets the id value for this BaseInfo.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this BaseInfo.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the mutable value for this BaseInfo.
     * 
     * @return mutable
     */
    public boolean isMutable() {
        return mutable;
    }


    /**
     * Sets the mutable value for this BaseInfo.
     * 
     * @param mutable
     */
    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }


    /**
     * Gets the name value for this BaseInfo.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this BaseInfo.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the stale value for this BaseInfo.
     * 
     * @return stale
     */
    public boolean isStale() {
        return stale;
    }


    /**
     * Sets the stale value for this BaseInfo.
     * 
     * @param stale
     */
    public void setStale(boolean stale) {
        this.stale = stale;
    }


    /**
     * Gets the ttl value for this BaseInfo.
     * 
     * @return ttl
     */
    public long getTtl() {
        return ttl;
    }


    /**
     * Sets the ttl value for this BaseInfo.
     * 
     * @param ttl
     */
    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BaseInfo)) return false;
        BaseInfo other = (BaseInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cacheKey==null && other.getCacheKey()==null) || 
             (this.cacheKey!=null &&
              this.cacheKey.equals(other.getCacheKey()))) &&
            ((this.cacheName==null && other.getCacheName()==null) || 
             (this.cacheName!=null &&
              this.cacheName.equals(other.getCacheName()))) &&
            this.creation == other.getCreation() &&
            this.entityVersion == other.getEntityVersion() &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            this.mutable == other.isMutable() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.stale == other.isStale() &&
            this.ttl == other.getTtl();
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
        if (getCacheKey() != null) {
            _hashCode += getCacheKey().hashCode();
        }
        if (getCacheName() != null) {
            _hashCode += getCacheName().hashCode();
        }
        _hashCode += new Long(getCreation()).hashCode();
        _hashCode += new Long(getEntityVersion()).hashCode();
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        _hashCode += (isMutable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += (isStale() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Long(getTtl()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BaseInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://core.db.server.flashcast.macromedia.com", "BaseInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cacheKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cacheKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cacheName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cacheName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entityVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mutable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mutable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ttl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ttl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
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
