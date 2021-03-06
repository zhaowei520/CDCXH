package com.mzkj.service.template.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.TemplateBean;
import com.mzkj.mapper.template.TemplateMapper;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.template.TemplateQueryVo;
import com.mzkj.vo.template.TemplateVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mzkj.service.template.TemplateManager;

import java.util.List;

/**
 * 说明： 薪资模板表
 * 创建人：CDCXH
 * 创建时间：2019-03-21
 */
@Service("templateService")
public class TemplateService implements TemplateManager {

    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 新增
     */
    @Override
    public TemplateVo save(TemplateVo templateVo) throws Exception {
        TemplateBean templateBean = ConvertUtil.objectCopyParams(templateVo, TemplateBean.class);
        templateMapper.save(templateBean);
        templateVo = ConvertUtil.objectCopyParams(templateBean, TemplateVo.class);
        return templateVo;
    }

    /**
     * 删除
     */
    @Override
    public void delete(String TEMPLATE_ID) throws Exception {
        templateMapper.delete(TEMPLATE_ID);
    }

    /**
     * 查看详情
     * return
     * Author luosc
     * param
     * Date 2019-04-03 9:27
     */
    @Override
    public TemplateVo findById(String TEMPLATE_ID) throws Exception {
        TemplateBean templateBean = templateMapper.findById(TEMPLATE_ID);
        return ConvertUtil.objectCopyParams(templateBean, TemplateVo.class);
    }

    /**
     * 修改
     */
    @Override
    public void edit(TemplateVo templateVo) throws Exception {
        String templateVoJson = JSON.toJSONString(templateVo);
        TemplateBean templateBean =
                JSON.parseObject(templateVoJson, new TypeReference<TemplateBean>() {
                });
        templateMapper.edit(templateBean);
    }

    /**
     * 列表
     */
    @Override
    public PageInfo<TemplateQueryVo> list(TemplateQueryVo testQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        TemplateBean templateBean = PageUtil.startPageAndObjectCopyParams(testQueryVo, TemplateBean.class);
        List<TemplateBean> templatePageBean = templateMapper.list(templateBean);
        PageInfo<TemplateBean> pageInfo = new PageInfo<>(templatePageBean);
        //将DO转vo
        PageInfo<TemplateQueryVo> templatePageVo = ConvertUtil.objectCopyParams(pageInfo, PageInfo.class);
        return templatePageVo;
    }

}

