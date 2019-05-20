package com.mzkj.bean;

import java.util.List;

public class UserOfUsergroupBean extends UserBean {
    private String usergroupId;
    private List<String> userIds;

    public String getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(String usergroupId) {
        this.usergroupId = usergroupId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
