package com.mzkj.mapper.activiti.ruprocdef;

import com.mzkj.bean.RuprocdefBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 说明： 工作流历史信息接口
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 */
@Mapper
public interface RuprocdefMapper {

    /**
     * 新增
     *
     * @param ruprocdefBean
     * @throws Exception
     */
    public void save(RuprocdefBean ruprocdefBean) throws Exception;

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
     * @param ruprocdefBean
     * @throws Exception
     */
    public void edit(RuprocdefBean ruprocdefBean) throws Exception;

    /**
     * 列表
     *
     * @param ruprocdefBean
     * @throws Exception
     */
    public List<RuprocdefBean> list(RuprocdefBean ruprocdefBean) throws Exception;

    /**
     * 查看流程记录信息
     *
     * @param ruprocdefBean
     * @throws Exception
     */
    public List<RuprocdefBean> viewProcess(RuprocdefBean ruprocdefBean) throws Exception;
}

