package com.mzkj.service.template.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.TemplateBean;
import com.mzkj.mapper.template.TemplateMapper;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.Template.TemplateQueryVo;
import com.mzkj.vo.Template.TemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

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
     *
     * @param templateVo
     * @throws Exception
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
     *
     * @param TEMPLATE_ID
     * @throws Exception
     */
    @Override
    public void delete(String TEMPLATE_ID) throws Exception {
        templateMapper.delete(TEMPLATE_ID);
    }

    /**
     * 修改
     *
     * @param templateVo
     * @throws Exception
     */
    @Override
    public void templateVo(TemplateVo templateVo) throws Exception {
        String templateVoJson = JSON.toJSONString(templateVo);
        TemplateBean templateBean =
            JSON.parseObject(templateVoJson, new TypeReference<TemplateBean>() {
            });
        templateMapper.edit(templateBean);
    }

    /**
     * 列表
     *
     * @param templateVo
     * @throws Exception
     */
    @Override
    public PageInfo<TemplateQueryVo> list(TemplateQueryVo templateVo) throws Exception {
        PageInfo<TemplateQueryVo> templatePageVo = new PageInfo<>();
        PageUtil.startPage(templateVo.getPageIndex(), templateVo.getPageSise());
        TemplateBean templateBean = ConvertUtil.objectCopyParams(templateVo, TemplateBean.class);
        List<TemplateBean> templatePageBean = templateMapper.list(templateBean);
        PageInfo<TemplateBean> pageInfo = new PageInfo<>(templatePageBean);
        templatePageVo = ConvertUtil.objectCopyParams(pageInfo, templatePageVo.getClass());
        return templatePageVo;
    }

}

