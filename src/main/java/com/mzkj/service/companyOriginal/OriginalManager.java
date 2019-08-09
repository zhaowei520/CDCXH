package com.mzkj.service.companyOriginal;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.vo.Result;
import com.mzkj.vo.companyOriginal.OriginalQueryVo;
import com.mzkj.vo.companyOriginal.OriginalVo;

import java.util.List;

/**
 * 说明： 公司原件详情接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
public interface OriginalManager {

    /**
     * 新增
     */
    public List<OriginalVo> save(List<OriginalVo>  originalVoList) throws Exception;

    /**
     * 根据ID查询
     * return
     * Author luosc
     * param
     * Date 2019-04-25 15:26
     */
    public OriginalQueryVo findById(OriginalQueryVo originalQueryVo) throws Exception;
    /**
     * 删除
     */
    public void delete(String ORIGINAL_ID) throws Exception;

    /**
     * 修改
     */
    public void edit(OriginalVo originalVo) throws Exception;

    /**
     * 借出
     * return
     * Author luosc
     * param
     * Date 2019-04-24 15:35
     */
    public void loanOut(OriginalVo originalVo) throws Exception;

    /**
     * 借入确认
     * return
     * Author luosc
     * param
     * Date 2019-04-24 15:35
     */
    public void loanOutConfirmed(OriginalVo originalVo) throws Exception;

    /**
     * 驳回
     * return
     * Author luosc
     * param
     * Date 2019-04-24 17:00
     */
    public void reject(OriginalVo originalVo) throws Exception;

    /**
     * 借入
     * return
     * Author luosc
     * param
     * Date 2019-04-24 17:21
     */
    public void loanIn(OriginalVo originalVo) throws Exception;
    /**
     * 列表
     */
    public PageInfo<OriginalQueryVo> list(OriginalQueryVo originalQueryVo) throws Exception;

    /**
     * 交接原价
     */
    public void handoverOriginal(String holder,String fromHolder,String originalIds) throws Exception;
}

