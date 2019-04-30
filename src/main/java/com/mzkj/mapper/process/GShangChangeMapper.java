package com.mzkj.mapper.process;

import com.mzkj.bean.GShangChangeBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明： 工商变更接口
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@Mapper
public interface GShangChangeMapper {

    /**
     * 新增
     *
     * @param gShangChangeBean
     * @throws Exception
     */
    public void save(GShangChangeBean gShangChangeBean) throws Exception;

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
     * @param gShangChangeBean
     * @throws Exception
     */
    public void edit(GShangChangeBean gShangChangeBean) throws Exception;

    /**
     * 列表
     *
     * @param gShangChangeBean
     * @throws Exception
     */
    public List<GShangChangeBean> list(GShangChangeBean gShangChangeBean) throws Exception;

    /**
     * 根据创建人查询工商变更流程
     *
     * @param gShangChangeBean
     * @throws Exception
     */
    List<GShangChangeBean> listProcessByUser(GShangChangeBean gShangChangeBean) throws Exception;

    /**
     * 根据创建人查询工商变更流程
     *
     * @param
     * @throws Exception
     */
    Integer countProcessNumber(@Param("tenantId") String tenant, @Param("signMan") String u_name) throws Exception;
}

