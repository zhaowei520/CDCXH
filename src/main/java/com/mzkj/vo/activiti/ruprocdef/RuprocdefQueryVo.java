package com.mzkj.vo.activiti.ruprocdef;

import com.mzkj.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明： 工作流历史信息查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 */
@ApiModel(value = "Ruprocdef查询对象", description = "Ruprocdef查询")
public class RuprocdefQueryVo extends BaseVo {
    private String id;
    private String procInstId;
    private String executionId;
    private String taskId;
    private String actName;
    private String assignee;
    private Date startTime;
    private Date endTime;
    private Integer duration;
    private String tenantId;

    //节点内容和删除信息
    private String text;
    private String deleteReason;

    //用时(中文)
    private String durationCN;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public String getDurationCN() {
        return durationCN;
    }

    public void setDurationCN(String durationCN) {
        this.durationCN = durationCN;
    }
}

