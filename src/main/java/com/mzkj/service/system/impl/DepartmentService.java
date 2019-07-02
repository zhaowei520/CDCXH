package com.mzkj.service.system.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.DepartmentBean;
import com.mzkj.convert.DepartmentConvert;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.system.DepartmentListVo;
import com.mzkj.vo.system.DepartmentQueryVo;
import com.mzkj.vo.system.DepartmentVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mzkj.service.system.DepartmentManager;
import com.mzkj.mapper.system.DepartmentMapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 说明： 部门管理
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@Service("departmentService")
public class DepartmentService implements DepartmentManager {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 新增
     */
    @Override
    public DepartmentVo save(DepartmentVo departmentVo) throws Exception {
        DepartmentBean departmentBean = ConvertUtil.objectCopyParams(departmentVo, DepartmentBean.class);
        departmentMapper.save(departmentBean);
        departmentVo = ConvertUtil.objectCopyParams(departmentBean, DepartmentVo.class);
        return departmentVo;
    }

    /**
     * 删除
     */
    @Override
    public void delete(String DEPARTMENT_ID) throws Exception {
        departmentMapper.delete(DEPARTMENT_ID);
    }

    /**
     * 修改
     */
    @Override
    public void edit(DepartmentVo departmentVo) throws Exception {
        DepartmentBean departmentBean = ConvertUtil.objectCopyParams(departmentVo, DepartmentBean.class);
        departmentMapper.edit(departmentBean);
    }

    /**
     * 列表
     */
    @Override
    public PageInfo<DepartmentQueryVo> list(DepartmentQueryVo departmentQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        DepartmentBean departmentBean = PageUtil.startPageAndObjectCopyParams(departmentQueryVo, DepartmentBean.class);
        List<DepartmentBean> departmentPageBean = departmentMapper.list(departmentBean);
        PageInfo<DepartmentBean> pageInfo = new PageInfo<>(departmentPageBean);
        //将DO转vo
        PageInfo<DepartmentQueryVo> departmentPageVo = ConvertUtil.objectCopyParams(pageInfo, PageInfo.class);
        return departmentPageVo;
    }

    @Override
    public DepartmentListVo listHierarchy(DepartmentListVo departmentListVo) throws Exception {
        DepartmentBean departmentBean =
                DepartmentConvert.departmentListVoToDepartmentBean(departmentListVo);
        //获取当前登录人，是否是部门负责人
        String username = Jurisdiction.getUsername();
        departmentBean.setUsername(username);
        departmentBean.setTenantId(Jurisdiction.getTenant());
        List<DepartmentBean> headMans = departmentMapper.findByHeadMan(departmentBean);
        DepartmentListVo newDepartmentListVo = new DepartmentListVo();
        if (headMans != null && headMans.size() > 0) {
            for (DepartmentBean headMan : headMans) {
                newDepartmentListVo = DepartmentConvert.departmentBeanToDepartmentVo(headMan);
                String departmentId = headMan.getDepartmentId();
                List<DepartmentListVo> departmentListVos = listAllDepartmentToSelect(departmentId);
                newDepartmentListVo.getNodes().addAll(departmentListVos);
            }
        }
        return newDepartmentListVo;
    }

    private List<DepartmentListVo> listAllDepartmentToSelect(String departmentId) {
        List<DepartmentBean> departmentList = this.listSubDepartmentByParentId(departmentId);
        List<DepartmentListVo> departmentVoList = DepartmentConvert.departmentBeanListToDepartmentVoList(departmentList);
        for (DepartmentListVo depar : departmentVoList) {
            depar.setNodes(listAllDepartmentToSelect(depar.getKey()));
        }
        return departmentVoList;
    }

    private DepartmentListVo[] listAllbyPd(String departmentId, DepartmentListVo departmentListVo) {
        DepartmentListVo[] arrayDep = new DepartmentListVo[2];
        DepartmentBean bean = new DepartmentBean();
        List<DepartmentBean> departmentList = this.listSubDepartmentByParentId(departmentId);
        bean.setDepartmentBeans(departmentList);
        DepartmentListVo newDepartmentListVo = DepartmentConvert.departmentBeanToDepartmentVo(bean);
        List<DepartmentListVo> departmentVoList = DepartmentConvert.departmentBeanListToDepartmentVoList(departmentList);
        departmentListVo.setNodes(departmentVoList);
        arrayDep[0] = departmentListVo;
        arrayDep[1] = newDepartmentListVo;
        return arrayDep;
    }

    private List<DepartmentBean> listSubDepartmentByParentId(String departmentId) {
        DepartmentBean departmentBean = new DepartmentBean();
        departmentBean.setTenantId(Jurisdiction.getTenant());
        departmentBean.setParentId(departmentId);
        return departmentMapper.listSubDepartmentByParentId(departmentBean);
    }

}

