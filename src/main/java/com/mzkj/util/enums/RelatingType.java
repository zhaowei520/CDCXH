package com.mzkj.util.enums;

public enum RelatingType {

    USERGROUP("usergroup"),
    USER("user"),
    PRIVILEGE("privilege");

    private String code;

    RelatingType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
