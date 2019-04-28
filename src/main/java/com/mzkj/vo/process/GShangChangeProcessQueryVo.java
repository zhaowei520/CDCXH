package com.mzkj.vo.process;

import com.mzkj.vo.BaseVo;
import io.swagger.annotations.ApiModel;

/**
 * 说明： 工商变更查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@ApiModel(value = "GShangChange流程查询对象", description = "GShangChange流程查询")
public class GShangChangeProcessQueryVo extends BaseVo {
    private String gShangChangeId;
    private String companyName;
    private String signMan;
    private String signDate;
    private String note;
    private String businessTypes;
    private String createer;
    private String procInstId;
    private String tenantId;

    //工作流act_ru_task表信息
    private String actAssignee;
    private String actName;

    public String getgShangChangeId() {
        return gShangChangeId;
    }

    public void setgShangChangeId(String gShangChangeId) {
        this.gShangChangeId = gShangChangeId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSignMan() {
        return signMan;
    }

    public void setSignMan(String signMan) {
        this.signMan = signMan;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
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

    public String getCreateer() {
        return createer;
    }

    public void setCreateer(String createer) {
        this.createer = createer;
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

