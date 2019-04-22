package com.mzkj.bean;

import com.mzkj.domain.CompanyInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明： 业务对象，与数据库操作
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 * @version
 */
public class CompanyInformationBean extends CompanyInformation{

    private List<OriginalBean> originalList;
    private String originalHolderDepartment;
    private String originalHolder;
    private String originalOutStatus;

    public List<OriginalBean> getOriginalList() {
        return originalList;
    }

    public void setOriginalList(List<OriginalBean> originalList) {
        this.originalList = originalList;
    }

    public String getOriginalHolderDepartment() {
        return originalHolderDepartment;
    }

    public void setOriginalHolderDepartment(String originalHolderDepartment) {
        this.originalHolderDepartment = originalHolderDepartment;
    }

    public String getOriginalHolder() {
        return originalHolder;
    }

    public void setOriginalHolder(String originalHolder) {
        this.originalHolder = originalHolder;
    }

    public String getOriginalOutStatus() {
        return originalOutStatus;
    }

    public void setOriginalOutStatus(String originalOutStatus) {
        this.originalOutStatus = originalOutStatus;
    }
}

