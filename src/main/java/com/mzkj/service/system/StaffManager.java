package com.mzkj.service.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.StaffBean;
import com.mzkj.facade.vo.Result;
import com.mzkj.vo.system.StaffQueryVo;
import com.mzkj.vo.system.StaffVo;

import java.util.List;

/**
 * 说明： 员工接口
 * 创建人：CDCXH
 * 创建时间：2019-07-01
 */
public interface StaffManager {

    /**
     * 新增
     */
    public StaffVo save(StaffVo staffVo) throws Exception;

    /**
     * 删除
     */
    public void delete(String staffId) throws Exception;

    /**
     * 修改
     */
    public void edit(StaffVo staffVo) throws Exception;

    /**
     * 列表
     */
    public PageInfo<StaffQueryVo> list(StaffQueryVo staffQueryVo) throws Exception;

    /**
     * 根据部门id查询员工
     */
    public List<StaffQueryVo> listAllByDepartId(StaffQueryVo staffQueryVo) throws Exception;

    /**
     * 根据id查询员工
     */
    public StaffBean findOneById(StaffBean staffBean) throws Exception;
}

