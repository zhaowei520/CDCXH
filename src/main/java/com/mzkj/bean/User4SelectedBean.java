package com.mzkj.bean;

import com.mzkj.domain.User;

/**
 * 说明： 业务对象，与数据库操作 创建人：CDCXH 创建时间：2019-04-18
 */
public class User4SelectedBean extends User {

    private String masterAccessOperationMappingId;

    public String getMasterAccessOperationMappingId() {
        return masterAccessOperationMappingId;
    }

    public void setMasterAccessOperationMappingId(String masterAccessOperationMappingId) {
        this.masterAccessOperationMappingId = masterAccessOperationMappingId;
    }
}

