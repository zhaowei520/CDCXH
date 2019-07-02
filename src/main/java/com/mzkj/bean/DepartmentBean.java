package com.mzkj.bean;

import com.mzkj.domain.Department;
import com.mzkj.vo.system.DepartmentListVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明： 业务对象，与数据库操作
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
public class DepartmentBean extends Department{

    private List<DepartmentBean> departmentBeans = new ArrayList();

    //查询
    private String username;

    public List<DepartmentBean> getDepartmentBeans() {
        return departmentBeans;
    }

    public void setDepartmentBeans(List<DepartmentBean> departmentBeans) {
        this.departmentBeans = departmentBeans;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

