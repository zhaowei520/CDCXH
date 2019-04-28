package com.mzkj.bean;

import com.mzkj.domain.Ruprocdef;

/**
 * 说明： 业务对象，与数据库操作
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 */
public class RuprocdefBean extends Ruprocdef {

    //节点内容和删除信息
    private String text;
    private String deleteReason;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }
}

