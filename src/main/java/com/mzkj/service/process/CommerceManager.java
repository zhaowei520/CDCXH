package com.mzkj.service.process;

import com.github.pagehelper.PageInfo;
import com.mzkj.domain.MyPageInfo;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.CommerceQueryVo;
import com.mzkj.vo.process.CommerceVo;

/**
 * 说明： 工商注册接口
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
public interface CommerceManager {

    /**
     * 新增
     *
     * @param commerceVo
     * @throws Exception
     */
    public CommerceVo save(CommerceVo commerceVo) throws Exception;

    /**
     * 删除
     *
     * @param commerceId
     * @throws Exception
     */
    public void delete(String commerceId) throws Exception;

    /**
     * 修改
     *
     * @param commerceVo
     * @throws Exception
     */
    public void edit(CommerceVo commerceVo) throws Exception;

    /**
     * 列表
     *
     * @param commerceQueryVo
     * @throws Exception
     */
    public PageInfo<CommerceQueryVo> list(CommerceQueryVo commerceQueryVo) throws Exception;


    /**
     * 根据创建人查询工商注册流程
     *
     * @param followUpQueryVo
     * @throws Exception
     */
    public MyPageInfo<String,Integer,FollowUpQueryVo> listProcessByUser(FollowUpQueryVo followUpQueryVo) throws Exception;

    /**
     * 根据创建人查询工商注册流程
     *
     * @param
     * @throws Exception
     */
    public Integer countProcessNumber() throws Exception;
}

