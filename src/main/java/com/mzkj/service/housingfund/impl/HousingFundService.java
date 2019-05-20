package com.mzkj.service.housingfund.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.HousingFundBean;
import com.mzkj.domain.User;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.DateUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.housingfund.HousingFundQueryVo;
import com.mzkj.vo.housingfund.HousingFundVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;

import com.mzkj.service.housingfund.HousingFundManager;
import com.mzkj.mapper.housingfund.HousingFundMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明： 公积金工单
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 * @version
 */
@Service("housingFundService")
public class HousingFundService implements HousingFundManager{

    @Autowired
    private HousingFundMapper housingFundMapper;

	@Autowired
	private UserMapper userMapper;

	/**新增
	 * @param housingFundVo
	 * @throws Exception
	 */
    @Override
	public HousingFundVo save(HousingFundVo housingFundVo)throws Exception{
        HousingFundBean housingFundBean = ConvertUtil.objectCopyParams(housingFundVo, HousingFundBean.class);
		housingFundBean.setTenantId(Jurisdiction.getTenant());
		housingFundBean.setCreateUser(Jurisdiction.getUsername());
		housingFundBean.setCreateDate(DateUtil.getTime());
        housingFundMapper.save(housingFundBean);
        housingFundVo = ConvertUtil.objectCopyParams(housingFundBean, HousingFundVo.class);
        return housingFundVo;
	}

	/**删除
	 * @param housingFundId
	 * @throws Exception
	 */
    @Override
	public void delete(String housingFundId)throws Exception{
        housingFundMapper.delete(housingFundId);
	}

	/**修改
	 * @param housingFundVo
	 * @throws Exception
	 */
    @Override
	public void edit(HousingFundVo housingFundVo)throws Exception{
        HousingFundBean housingFundBean = ConvertUtil.objectCopyParams(housingFundVo, HousingFundBean.class);
		housingFundBean.setUpdateDate(DateUtil.getTime());
		housingFundBean.setUpdateUser(Jurisdiction.getUsername());
        housingFundMapper.edit(housingFundBean);
	}

	/**
	 * 根据ID查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-13 16:16
	 */
	@Override
	public HousingFundQueryVo findById(String housingFundId) throws Exception {
		HousingFundBean housingFundBean = housingFundMapper.findById(housingFundId);
		return ConvertUtil.objectCopyParams(housingFundBean, HousingFundQueryVo.class);
	}

	/**列表
	 * @param housingFundQueryVo
	 * @throws Exception
	 */
    @Override
	public PageInfo<HousingFundQueryVo> list(HousingFundQueryVo housingFundQueryVo)throws Exception{
        //将vo转DO并将分页信息传到pageHelper
        HousingFundBean housingFundBean = PageUtil.startPageAndObjectCopyParams(housingFundQueryVo, HousingFundBean.class);
        //设置租户ID
        housingFundBean.setTenantId(Jurisdiction.getTenant());
        List<HousingFundBean> housingFundPageBean = housingFundMapper.list(housingFundBean);
		PageInfo<HousingFundQueryVo> pageInfo = new PageInfo(housingFundPageBean);
        //DO转VO
		List<HousingFundQueryVo> housingFundQueryVoList = new ArrayList<>();
		for (HousingFundBean housingFundBeanData:housingFundPageBean
			 ) {
			HousingFundQueryVo housingFundQueryVoData=ConvertUtil.objectCopyParams(housingFundBeanData, HousingFundQueryVo.class);
			//签单人username转name
			if (!StringUtils.isEmpty(housingFundQueryVoData.getSaler())) {
				User user=userMapper.findByUsername(housingFundQueryVoData.getSaler());
				if (null!=user&&!StringUtils.isEmpty(user.getName())) {
					housingFundQueryVoData.setSaler(user.getName());
				}
			}
			housingFundQueryVoList.add(housingFundQueryVoData);
		}
		pageInfo.setList(housingFundQueryVoList);
         return pageInfo;
	}

}

