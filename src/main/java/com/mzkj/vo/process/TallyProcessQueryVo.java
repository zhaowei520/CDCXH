package com.mzkj.vo.process;

import com.mzkj.vo.BaseVo;
import io.swagger.annotations.ApiModel;

/**
 * 说明： 代理记账查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@ApiModel(value = "Tally流程查询对象", description = "Tally流程查询")
public class TallyProcessQueryVo extends BaseVo {
    private String tallyId;
    private String companyName;
    private String legalRepresentative;
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

    public String getTallyId() {
        return tallyId;
    }

    public void setTallyId(String tallyId) {
        this.tallyId = tallyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
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
}

