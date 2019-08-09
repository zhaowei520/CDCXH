package com.mzkj.vo.companyOriginal;

import java.util.List;

/**
 * 说明： 原件管理客户信息新增/修改vo
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 */
public class CompanyInformationVo {
    private String companyInformationId;
    private String companyName;
    private String customerUsername;
    private String remark;
    private String createDate;
    private String createUser;
    private String updateDate;
    private String updateUser;
    private String tenantId;
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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

