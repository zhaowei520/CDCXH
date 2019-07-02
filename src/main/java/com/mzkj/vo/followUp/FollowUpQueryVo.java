package com.mzkj.vo.followUp;

import com.mzkj.vo.BaseVo;
import io.swagger.annotations.ApiModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明： 跟进查询vo
 * 创建人：zw
 * 创建时间：2019-04-22
 */
@ApiModel(value = "跟进流程查询对象", description = "跟进流程查询")
public class FollowUpQueryVo extends BaseVo {
    private String excutionId;
    private String companyName;
    private String customer;
    private String signPerson;
    private String contractDate;
    private String note;
    private String businessTypes;
    private String cUser;
    private String procInstId;
    private String tenantId;

    //工作流act_ru_task表信息
    private String actAssignee;
    private String actName;

    //模糊查询
    private String staffId;

    //所有工单统计数量
    private Map<String, Integer> allProcessNumber = new HashMap();

    public String getExcutionId() {
        return excutionId;
    }

    public void setExcutionId(String excutionId) {
        this.excutionId = excutionId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSignPerson() {
        return signPerson;
    }

    public void setSignPerson(String signPerson) {
        this.signPerson = signPerson;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(String businessTypes) {
        this.businessTypes = businessTypes;
    }

    public String getcUser() {
        return cUser;
    }

    public void setcUser(String cUser) {
        this.cUser = cUser;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getActAssignee() {
        return actAssignee;
    }

    public void setActAssignee(String actAssignee) {
        this.actAssignee = actAssignee;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public Map<String, Integer> getAllProcessNumber() {
        return allProcessNumber;
    }

    public void setAllProcessNumber(Map<String, Integer> allProcessNumber) {
        this.allProcessNumber = allProcessNumber;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}

