package com.mzkj.domain;

import java.io.Serializable;

/**
 * @Description: 作用
 * @Author: zw
 * @Date: 2019/3/26 17:21
 * @Version: 1.0
 */
public class Template implements Serializable {
    String TEMPLATE_ID;
    String USER_ID;
    String TEMPLATE_DATE;
    String TEMPLATE_DATETIME;
    String TEMPLATE_RADIO;
    String TEMPLATE_CHECKBOX;
    String TEMPLATE_SELECT;
    String TEMPLATE_TEXTAREA;
    String FILE_URL;
    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getTEMPLATE_DATE() {
        return TEMPLATE_DATE;
    }

    public void setTEMPLATE_DATE(String TEMPLATE_DATE) {
        this.TEMPLATE_DATE = TEMPLATE_DATE;
    }

    public String getTEMPLATE_DATETIME() {
        return TEMPLATE_DATETIME;
    }

    public void setTEMPLATE_DATETIME(String TEMPLATE_DATETIME) {
        this.TEMPLATE_DATETIME = TEMPLATE_DATETIME;
    }

    public String getTEMPLATE_RADIO() {
        return TEMPLATE_RADIO;
    }

    public void setTEMPLATE_RADIO(String TEMPLATE_RADIO) {
        this.TEMPLATE_RADIO = TEMPLATE_RADIO;
    }

    public String getTEMPLATE_CHECKBOX() {
        return TEMPLATE_CHECKBOX;
    }

    public void setTEMPLATE_CHECKBOX(String TEMPLATE_CHECKBOX) {
        this.TEMPLATE_CHECKBOX = TEMPLATE_CHECKBOX;
    }

    public String getTEMPLATE_SELECT() {
        return TEMPLATE_SELECT;
    }

    public void setTEMPLATE_SELECT(String TEMPLATE_SELECT) {
        this.TEMPLATE_SELECT = TEMPLATE_SELECT;
    }

    public String getTEMPLATE_TEXTAREA() {
        return TEMPLATE_TEXTAREA;
    }

    public void setTEMPLATE_TEXTAREA(String TEMPLATE_TEXTAREA) {
        this.TEMPLATE_TEXTAREA = TEMPLATE_TEXTAREA;
    }

    public String getFILE_URL() {
        return FILE_URL;
    }

    public void setFILE_URL(String FILE_URL) {
        this.FILE_URL = FILE_URL;
    }
}
