package com.mzkj.domain;

import java.io.Serializable;

/**
 * 说明： 实体类，与对应表字段相同
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 */
public class CompanyInformation implements Serializable {
    private String companyInformationId;
    private String companyName;
    private String customerUsername;
    private String remark;
    private String createDate;
    private String createUser;
    private String updateDate;
    private String updateUser;
    private String tenantId;

    public String getCompanyInformationId() {
        return companyInformationId;
    }

    public void setCompanyInformationId(String companyInformationId) {
        this.companyInformationId = companyInformationId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }


}

