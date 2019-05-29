package com.mzkj.facade.enums;

public enum PrivilegeSubType {


    工商注册("GONGSHANGZHUCE");

    private String code;

    PrivilegeSubType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
