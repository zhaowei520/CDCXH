package com.mzkj.vo.companyOriginal;

import com.mzkj.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 原件流转记录查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@ApiModel(value = "OriginalProcessRecords查询对象", description = "OriginalProcessRecords查询")
public class OriginalProcessRecordsQueryVo extends BaseVo {
    private String originalProcessRecordsId;
    private String originalId;
    private String originalFromTime;
    private String originalFromUsername;
    private String originalOutTime;
    private String originalOutUsername;
    private String remark;
    private String createDate;
    private String createUser;
    private String updateDate;
    private String updateUser;
    private String tenantId;

    public String getOriginalProcessRecordsId() {
        return originalProcessRecordsId;
    }

    public void setOriginalProcessRecordsId(String originalProcessRecordsId) {
        this.originalProcessRecordsId = originalProcessRecordsId;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getOriginalFromTime() {
        return originalFromTime;
    }

    public void setOriginalFromTime(String originalFromTime) {
        this.originalFromTime = originalFromTime;
    }

    public String getOriginalFromUsername() {
        return originalFromUsername;
    }

    public void setOriginalFromUsername(String originalFromUsername) {
        this.originalFromUsername = originalFromUsername;
    }

    public String getOriginalOutTime() {
        return originalOutTime;
    }

    public void setOriginalOutTime(String originalOutTime) {
        this.originalOutTime = originalOutTime;
    }

    public String getOriginalOutUsername() {
        return originalOutUsername;
    }

    public void setOriginalOutUsername(String originalOutUsername) {
        this.originalOutUsername = originalOutUsername;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }


}

