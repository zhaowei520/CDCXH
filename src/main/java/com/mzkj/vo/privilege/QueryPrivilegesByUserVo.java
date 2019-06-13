package com.mzkj.vo.privilege;

import com.mzkj.vo.BaseVo;

public class QueryPrivilegesByUserVo extends BaseVo {

    private String name;                    //名称
    private String type;
    private String subType;
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}