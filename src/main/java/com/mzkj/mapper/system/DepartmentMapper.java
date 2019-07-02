package com.mzkj.mapper.system;

import com.mzkj.bean.DepartmentBean;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 说明： 部门管理接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 新增
     */
    public void save(DepartmentBean departmentBean) throws Exception;

    /**
     * 删除
     */
    public void delete(String DEPARTMENT_ID) throws Exception;

    /**
     * 修改
     */
    public void edit(DepartmentBean departmentBean) throws Exception;

    /**
     * 列表
     */
    public List<DepartmentBean> list(DepartmentBean departmentBean) throws Exception;

    /**
     * 根据当前登录人查找负责人
     */
    public List<DepartmentBean> findByHeadMan(DepartmentBean departmentBean) throws Exception;

    /**
     * 根据父id查找部门信息
     */
    public List<DepartmentBean> listSubDepartmentByParentId(DepartmentBean departmentBean);
}

