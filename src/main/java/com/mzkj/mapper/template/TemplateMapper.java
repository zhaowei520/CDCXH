package com.mzkj.mapper.template;

import com.mzkj.bean.TemplateBean;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 说明： 薪资模板表接口
 * 创建人：CDCXH
 * 创建时间：2019-03-21
 */
@Mapper
public interface TemplateMapper {

    /**
     * 新增
     */
    public void save(TemplateBean templateBean) throws Exception;

    /**
     * 删除
     */
    public void delete(String TEMPLATE_ID) throws Exception;

    /**
     * 查看详情
     * return
     * Author luosc
     * param
     * Date 2019-04-03 9:28
     */
    public TemplateBean findById(String TEMPLATE_ID) throws Exception;

    /**
     * 修改
     */
    public void edit(TemplateBean templateBean) throws Exception;

    /**
     * 列表
     */
    public List<TemplateBean> list(TemplateBean templateBean) throws Exception;

}

