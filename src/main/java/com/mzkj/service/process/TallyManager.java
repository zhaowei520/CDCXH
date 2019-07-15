package com.mzkj.service.process;

import com.github.pagehelper.PageInfo;
import com.mzkj.domain.MyPageInfo;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.TallyQueryVo;
import com.mzkj.vo.process.TallyVo;

/**
 * 说明： 代理记账接口
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
public interface TallyManager {

    /**
     * 新增
     *
     * @param tallyVo
     * @throws Exception
     */
    public TallyVo save(TallyVo tallyVo) throws Exception;

    /**
     * 删除
     *
     * @param tallyId
     * @throws Exception
     */
    public void delete(String tallyId) throws Exception;

    /**
     * 修改
     *
     * @param tallyVo
     * @throws Exception
     */
    public void edit(TallyVo tallyVo) throws Exception;

    /**
     * 列表
     *
     * @param tallyQueryVo
     * @throws Exception
     */
    public PageInfo<TallyQueryVo> list(TallyQueryVo tallyQueryVo) throws Exception;


    public MyPageInfo<String,Integer,FollowUpQueryVo> listProcessByDepartmentId(FollowUpQueryVo followUpQueryVo) throws Exception;

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
     * @param name
     * @throws Exception
     */
    public Integer countProcessNumber(String name) throws Exception;
}

