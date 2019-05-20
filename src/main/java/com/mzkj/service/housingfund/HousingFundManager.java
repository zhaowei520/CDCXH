package com.mzkj.service.housingfund;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.housingfund.HousingFundQueryVo;
import com.mzkj.vo.housingfund.HousingFundVo;

/** 
 * 说明： 公积金工单接口
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 * @version
 */
public interface HousingFundManager{

	/**新增
	 * @param housingFundVo
	 * @throws Exception
	 */
	public HousingFundVo save(HousingFundVo housingFundVo)throws Exception;
	
	/**删除
	 * @param housingFundId
	 * @throws Exception
	 */
	public void delete(String housingFundId)throws Exception;
	
	/**修改
	 * @param housingFundVo
	 * @throws Exception
	 */
	public void edit(HousingFundVo housingFundVo)throws Exception;

	/**
	 * 根据ID查询数据
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-13 15:58
	 */
	public HousingFundQueryVo findById(String housingFundId)throws Exception;
	/**列表
	 * @param housingFundQueryVo
	 * @throws Exception
	 */
	public PageInfo<HousingFundQueryVo> list(HousingFundQueryVo housingFundQueryVo)throws Exception;
	
}

