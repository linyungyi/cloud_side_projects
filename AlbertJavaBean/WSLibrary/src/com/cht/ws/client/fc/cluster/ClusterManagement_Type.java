/**
 * ClusterManagement_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cht.ws.client.fc.cluster;

public class ClusterManagement_Type  implements java.io.Serializable {
    private java.lang.String activeAdminCount;

    private java.lang.String activeUserCount;

    private java.lang.String adminSessionStats;

    private java.lang.String cacheEvictionsPerClass;

    private java.lang.String cacheHitsPerClass;

    private java.lang.String cacheInfo;

    private java.lang.String cacheMissesPerClass;

    private java.lang.String clientNetworkStats;

    private java.lang.String datasourceNetworkStats;

    private int serverCount;

    private java.lang.String systemStats;

    private java.lang.String userSessionStats;

    public ClusterManagement_Type() {
    }

    public ClusterManagement_Type(
           java.lang.String activeAdminCount,
           java.lang.String activeUserCount,
           java.lang.String adminSessionStats,
           java.lang.String cacheEvictionsPerClass,
           java.lang.String cacheHitsPerClass,
           java.lang.String cacheInfo,
           java.lang.String cacheMissesPerClass,
           java.lang.String clientNetworkStats,
           java.lang.String datasourceNetworkStats,
           int serverCount,
           java.lang.String systemStats,
           java.lang.String userSessionStats) {
           this.activeAdminCount = activeAdminCount;
           this.activeUserCount = activeUserCount;
           this.adminSessionStats = adminSessionStats;
           this.cacheEvictionsPerClass = cacheEvictionsPerClass;
           this.cacheHitsPerClass = cacheHitsPerClass;
           this.cacheInfo = cacheInfo;
           this.cacheMissesPerClass = cacheMissesPerClass;
           this.clientNetworkStats = clientNetworkStats;
           this.datasourceNetworkStats = datasourceNetworkStats;
           this.serverCount = serverCount;
           this.systemStats = systemStats;
           this.userSessionStats = userSessionStats;
    }


    /**
     * Gets the activeAdminCount value for this ClusterManagement_Type.
     * 
     * @return activeAdminCount
     */
    public java.lang.String getActiveAdminCount() {
        return activeAdminCount;
    }


    /**
     * Sets the activeAdminCount value for this ClusterManagement_Type.
     * 
     * @param activeAdminCount
     */
    public void setActiveAdminCount(java.lang.String activeAdminCount) {
        this.activeAdminCount = activeAdminCount;
    }


    /**
     * Gets the activeUserCount value for this ClusterManagement_Type.
     * 
     * @return activeUserCount
     */
    public java.lang.String getActiveUserCount() {
        return activeUserCount;
    }


    /**
     * Sets the activeUserCount value for this ClusterManagement_Type.
     * 
     * @param activeUserCount
     */
    public void setActiveUserCount(java.lang.String activeUserCount) {
        this.activeUserCount = activeUserCount;
    }


    /**
     * Gets the adminSessionStats value for this ClusterManagement_Type.
     * 
     * @return adminSessionStats
     */
    public java.lang.String getAdminSessionStats() {
        return adminSessionStats;
    }


    /**
     * Sets the adminSessionStats value for this ClusterManagement_Type.
     * 
     * @param adminSessionStats
     */
    public void setAdminSessionStats(java.lang.String adminSessionStats) {
        this.adminSessionStats = adminSessionStats;
    }


    /**
     * Gets the cacheEvictionsPerClass value for this ClusterManagement_Type.
     * 
     * @return cacheEvictionsPerClass
     */
    public java.lang.String getCacheEvictionsPerClass() {
        return cacheEvictionsPerClass;
    }


    /**
     * Sets the cacheEvictionsPerClass value for this ClusterManagement_Type.
     * 
     * @param cacheEvictionsPerClass
     */
    public void setCacheEvictionsPerClass(java.lang.String cacheEvictionsPerClass) {
        this.cacheEvictionsPerClass = cacheEvictionsPerClass;
    }


    /**
     * Gets the cacheHitsPerClass value for this ClusterManagement_Type.
     * 
     * @return cacheHitsPerClass
     */
    public java.lang.String getCacheHitsPerClass() {
        return cacheHitsPerClass;
    }


    /**
     * Sets the cacheHitsPerClass value for this ClusterManagement_Type.
     * 
     * @param cacheHitsPerClass
     */
    public void setCacheHitsPerClass(java.lang.String cacheHitsPerClass) {
        this.cacheHitsPerClass = cacheHitsPerClass;
    }


    /**
     * Gets the cacheInfo value for this ClusterManagement_Type.
     * 
     * @return cacheInfo
     */
    public java.lang.String getCacheInfo() {
        return cacheInfo;
    }


    /**
     * Sets the cacheInfo value for this ClusterManagement_Type.
     * 
     * @param cacheInfo
     */
    public void setCacheInfo(java.lang.String cacheInfo) {
        this.cacheInfo = cacheInfo;
    }


    /**
     * Gets the cacheMissesPerClass value for this ClusterManagement_Type.
     * 
     * @return cacheMissesPerClass
     */
    public java.lang.String getCacheMissesPerClass() {
        return cacheMissesPerClass;
    }


    /**
     * Sets the cacheMissesPerClass value for this ClusterManagement_Type.
     * 
     * @param cacheMissesPerClass
     */
    public void setCacheMissesPerClass(java.lang.String cacheMissesPerClass) {
        this.cacheMissesPerClass = cacheMissesPerClass;
    }


    /**
     * Gets the clientNetworkStats value for this ClusterManagement_Type.
     * 
     * @return clientNetworkStats
     */
    public java.lang.String getClientNetworkStats() {
        return clientNetworkStats;
    }


    /**
     * Sets the clientNetworkStats value for this ClusterManagement_Type.
     * 
     * @param clientNetworkStats
     */
    public void setClientNetworkStats(java.lang.String clientNetworkStats) {
        this.clientNetworkStats = clientNetworkStats;
    }


    /**
     * Gets the datasourceNetworkStats value for this ClusterManagement_Type.
     * 
     * @return datasourceNetworkStats
     */
    public java.lang.String getDatasourceNetworkStats() {
        return datasourceNetworkStats;
    }


    /**
     * Sets the datasourceNetworkStats value for this ClusterManagement_Type.
     * 
     * @param datasourceNetworkStats
     */
    public void setDatasourceNetworkStats(java.lang.String datasourceNetworkStats) {
        this.datasourceNetworkStats = datasourceNetworkStats;
    }


    /**
     * Gets the serverCount value for this ClusterManagement_Type.
     * 
     * @return serverCount
     */
    public int getServerCount() {
        return serverCount;
    }


    /**
     * Sets the serverCount value for this ClusterManagement_Type.
     * 
     * @param serverCount
     */
    public void setServerCount(int serverCount) {
        this.serverCount = serverCount;
    }


    /**
     * Gets the systemStats value for this ClusterManagement_Type.
     * 
     * @return systemStats
     */
    public java.lang.String getSystemStats() {
        return systemStats;
    }


    /**
     * Sets the systemStats value for this ClusterManagement_Type.
     * 
     * @param systemStats
     */
    public void setSystemStats(java.lang.String systemStats) {
        this.systemStats = systemStats;
    }


    /**
     * Gets the userSessionStats value for this ClusterManagement_Type.
     * 
     * @return userSessionStats
     */
    public java.lang.String getUserSessionStats() {
        return userSessionStats;
    }


    /**
     * Sets the userSessionStats value for this ClusterManagement_Type.
     * 
     * @param userSessionStats
     */
    public void setUserSessionStats(java.lang.String userSessionStats) {
        this.userSessionStats = userSessionStats;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClusterManagement_Type)) return false;
        ClusterManagement_Type other = (ClusterManagement_Type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.activeAdminCount==null && other.getActiveAdminCount()==null) || 
             (this.activeAdminCount!=null &&
              this.activeAdminCount.equals(other.getActiveAdminCount()))) &&
            ((this.activeUserCount==null && other.getActiveUserCount()==null) || 
             (this.activeUserCount!=null &&
              this.activeUserCount.equals(other.getActiveUserCount()))) &&
            ((this.adminSessionStats==null && other.getAdminSessionStats()==null) || 
             (this.adminSessionStats!=null &&
              this.adminSessionStats.equals(other.getAdminSessionStats()))) &&
            ((this.cacheEvictionsPerClass==null && other.getCacheEvictionsPerClass()==null) || 
             (this.cacheEvictionsPerClass!=null &&
              this.cacheEvictionsPerClass.equals(other.getCacheEvictionsPerClass()))) &&
            ((this.cacheHitsPerClass==null && other.getCacheHitsPerClass()==null) || 
             (this.cacheHitsPerClass!=null &&
              this.cacheHitsPerClass.equals(other.getCacheHitsPerClass()))) &&
            ((this.cacheInfo==null && other.getCacheInfo()==null) || 
             (this.cacheInfo!=null &&
              this.cacheInfo.equals(other.getCacheInfo()))) &&
            ((this.cacheMissesPerClass==null && other.getCacheMissesPerClass()==null) || 
             (this.cacheMissesPerClass!=null &&
              this.cacheMissesPerClass.equals(other.getCacheMissesPerClass()))) &&
            ((this.clientNetworkStats==null && other.getClientNetworkStats()==null) || 
             (this.clientNetworkStats!=null &&
              this.clientNetworkStats.equals(other.getClientNetworkStats()))) &&
            ((this.datasourceNetworkStats==null && other.getDatasourceNetworkStats()==null) || 
             (this.datasourceNetworkStats!=null &&
              this.datasourceNetworkStats.equals(other.getDatasourceNetworkStats()))) &&
            this.serverCount == other.getServerCount() &&
            ((this.systemStats==null && other.getSystemStats()==null) || 
             (this.systemStats!=null &&
              this.systemStats.equals(other.getSystemStats()))) &&
            ((this.userSessionStats==null && other.getUserSessionStats()==null) || 
             (this.userSessionStats!=null &&
              this.userSessionStats.equals(other.getUserSessionStats())));
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
        if (getActiveAdminCount() != null) {
            _hashCode += getActiveAdminCount().hashCode();
        }
        if (getActiveUserCount() != null) {
            _hashCode += getActiveUserCount().hashCode();
        }
        if (getAdminSessionStats() != null) {
            _hashCode += getAdminSessionStats().hashCode();
        }
        if (getCacheEvictionsPerClass() != null) {
            _hashCode += getCacheEvictionsPerClass().hashCode();
        }
        if (getCacheHitsPerClass() != null) {
            _hashCode += getCacheHitsPerClass().hashCode();
        }
        if (getCacheInfo() != null) {
            _hashCode += getCacheInfo().hashCode();
        }
        if (getCacheMissesPerClass() != null) {
            _hashCode += getCacheMissesPerClass().hashCode();
        }
        if (getClientNetworkStats() != null) {
            _hashCode += getClientNetworkStats().hashCode();
        }
        if (getDatasourceNetworkStats() != null) {
            _hashCode += getDatasourceNetworkStats().hashCode();
        }
        _hashCode += getServerCount();
        if (getSystemStats() != null) {
            _hashCode += getSystemStats().hashCode();
        }
        if (getUserSessionStats() != null) {
            _hashCode += getUserSessionStats().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClusterManagement_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ClusterManagement:axis:management:server:flashcast:macromedia:com", "ClusterManagement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activeAdminCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activeAdminCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activeUserCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activeUserCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adminSessionStats");
        elemField.setXmlName(new javax.xml.namespace.QName("", "adminSessionStats"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cacheEvictionsPerClass");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cacheEvictionsPerClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cacheHitsPerClass");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cacheHitsPerClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cacheInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cacheInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cacheMissesPerClass");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cacheMissesPerClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientNetworkStats");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientNetworkStats"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datasourceNetworkStats");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datasourceNetworkStats"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serverCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemStats");
        elemField.setXmlName(new javax.xml.namespace.QName("", "systemStats"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userSessionStats");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userSessionStats"));
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
