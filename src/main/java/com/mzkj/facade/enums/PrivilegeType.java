package com.mzkj.facade.enums;

public enum PrivilegeType {

    客户信息("CUSTOMER_INFO"),
    工单("SHEET");

    private String code;

    PrivilegeType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
