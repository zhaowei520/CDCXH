package com.mzkj.service.companyOriginal.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.OriginalProcessRecordsBean;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.companyOriginal.OriginalProcessRecordsQueryVo;
import com.mzkj.vo.companyOriginal.OriginalProcessRecordsVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import com.mzkj.service.companyOriginal.OriginalProcessRecordsManager;
import com.mzkj.mapper.companyOriginal.OriginalProcessRecordsMapper;

import java.util.List;

/**
 * 说明： 原件流转记录
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@Service("originalprocessrecordsService")
public class OriginalProcessRecordsService implements OriginalProcessRecordsManager {

    @Autowired
    private OriginalProcessRecordsMapper originalprocessrecordsMapper;

    /**
     * 新增
     */
    @Override
    public OriginalProcessRecordsVo save(OriginalProcessRecordsVo originalprocessrecordsVo) throws Exception {
        OriginalProcessRecordsBean originalprocessrecordsBean = ConvertUtil.objectCopyParams(originalprocessrecordsVo, OriginalProcessRecordsBean.class);
        originalprocessrecordsMapper.save(originalprocessrecordsBean);
        originalprocessrecordsVo = ConvertUtil.objectCopyParams(originalprocessrecordsBean, OriginalProcessRecordsVo.class);
        return originalprocessrecordsVo;
    }

    /**
     * 删除
     */
    @Override
    public void delete(String ORIGINALPROCESSRECORDS_ID) throws Exception {
        originalprocessrecordsMapper.delete(ORIGINALPROCESSRECORDS_ID);
    }

    /**
     * 修改
     */
    @Override
    public void edit(OriginalProcessRecordsVo originalprocessrecordsVo) throws Exception {
        OriginalProcessRecordsBean originalprocessrecordsBean = ConvertUtil.objectCopyParams(originalprocessrecordsVo, OriginalProcessRecordsBean.class);
        originalprocessrecordsMapper.edit(originalprocessrecordsBean);
    }

    /**
     * 列表
     */
    @Override
    public PageInfo<OriginalProcessRecordsQueryVo> list(OriginalProcessRecordsQueryVo originalprocessrecordsQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        OriginalProcessRecordsBean originalprocessrecordsBean = PageUtil.startPageAndObjectCopyParams(originalprocessrecordsQueryVo, OriginalProcessRecordsBean.class);
        List<OriginalProcessRecordsBean> originalprocessrecordsPageBean = originalprocessrecordsMapper.list(originalprocessrecordsBean);
        PageInfo<OriginalProcessRecordsBean> pageInfo = new PageInfo<>(originalprocessrecordsPageBean);
        //将DO转vo
        PageInfo<OriginalProcessRecordsQueryVo> originalprocessrecordsPageVo = ConvertUtil.objectCopyParams(pageInfo, PageInfo.class);
        return originalprocessrecordsPageVo;
    }

}

