package com.mzkj.domain;

public class MasterAccessOperationMapping {

    private String masterAccessOperationMappingId;
    private String masterType;
    private String masterValue;
    private String accessType;
    private String accessValue;
    private String operation;
    private String tenantId;
    private String createUser;
    private String createDate;
    private String updateUser;
    private String updateDate;
    private String deleted;

    public String getMasterAccessOperationMappingId() {
        return masterAccessOperationMappingId;
    }

    public void setMasterAccessOperationMappingId(String masterAccessOperationMappingId) {
        this.masterAccessOperationMappingId = masterAccessOperationMappingId;
    }

    public String getMasterType() {
        return masterType;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
    }

    public String getMasterValue() {
        return masterValue;
    }

    public void setMasterValue(String masterValue) {
        this.masterValue = masterValue;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getAccessValue() {
        return accessValue;
    }

    public void setAccessValue(String accessValue) {
        this.accessValue = accessValue;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
