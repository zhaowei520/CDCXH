package com.mzkj.service.activiti.ruprocdef;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.activiti.ruprocdef.RuprocdefQueryVo;
import com.mzkj.vo.activiti.ruprocdef.RuprocdefVo;

import java.util.List;

/**
 * 说明： 工作流历史信息接口
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 */
public interface RuprocdefManager {

    /**
     * 新增
     *
     * @param ruprocdefVo
     * @throws Exception
     */
    public RuprocdefVo save(RuprocdefVo ruprocdefVo) throws Exception;

    /**
     * 删除
     *
     * @param ruprocdefId
     * @throws Exception
     */
    public void delete(String ruprocdefId) throws Exception;

    /**
     * 修改
     *
     * @param ruprocdefVo
     * @throws Exception
     */
    public void edit(RuprocdefVo ruprocdefVo) throws Exception;

    /**
     * 列表
     *
     * @param ruprocdefQueryVo
     * @throws Exception
     */
    public PageInfo<RuprocdefQueryVo> list(RuprocdefQueryVo ruprocdefQueryVo) throws Exception;

    /**
     * 查看当前流程信息
     *
     * @param ruprocdefQueryVo
     * @throws Exception
     */
    public List<RuprocdefQueryVo> viewProcess(RuprocdefQueryVo ruprocdefQueryVo) throws Exception;
}

