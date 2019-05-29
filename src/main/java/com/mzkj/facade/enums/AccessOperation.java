package com.mzkj.facade.enums;

public enum AccessOperation {

    WRITE("write"),
    READ("read");

    private String code;

    AccessOperation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
