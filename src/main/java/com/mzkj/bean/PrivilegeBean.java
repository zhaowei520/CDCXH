package com.mzkj.bean;

import com.mzkj.domain.Privilege;

public class PrivilegeBean extends Privilege {
    private String oldUserId;
    private String newUserId;

    public String getOldUserId() {
        return oldUserId;
    }

    public void setOldUserId(String oldUserId) {
        this.oldUserId = oldUserId;
    }

    public String getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(String newUserId) {
        this.newUserId = newUserId;
    }
}
