package com.mzkj.service.insurance.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.SocialSecurityBean;
import com.mzkj.domain.User;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.util.Const;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.DateUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.insurance.SocialSecurityQueryVo;
import com.mzkj.vo.insurance.SocialSecurityVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;

import com.mzkj.service.insurance.SocialSecurityManager;
import com.mzkj.mapper.insurance.SocialSecurityMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明： 社保工单
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 */
@Service("socialSecurityService")
public class SocialSecurityService implements SocialSecurityManager {

    @Autowired
    private SocialSecurityMapper socialSecurityMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增
     */
    @Override
    public SocialSecurityVo save(SocialSecurityVo socialSecurityVo) throws Exception {
        SocialSecurityBean socialSecurityBean = ConvertUtil.objectCopyParams(socialSecurityVo, SocialSecurityBean.class);
        if (StringUtils.isEmpty(socialSecurityBean.getTenantId())) {
            socialSecurityBean.setTenantId(Jurisdiction.getTenant());
        }
        if (StringUtils.isEmpty(socialSecurityBean.getCreateUser())) {
            socialSecurityBean.setCreateUser(Jurisdiction.getUsername());
        }
        socialSecurityBean.setCreateDate(DateUtil.getTime());
        socialSecurityMapper.save(socialSecurityBean);
        socialSecurityVo = ConvertUtil.objectCopyParams(socialSecurityBean, SocialSecurityVo.class);
        return socialSecurityVo;
    }

    /**
     * 删除
     */
    @Override
    public void delete(String socialSecurityId) throws Exception {
        socialSecurityMapper.delete(socialSecurityId);
    }

    /**
     * 修改
     */
    @Override
    public void edit(SocialSecurityVo socialSecurityVo) throws Exception {
        SocialSecurityBean socialSecurityBean = ConvertUtil.objectCopyParams(socialSecurityVo, SocialSecurityBean.class);
        socialSecurityBean.setUpdateUser(Jurisdiction.getUsername());
        socialSecurityBean.setUpdateDate(DateUtil.getTime());
        socialSecurityMapper.edit(socialSecurityBean);
    }

    /**
     * 根据ID查询
     * return
     * Author luosc
     * param
     * Date 2019-05-13 15:41
     */
    @Override
    public SocialSecurityQueryVo findById(String socialSecurityId) throws Exception {
        SocialSecurityBean socialSecurityBean = socialSecurityMapper.findById(socialSecurityId);
        SocialSecurityQueryVo socialSecurityQueryVo = ConvertUtil.objectCopyParams(socialSecurityBean, SocialSecurityQueryVo.class);
        return socialSecurityQueryVo;
    }

    /**
     * 列表
     */
    @Override
    public PageInfo<SocialSecurityQueryVo> list(SocialSecurityQueryVo socialSecurityQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        SocialSecurityBean socialSecurityBean = PageUtil.startPageAndObjectCopyParams(socialSecurityQueryVo, SocialSecurityBean.class);
        //设置租户ID
        socialSecurityBean.setTenantId(Jurisdiction.getTenant());
        List<SocialSecurityBean> socialSecurityPageBean = socialSecurityMapper.list(socialSecurityBean);
        PageInfo<SocialSecurityQueryVo> pageInfo = new PageInfo(socialSecurityPageBean);
        //DO转VO
        List<SocialSecurityQueryVo> list = new ArrayList<>();
        for (SocialSecurityBean securityBean : socialSecurityPageBean) {
            SocialSecurityQueryVo securityQueryVo = ConvertUtil.objectCopyParams(securityBean, SocialSecurityQueryVo.class);
            //签单人username转name
            if (!StringUtils.isEmpty(securityQueryVo.getSaler())) {
                User user=userMapper.findByUsername(securityQueryVo.getSaler());
                if (null!=user&&!StringUtils.isEmpty(user.getName())) {
                    securityQueryVo.setSaler(user.getName());
                }
            }
            list.add(securityQueryVo);
        }
        return pageInfo;
    }

}

