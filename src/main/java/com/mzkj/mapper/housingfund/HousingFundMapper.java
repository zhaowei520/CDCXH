package com.mzkj.mapper.housingfund;

import com.mzkj.bean.HousingFundBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 公积金工单接口
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 * @version
 */
@Mapper
public interface HousingFundMapper{

	/**新增
	 * @param housingFundBean
	 * @throws Exception
	 */
	public void save(HousingFundBean housingFundBean)throws Exception;
	
	/**删除
	 * @param housingFundId
	 * @throws Exception
	 */
	public void delete(String housingFundId)throws Exception;
	
	/**修改
	 * @param housingFundBean
	 * @throws Exception
	 */
	public void edit(HousingFundBean housingFundBean)throws Exception;
	/**
	 * 根据ID查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-13 16:00
	 */
	public HousingFundBean findById(String housingFundId)throws Exception;
	/**列表
	 * @param housingFundBean
	 * @throws Exception
	 */
	public List<HousingFundBean> list(HousingFundBean housingFundBean)throws Exception;
	
}

