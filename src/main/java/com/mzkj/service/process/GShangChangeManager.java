package com.mzkj.service.process;

import com.github.pagehelper.PageInfo;
import com.mzkj.domain.MyPageInfo;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.GShangChangeQueryVo;
import com.mzkj.vo.process.GShangChangeVo;

/**
 * 说明： 工商变更接口
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
public interface GShangChangeManager {

    /**
     * 新增
     *
     * @param gShangChangeVo
     * @throws Exception
     */
    public GShangChangeVo save(GShangChangeVo gShangChangeVo) throws Exception;

    /**
     * 删除
     *
     * @param gShangChangeId
     * @throws Exception
     */
    public void delete(String gShangChangeId) throws Exception;

    /**
     * 修改
     *
     * @param gShangChangeVo
     * @throws Exception
     */
    public void edit(GShangChangeVo gShangChangeVo) throws Exception;

    /**
     * 列表
     *
     * @param gShangChangeQueryVo
     * @throws Exception
     */
    public PageInfo<GShangChangeQueryVo> list(GShangChangeQueryVo gShangChangeQueryVo) throws Exception;

    public MyPageInfo<String,Integer,FollowUpQueryVo> listProcessByDepartmentId(FollowUpQueryVo followUpQueryVo) throws Exception;
    public Integer countProcessNumberByDepartment(FollowUpQueryVo followUpQueryVo) throws Exception;
    /**
     * 根据创建人查询工商变更流程
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

