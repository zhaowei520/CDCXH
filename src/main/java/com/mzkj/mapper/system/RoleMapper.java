package com.mzkj.mapper.system;

import com.mzkj.bean.RoleBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明： 系统角色接口
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 */
@Mapper
public interface RoleMapper {

    /**
     * 新增
     *
     * @param roleBean
     * @throws Exception
     */
    public void save(RoleBean roleBean) throws Exception;

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
     * @param roleBean
     * @throws Exception
     */
    public void edit(RoleBean roleBean) throws Exception;

    /**
     * 列表
     *
     * @param roleBean
     * @throws Exception
     */
    public List<RoleBean> list(RoleBean roleBean) throws Exception;

    /**
     * 根据角色编码和租户查询角色数据
     *
     * @param rnumber,tenant
     * @throws Exception
     */
    public RoleBean getRoleByRnumber(@Param("rnumber") String rnumber,@Param("tenantId") String tenantId);
}

