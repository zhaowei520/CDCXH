package com.mzkj.bean;

import com.mzkj.domain.Staff;
/**
 * 说明： 业务对象，与数据库操作
 * 创建人：CDCXH
 * 创建时间：2019-07-01
 * @version
 */
public class StaffBean extends Staff{
    //查询
    private String departmentId;

    @Override
    public String getDepartmentId() {
        return departmentId;
    }

    @Override
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}

