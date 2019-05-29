package com.mzkj.domain;

import java.io.Serializable;

/**
 * 说明： 实体类，与对应表字段相同
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 */
public class HousingFund implements Serializable {
    private String housingFundId;
    private String companyName;
    private String registeredArea;
    private String customerPhone;
    private String address;
    private String customer;
    private String fees;
    private String saler;
    private String buyStartMonth;
    private String openAccount;
    private String backAccount;
    private String buyType;
    private String personalInformation;
    private String identityCardNumber;
    private String createUser;
    private String createDate;
    private String updateUser;
    private String updateDate;
    private String tenantId;
    private String dataPrivilegeCode;
    private String procInstId;
    private String businessId;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getHousingFundId() {
        return housingFundId;
    }

    public void setHousingFundId(String housingFundId) {
        this.housingFundId = housingFundId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegisteredArea() {
        return registeredArea;
    }

    public void setRegisteredArea(String registeredArea) {
        this.registeredArea = registeredArea;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getSaler() {
        return saler;
    }

    public void setSaler(String saler) {
        this.saler = saler;
    }

    public String getBuyStartMonth() {
        return buyStartMonth;
    }

    public void setBuyStartMonth(String buyStartMonth) {
        this.buyStartMonth = buyStartMonth;
    }

    public String getOpenAccount() {
        return openAccount;
    }

    public void setOpenAccount(String openAccount) {
        this.openAccount = openAccount;
    }

    public String getBackAccount() {
        return backAccount;
    }

    public void setBackAccount(String backAccount) {
        this.backAccount = backAccount;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public String getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(String personalInformation) {
        this.personalInformation = personalInformation;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
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

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDataPrivilegeCode() {
        return dataPrivilegeCode;
    }

    public void setDataPrivilegeCode(String dataPrivilegeCode) {
        this.dataPrivilegeCode = dataPrivilegeCode;
    }


}

