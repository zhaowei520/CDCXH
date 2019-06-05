package com.mzkj.service.general.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.GeneralContractBean;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.DateUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.general.GeneralContractQueryVo;
import com.mzkj.vo.general.GeneralContractVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import com.mzkj.service.general.GeneralContractManager;
import com.mzkj.mapper.general.GeneralContractMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明： 其它合同管理
 * 创建人：CDCXH
 * 创建时间：2019-06-03
 * @version
 */
@Service("generalContractService")
public class GeneralContractService implements GeneralContractManager{

    @Autowired
    private GeneralContractMapper generalContractMapper;

	/**新增
	 * @param generalContractVo
	 * @throws Exception
	 */
    @Override
	public GeneralContractVo save(GeneralContractVo generalContractVo)throws Exception{
        GeneralContractBean generalContractBean = ConvertUtil.objectCopyParams(generalContractVo, GeneralContractBean.class);
		generalContractBean.setTenantId(Jurisdiction.getTenant());
		generalContractBean.setCreateUser(Jurisdiction.getUsername());
		generalContractBean.setCreateDate(DateUtil.getTime());
        generalContractMapper.save(generalContractBean);
        generalContractVo = ConvertUtil.objectCopyParams(generalContractBean, GeneralContractVo.class);
        return generalContractVo;
	}

	/**
	 * 根据ID查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-06-03 14:59
	 */
	@Override
	public GeneralContractQueryVo findById(String generalContractId) throws Exception {
		GeneralContractBean generalContractBean= generalContractMapper.findById(generalContractId);
		GeneralContractQueryVo generalContractQueryVo = ConvertUtil.objectCopyParams(generalContractBean, GeneralContractQueryVo.class);
		return generalContractQueryVo;
	}

	@Override
	public GeneralContractQueryVo findByBusinessId(String businessId) throws Exception {
		GeneralContractBean generalContractBean= generalContractMapper.findByBusinessId(businessId);
		GeneralContractQueryVo generalContractQueryVo = ConvertUtil.objectCopyParams(generalContractBean, GeneralContractQueryVo.class);
		return generalContractQueryVo;
	}

	/**删除
	 * @param generalContractId
	 * @throws Exception
	 */
    @Override
	public void delete(String generalContractId)throws Exception{
        generalContractMapper.delete(generalContractId);
	}

	/**修改
	 * @param generalContractVo
	 * @throws Exception
	 */
    @Override
	public void edit(GeneralContractVo generalContractVo)throws Exception{
        GeneralContractBean generalContractBean = ConvertUtil.objectCopyParams(generalContractVo, GeneralContractBean.class);
		generalContractBean.setUpdateUser(Jurisdiction.getUsername());
		generalContractBean.setUpdateDate(DateUtil.getTime());
        generalContractMapper.edit(generalContractBean);
	}

	/**列表
	 * @param generalContractQueryVo
	 * @throws Exception
	 */
    @Override
	public PageInfo<GeneralContractQueryVo> list(GeneralContractQueryVo generalContractQueryVo)throws Exception{
        //将vo转DO并将分页信息传到pageHelper
        GeneralContractBean generalContractBean = PageUtil.startPageAndObjectCopyParams(generalContractQueryVo, GeneralContractBean.class);
        //设置租户ID
        generalContractBean.setTenantId(Jurisdiction.getTenant());
        List<GeneralContractBean> generalContractPageBean = generalContractMapper.list(generalContractBean);
		PageInfo<GeneralContractQueryVo> pageInfo = new PageInfo(generalContractPageBean);
        //DO转VO
		List<GeneralContractQueryVo> list = new ArrayList<>();
		for (GeneralContractBean generalContractBean1 : generalContractPageBean) {
			GeneralContractQueryVo contractQueryVo = ConvertUtil.objectCopyParams(generalContractBean1, GeneralContractQueryVo.class);
			list.add(contractQueryVo);
		}
		pageInfo.setList(list);
         return pageInfo;
	}

}

