package com.mzkj.vo.general;

import com.mzkj.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 其它合同管理查询vo
 * 创建人：CDCXH
 * 创建时间：2019-06-03
 */
@ApiModel(value = "GeneralContract查询对象", description = "GeneralContract查询")
public class GeneralContractQueryVo extends BaseVo {
    private String generalContractId;
    private String contractCreateDate;
    private String companyName;
    private String registerArea;
    private String contractDate;
    private String hasContract;
    private String industry;
    private String drawerDepartment;
    private String drawer;
    private String signPerson;
    private String sourceOfCustomer;
    private String linkman;
    private String contractType;
    private Double contractPrice;
    private Double advancesReceived;
    private Double finalPayment;
    private String deadline;
    private String remark;
    private String tenantId;
    private String createUser;
    private String createDate;
    private String updateUser;
    private String updateDate;
    private String businessId;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getGeneralContractId() {
        return generalContractId;
    }

    public void setGeneralContractId(String generalContractId) {
        this.generalContractId = generalContractId;
    }

    public String getContractCreateDate() {
        return contractCreateDate;
    }

    public void setContractCreateDate(String contractCreateDate) {
        this.contractCreateDate = contractCreateDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getHasContract() {
        return hasContract;
    }

    public void setHasContract(String hasContract) {
        this.hasContract = hasContract;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDrawerDepartment() {
        return drawerDepartment;
    }

    public void setDrawerDepartment(String drawerDepartment) {
        this.drawerDepartment = drawerDepartment;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getSignPerson() {
        return signPerson;
    }

    public void setSignPerson(String signPerson) {
        this.signPerson = signPerson;
    }

    public String getSourceOfCustomer() {
        return sourceOfCustomer;
    }

    public void setSourceOfCustomer(String sourceOfCustomer) {
        this.sourceOfCustomer = sourceOfCustomer;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Double getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(Double contractPrice) {
        this.contractPrice = contractPrice;
    }

    public Double getAdvancesReceived() {
        return advancesReceived;
    }

    public void setAdvancesReceived(Double advancesReceived) {
        this.advancesReceived = advancesReceived;
    }

    public Double getFinalPayment() {
        return finalPayment;
    }

    public void setFinalPayment(Double finalPayment) {
        this.finalPayment = finalPayment;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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


}

