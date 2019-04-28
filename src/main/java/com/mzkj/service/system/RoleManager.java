package com.mzkj.service.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.system.RoleQueryVo;
import com.mzkj.vo.system.RoleVo;

/**
 * 说明： 系统角色接口
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 */
public interface RoleManager {

    /**
     * 新增
     *
     * @param roleVo
     * @throws Exception
     */
    public RoleVo save(RoleVo roleVo) throws Exception;

    /**
     * 删除
     *
     * @param roleId
     * @throws Exception
     */
    public void delete(String roleId) throws Exception;

    /**
     * 修改
     *
     * @param roleVo
     * @throws Exception
     */
    public void edit(RoleVo roleVo) throws Exception;

    /**
     * 列表
     *
     * @param roleQueryVo
     * @throws Exception
     */
    public PageInfo<RoleQueryVo> list(RoleQueryVo roleQueryVo) throws Exception;

    /**
     * 根据编码查询角色信息
     *
     * @param rnumber
     * @throws Exception
     */
    public RoleVo getRoleByRnumber(String rnumber) throws Exception;
}

