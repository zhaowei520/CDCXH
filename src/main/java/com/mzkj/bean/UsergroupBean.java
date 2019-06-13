package com.mzkj.bean;

import com.mzkj.domain.Usergroup;

import java.util.List;

public class UsergroupBean extends Usergroup {

    private List<String> usergroupIds;

    public List<String> getUsergroupIds() {
        return usergroupIds;
    }

    public void setUsergroupIds(List<String> usergroupIds) {
        this.usergroupIds = usergroupIds;
    }
}
