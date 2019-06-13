package com.mzkj.facade.enums;

public enum SheetEnum {

    工商注册("COMMERCE");

    private String code;

    SheetEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
