package com.mzkj.service.companyOriginal.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.CompanyInformationBean;
import com.mzkj.util.Const;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.companyOriginal.CompanyInformationQueryVo;
import com.mzkj.vo.companyOriginal.CompanyInformationVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import com.mzkj.service.companyOriginal.CompanyInformationManager;
import com.mzkj.mapper.companyOriginal.CompanyInformationMapper;

import java.util.List;

/**
 * 说明： 原件管理客户信息
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 */
@Service("companyinformationService")
public class CompanyInformationService implements CompanyInformationManager {

    @Autowired
    private CompanyInformationMapper companyinformationMapper;

    /**
     * 新增
     */
    @Override
    public CompanyInformationVo save(CompanyInformationVo companyinformationVo) throws Exception {
        CompanyInformationBean companyinformationBean = ConvertUtil.objectCopyParams(companyinformationVo, CompanyInformationBean.class);
        //设置租户ID
        companyinformationBean.setTenantId(Jurisdiction.getTenant());
        companyinformationMapper.save(companyinformationBean);
        companyinformationVo = ConvertUtil.objectCopyParams(companyinformationBean, CompanyInformationVo.class);
        return companyinformationVo;
    }

    /**
     * 删除
     */
    @Override
    public void delete(String COMPANYINFORMATION_ID) throws Exception {
        companyinformationMapper.delete(COMPANYINFORMATION_ID);
    }

    /**
     * 修改
     */
    @Override
    public void edit(CompanyInformationVo companyinformationVo) throws Exception {
        CompanyInformationBean companyinformationBean = ConvertUtil.objectCopyParams(companyinformationVo, CompanyInformationBean.class);
        //设置租户ID
        companyinformationBean.setTenantId(Jurisdiction.getTenant());
        companyinformationMapper.edit(companyinformationBean);
    }

    /**
     * 列表
     */
    @Override
    public PageInfo<CompanyInformationQueryVo> list(CompanyInformationQueryVo companyinformationQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        CompanyInformationBean companyinformationBean = PageUtil.startPageAndObjectCopyParams(companyinformationQueryVo, CompanyInformationBean.class);
        //设置租户ID
        companyinformationBean.setTenantId(Jurisdiction.getTenant());
        List<CompanyInformationBean> companyinformationPageBean = companyinformationMapper.list(companyinformationBean);
        PageInfo<CompanyInformationBean> pageInfo = new PageInfo<>(companyinformationPageBean);
        //将DO转vo
        PageInfo<CompanyInformationQueryVo> companyinformationPageVo = ConvertUtil.objectCopyParams(pageInfo, PageInfo.class);
        return companyinformationPageVo;
    }

}

