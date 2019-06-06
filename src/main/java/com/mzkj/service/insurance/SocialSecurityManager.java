package com.mzkj.service.insurance;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.vo.SocialSecurityQueryVo;
import com.mzkj.facade.vo.SocialSecurityVo;

/** 
 * 说明： 社保工单接口
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 * @version
 */
public interface SocialSecurityManager{

	/**新增
	 * @param socialSecurityVo
	 * @throws Exception
	 */
	public SocialSecurityVo save(SocialSecurityVo socialSecurityVo)throws Exception;
	
	/**删除
	 * @param socialSecurityId
	 * @throws Exception
	 */
	public void delete(String socialSecurityId)throws Exception;
	
	/**修改
	 * @param socialSecurityVo
	 * @throws Exception
	 */
	public void edit(SocialSecurityVo socialSecurityVo)throws Exception;

	/**
	 * 根据ID查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-13 15:40
	 */
	public SocialSecurityQueryVo findById(String  socialSecurityId)throws Exception;

	public SocialSecurityQueryVo findByCode(String  businessId)throws Exception;

	/**列表
	 * @param socialSecurityQueryVo
	 * @throws Exception
	 */
	public PageInfo<SocialSecurityQueryVo> list(SocialSecurityQueryVo socialSecurityQueryVo)throws Exception;
	
}

