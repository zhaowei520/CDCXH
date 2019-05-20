package com.mzkj.bean;

import com.mzkj.domain.Privilege;

public class PrivilegeOfUsergroupBean extends Privilege {

    private String usergroupId;
    private String masterAccessOperationMappingId;

    public String getMasterAccessOperationMappingId() {
        return masterAccessOperationMappingId;
    }

    public void setMasterAccessOperationMappingId(String masterAccessOperationMappingId) {
        this.masterAccessOperationMappingId = masterAccessOperationMappingId;
    }

    public String getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(String usergroupId) {
        this.usergroupId = usergroupId;
    }
}