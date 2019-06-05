package com.mzkj.service.general;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.general.GeneralContractQueryVo;
import com.mzkj.vo.general.GeneralContractVo;

/**
 * 说明： 其它合同管理接口
 * 创建人：CDCXH
 * 创建时间：2019-06-03
 */
public interface GeneralContractManager {

    /**
     * 新增
     */
    public GeneralContractVo save(GeneralContractVo generalContractVo) throws Exception;

    /**
     * return
     * Author luosc
     * param
     * Date 2019-06-03 14:48
     */
    public GeneralContractQueryVo findById(String generalContractId) throws Exception;

    public GeneralContractQueryVo findByBusinessId(String businessId) throws Exception;
    /**
     * 删除
     */
    public void delete(String generalContractId) throws Exception;

    /**
     * 修改
     */
    public void edit(GeneralContractVo generalContractVo) throws Exception;

    /**
     * 列表
     */
    public PageInfo<GeneralContractQueryVo> list(GeneralContractQueryVo generalContractQueryVo) throws Exception;

}

