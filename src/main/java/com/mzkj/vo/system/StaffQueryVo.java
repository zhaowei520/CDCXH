package com.mzkj.vo.system;

import com.mzkj.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 员工查询vo
 * 创建人：zw
 * 创建时间：2019-07-01
 */
@ApiModel(value = "Staff查询对象", description = "Staff查询")
public class StaffQueryVo extends BaseVo {
    private String staffId;
    private String name;

    //查询
    private String departmentId;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}

