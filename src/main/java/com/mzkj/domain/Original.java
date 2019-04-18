package com.mzkj.domain;

import java.io.Serializable;

/**
 * 说明： 实体类，与对应表字段相同
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
public class Original implements Serializable{
private String originalId;
        private String companyInformationId;
        private String companyName;
        private String originalName;
        private String originalAmount;
        private String originalHolder;
        private String originalHolderDepartment;
        private String originalOutTo;
        private String originalHoldStatus;
        private String originalOutStatus;
        private String remark;
        private String createDate;
        private String createUser;
        private String updateDate;
        private String updateUser;
        private String tenantId;

    public String getOriginalId(){
        return originalId;
    }
    public void setOriginalId (String originalId){
        this.originalId = originalId;
    }

    public String getCompanyInformationId(){
        return companyInformationId;
    }
    public void setCompanyInformationId (String companyInformationId){
        this.companyInformationId = companyInformationId;
    }
    public String getCompanyName(){
        return companyName;
    }
    public void setCompanyName (String companyName){
        this.companyName = companyName;
    }
    public String getOriginalName(){
        return originalName;
    }
    public void setOriginalName (String originalName){
        this.originalName = originalName;
    }
    public String getOriginalAmount(){
        return originalAmount;
    }
    public void setOriginalAmount (String originalAmount){
        this.originalAmount = originalAmount;
    }
    public String getOriginalHolder(){
        return originalHolder;
    }
    public void setOriginalHolder (String originalHolder){
        this.originalHolder = originalHolder;
    }
    public String getOriginalHolderDepartment(){
        return originalHolderDepartment;
    }
    public void setOriginalHolderDepartment (String originalHolderDepartment){
        this.originalHolderDepartment = originalHolderDepartment;
    }
    public String getOriginalOutTo(){
        return originalOutTo;
    }
    public void setOriginalOutTo (String originalOutTo){
        this.originalOutTo = originalOutTo;
    }
    public String getOriginalHoldStatus(){
        return originalHoldStatus;
    }
    public void setOriginalHoldStatus (String originalHoldStatus){
        this.originalHoldStatus = originalHoldStatus;
    }
    public String getOriginalOutStatus(){
        return originalOutStatus;
    }
    public void setOriginalOutStatus (String originalOutStatus){
        this.originalOutStatus = originalOutStatus;
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

