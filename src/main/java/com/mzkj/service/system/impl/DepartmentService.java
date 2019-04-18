package com.mzkj.service.system.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.DepartmentBean;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.system.DepartmentQueryVo;
import com.mzkj.vo.system.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import com.mzkj.service.system.DepartmentManager;
import com.mzkj.mapper.system.DepartmentMapper;

import java.util.List;

/** 
 * 说明： 部门管理
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@Service("departmentService")
public class DepartmentService implements DepartmentManager{

    @Autowired
    private DepartmentMapper departmentMapper;
	
	/**新增
	 * @param departmentVo
	 * @throws Exception
	 */
    @Override
	public DepartmentVo save(DepartmentVo departmentVo)throws Exception{
        DepartmentBean departmentBean = ConvertUtil.objectCopyParams(departmentVo, DepartmentBean.class);
        departmentMapper.save(departmentBean);
        departmentVo = ConvertUtil.objectCopyParams(departmentBean, DepartmentVo.class);
        return departmentVo;
	}
	
	/**删除
	 * @param DEPARTMENT_ID
	 * @throws Exception
	 */
    @Override
	public void delete(String DEPARTMENT_ID)throws Exception{
        departmentMapper.delete(DEPARTMENT_ID);
	}
	
	/**修改
	 * @param departmentVo
	 * @throws Exception
	 */
    @Override
	public void edit(DepartmentVo departmentVo)throws Exception{
        DepartmentBean departmentBean = ConvertUtil.objectCopyParams(departmentVo, DepartmentBean.class);
        departmentMapper.edit(departmentBean);
	}
	
	/**列表
	 * @param departmentQueryVo
	 * @throws Exception
	 */
    @Override
	public PageInfo<DepartmentQueryVo> list(DepartmentQueryVo departmentQueryVo)throws Exception{
        //将vo转DO并将分页信息传到pageHelper
        DepartmentBean departmentBean =PageUtil.startPageAndObjectCopyParams(departmentQueryVo,DepartmentBean.class);
        List<DepartmentBean> departmentPageBean = departmentMapper.list(departmentBean);
        PageInfo<DepartmentBean> pageInfo = new PageInfo<>(departmentPageBean);
        //将DO转vo
        PageInfo<DepartmentQueryVo> departmentPageVo = ConvertUtil.objectCopyParams(pageInfo, PageInfo.class);
		return departmentPageVo;
	}
	
}

