package com.mzkj.bean;

import com.mzkj.domain.Tally;
/**
 * 说明： 业务对象，与数据库操作
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 * @version
 */
public class TallyBean extends Tally{
    //工作流act_ru_task表信息
    private String actAssignee;

    private String actName;

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

