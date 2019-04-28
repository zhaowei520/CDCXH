package com.mzkj.service.process.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.CommerceBean;
import com.mzkj.convert.FollowUpConvert;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.CommerceProcessQueryVo;
import com.mzkj.vo.process.CommerceQueryVo;
import com.mzkj.vo.process.CommerceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mzkj.service.process.CommerceManager;
import com.mzkj.mapper.process.CommerceMapper;

import java.util.List;

/**
 * 说明： 工商注册
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@Service("commerceService")
public class CommerceService implements CommerceManager {

    @Autowired
    private CommerceMapper commerceMapper;

    /**
     * 新增
     *
     * @param commerceVo
     * @throws Exception
     */
    @Override
    public CommerceVo save(CommerceVo commerceVo) throws Exception {
        CommerceBean commerceBean = ConvertUtil.objectCopyParams(commerceVo, CommerceBean.class);
        commerceMapper.save(commerceBean);
        commerceVo = ConvertUtil.objectCopyParams(commerceBean, CommerceVo.class);
        return commerceVo;
    }

    /**
     * 删除
     *
     * @param commerceId
     * @throws Exception
     */
    @Override
    public void delete(String commerceId) throws Exception {
        commerceMapper.delete(commerceId);
    }

    /**
     * 修改
     *
     * @param commerceVo
     * @throws Exception
     */
    @Override
    public void edit(CommerceVo commerceVo) throws Exception {
        CommerceBean commerceBean = ConvertUtil.objectCopyParams(commerceVo, CommerceBean.class);
        commerceMapper.edit(commerceBean);
    }

    /**
     * 列表
     *
     * @param commerceQueryVo
     * @throws Exception
     */
    @Override
    public PageInfo<CommerceQueryVo> list(CommerceQueryVo commerceQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        CommerceBean commerceBean =
            PageUtil.startPageAndObjectCopyParams(commerceQueryVo, CommerceBean.class);
        //设置租户ID
        commerceBean.setTenantId(Jurisdiction.getTenant());
        List<CommerceBean> commercePageBean = commerceMapper.list(commerceBean);
        //DO转VO
        List<CommerceQueryVo> commerceQueryVoList =
            (List<CommerceQueryVo>) ConvertUtil.castListObjectToTargetList(commercePageBean, CommerceQueryVo.class);
        PageInfo<CommerceQueryVo> pageInfo = new PageInfo<>(commerceQueryVoList);
        return pageInfo;
    }

    @Override
    public PageInfo<FollowUpQueryVo> listProcessByUser(FollowUpQueryVo followUpQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        CommerceBean commerceBean =
            FollowUpConvert.followUpVoToCommerceProcessBean(followUpQueryVo);
        //设置租户ID
        commerceBean.setTenantId(Jurisdiction.getTenant());
        commerceBean.setSaler(Jurisdiction.getU_name());
        List<CommerceBean> commercePageBean = commerceMapper.listProcessByUser(commerceBean);
        PageInfo<FollowUpQueryVo> pageInfo = new PageInfo(commercePageBean);
        //DO转VO
        List<FollowUpQueryVo> followUpQueryVoList =
            FollowUpConvert.commerceProcessBeanToFollowUpVo(commercePageBean);
        pageInfo.setList(followUpQueryVoList);
        return pageInfo;
    }

}

