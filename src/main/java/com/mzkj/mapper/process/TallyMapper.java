package com.mzkj.mapper.process;

import com.mzkj.bean.CommerceBean;
import com.mzkj.bean.TallyBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 说明： 代理记账接口
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@Mapper
public interface TallyMapper {

    /**
     * 新增
     *
     * @param tallyBean
     * @throws Exception
     */
    public void save(TallyBean tallyBean) throws Exception;

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
     * @param tallyBean
     * @throws Exception
     */
    public void edit(TallyBean tallyBean) throws Exception;

    /**
     * 列表
     *
     * @param tallyBean
     * @throws Exception
     */
    public List<TallyBean> list(TallyBean tallyBean) throws Exception;

    /**
     * 根据部门Id查询代理记账流程
     *
     * @param tallyBean
     * @throws Exception
     */
    public List<TallyBean> listProcessByDepartmentId(TallyBean tallyBean) throws Exception;
    public Integer countProcessNumberByDepartment(TallyBean tallyBean) throws Exception;
    /**
     * 根据创建人查询代理记账流程
     *
     * @param tallyBean
     * @throws Exception
     */
    public List<TallyBean> listProcessByUser(TallyBean tallyBean) throws Exception;

    /**
     * 根据创建人查询代账流程数量
     *
     * @param
     * @throws Exception
     */
    Integer countProcessNumber(@Param("tenantId") String tenant, @Param("signPerson") String u_name) throws Exception;
}

