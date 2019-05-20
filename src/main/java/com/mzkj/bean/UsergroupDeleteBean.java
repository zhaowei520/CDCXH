package com.mzkj.bean;

import java.util.List;

public class UsergroupDeleteBean {

    private List<String> ids;
    private String updateUser;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
