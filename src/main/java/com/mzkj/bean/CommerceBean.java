package com.mzkj.bean;

import com.mzkj.domain.Commerce;

/**
 * 说明： 业务对象，与数据库操作
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
public class CommerceBean extends Commerce {

    //工作流act_ru_task表信息
    private String actAssignee;

    private String actName;

    private String excutionId;

    //模糊查询
    private String staffId;

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

    public String getExcutionId() {
        return excutionId;
    }

    public void setExcutionId(String excutionId) {
        this.excutionId = excutionId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}

