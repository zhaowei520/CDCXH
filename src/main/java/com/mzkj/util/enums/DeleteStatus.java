package com.mzkj.util.enums;

public enum DeleteStatus {

    NOT_YET("0"),
    YET("1");

    private String code;

    DeleteStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
