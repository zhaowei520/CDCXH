package com.mzkj.vo.companyOriginal;

import com.mzkj.vo.BaseVo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 原件管理客户信息查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 */
@ApiModel(value = "CompanyInformation查询对象", description = "CompanyInformation查询")
public class CompanyInformationQueryVo extends BaseVo {
    private String companyInformationId;
    private String companyName;
    private String originalHolder;
    private String originalHolderDepartment;
    private String originalOutStatus;
    private List<OriginalQueryVo> originalList;
    private String originalListString;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOriginalListString() {
        return originalListString;
    }

    public void setOriginalListString(String originalListString) {
        this.originalListString = originalListString;
    }

    public String getCompanyInformationId() {
        return companyInformationId;
    }

    public void setCompanyInformationId(String companyInformationId) {
        this.companyInformationId = companyInformationId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOriginalHolder() {
        return originalHolder;
    }

    public void setOriginalHolder(String originalHolder) {
        this.originalHolder = originalHolder;
    }

    public String getOriginalHolderDepartment() {
        return originalHolderDepartment;
    }

    public void setOriginalHolderDepartment(String originalHolderDepartment) {
        this.originalHolderDepartment = originalHolderDepartment;
    }

    public String getOriginalOutStatus() {
        return originalOutStatus;
    }

    public void setOriginalOutStatus(String originalOutStatus) {
        this.originalOutStatus = originalOutStatus;
    }

    public List<OriginalQueryVo> getOriginalList() {
        return originalList;
    }

    public void setOriginalList(List<OriginalQueryVo> originalList) {
        this.originalList = originalList;
    }
}

