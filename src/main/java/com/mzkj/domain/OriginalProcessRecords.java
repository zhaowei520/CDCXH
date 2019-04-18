package com.mzkj.domain;

import java.io.Serializable;

/**
 * 说明： 实体类，与对应表字段相同
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
public class OriginalProcessRecords implements Serializable{
private String originalProcessRecordsId;
        private String originalId;
        private String originalFromTime;
        private String originalFromUsername;
        private String originalOutTime;
        private String originalOutUsername;
        private String remark;
        private String createDate;
        private String createUser;
        private String updateDate;
        private String updateUser;
        private String tenantId;

    public String getOriginalProcessRecordsId(){
        return originalProcessRecordsId;
    }
    public void setOriginalProcessRecordsId (String originalProcessRecordsId){
        this.originalProcessRecordsId = originalProcessRecordsId;
    }

    public String getOriginalId(){
        return originalId;
    }
    public void setOriginalId (String originalId){
        this.originalId = originalId;
    }
    public String getOriginalFromTime(){
        return originalFromTime;
    }
    public void setOriginalFromTime (String originalFromTime){
        this.originalFromTime = originalFromTime;
    }
    public String getOriginalFromUsername(){
        return originalFromUsername;
    }
    public void setOriginalFromUsername (String originalFromUsername){
        this.originalFromUsername = originalFromUsername;
    }
    public String getOriginalOutTime(){
        return originalOutTime;
    }
    public void setOriginalOutTime (String originalOutTime){
        this.originalOutTime = originalOutTime;
    }
    public String getOriginalOutUsername(){
        return originalOutUsername;
    }
    public void setOriginalOutUsername (String originalOutUsername){
        this.originalOutUsername = originalOutUsername;
    }
    public String getRemark(){
        return remark;
    }
    public void setRemark (String remark){
        this.remark = remark;
    }
    public String getCreateDate(){
        return createDate;
    }
    public void setCreateDate (String createDate){
        this.createDate = createDate;
    }
    public String getCreateUser(){
        return createUser;
    }
    public void setCreateUser (String createUser){
        this.createUser = createUser;
    }
    public String getUpdateDate(){
        return updateDate;
    }
    public void setUpdateDate (String updateDate){
        this.updateDate = updateDate;
    }
    public String getUpdateUser(){
        return updateUser;
    }
    public void setUpdateUser (String updateUser){
        this.updateUser = updateUser;
    }
    public String getTenantId(){
        return tenantId;
    }
    public void setTenantId (String tenantId){
        this.tenantId = tenantId;
    }


}

