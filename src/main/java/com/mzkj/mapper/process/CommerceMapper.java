package com.mzkj.mapper.process;

import com.mzkj.bean.CommerceBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明： 工商注册接口
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@Mapper
public interface CommerceMapper {

    /**
     * 新增
     *
     * @param commerceBean
     * @throws Exception
     */
    public void save(CommerceBean commerceBean) throws Exception;

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
     * @param commerceBean
     * @throws Exception
     */
    public void edit(CommerceBean commerceBean) throws Exception;

    /**
     * 列表
     *
     * @param commerceBean
     * @throws Exception
     */
    public List<CommerceBean> list(CommerceBean commerceBean) throws Exception;


    /**
     * 根据创建人查询工商注册流程
     *
     * @param commerceBean
     * @throws Exception
     */
    public List<CommerceBean> listProcessByUser(CommerceBean commerceBean) throws Exception;

    /**
     * 根据创建人查询工商注册流程
     *
     * @param
     * @throws Exception
     */
    Integer countProcessNumber(@Param("tenantId") String tenant,@Param("saler") String u_name) throws Exception;
}

