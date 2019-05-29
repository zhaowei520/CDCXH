package com.mzkj.mapper.insurance;

import com.mzkj.bean.SocialSecurityBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 社保工单接口
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 * @version
 */
@Mapper
public interface SocialSecurityMapper{

	/**新增
	 * @param socialSecurityBean
	 * @throws Exception
	 */
	public void save(SocialSecurityBean socialSecurityBean)throws Exception;
	
	/**删除
	 * @param socialSecurityId
	 * @throws Exception
	 */
	public void delete(String socialSecurityId)throws Exception;
	
	/**修改
	 * @param socialSecurityBean
	 * @throws Exception
	 */
	public void edit(SocialSecurityBean socialSecurityBean)throws Exception;

	/**
	 * 根据id查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-13 15:42
	 */
	public SocialSecurityBean findById(String socialSecurityId)throws Exception;

	/**
	 * 根据businessId获取数据
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-28 18:26
	 */
	public SocialSecurityBean findByCode(String businessId)throws Exception;

	/**列表
	 * @param socialSecurityBean
	 * @throws Exception
	 */
	public List<SocialSecurityBean> list(SocialSecurityBean socialSecurityBean)throws Exception;
	
}

