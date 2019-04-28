package com.mzkj.mapper.system;

import com.mzkj.bean.DictionariesBean;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 说明： 字典接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@Mapper
public interface DictionariesMapper {

    /**
     * 新增
     */
    public void save(DictionariesBean dictionariesBean) throws Exception;

    /**
     * 删除
     */
    public void delete(String DICTIONARIES_ID) throws Exception;

    /**
     * 修改
     */
    public void edit(DictionariesBean dictionariesBean) throws Exception;

    /**
     * 列表
     */
    public List<DictionariesBean> list(DictionariesBean dictionariesBean) throws Exception;


    /**
     * 通过编码获取字典
     * return
     * Author luosc
     * param
     * Date 2019-04-24 11:13
     */
    public DictionariesBean findByBianma(DictionariesBean dictionariesBean) throws Exception;

    /**
     * 根据parentId 查询子集
     * return
     * Author luosc
     * param
     * Date 2019-04-24 11:23
     */
    public List<DictionariesBean> findChildListByParentId(String dictionariesId) throws Exception;

}

