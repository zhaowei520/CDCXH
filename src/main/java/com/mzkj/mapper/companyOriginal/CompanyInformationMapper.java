package com.mzkj.mapper.companyOriginal;

import com.mzkj.bean.CompanyInformationBean;
import com.mzkj.bean.OriginalBean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/** 
 * 说明： 原件管理客户信息接口
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 * @version
 */
@Mapper
public interface CompanyInformationMapper{

	/**新增
	 * @param companyinformationBean
	 * @throws Exception
	 */
	public void save(CompanyInformationBean companyinformationBean)throws Exception;
	
	/**删除
	 * @param COMPANYINFORMATION_ID
	 * @throws Exception
	 */
	public void delete(String COMPANYINFORMATION_ID)throws Exception;
	
	/**修改
	 * @param companyinformationBean
	 * @throws Exception
	 */
	public void edit(CompanyInformationBean companyinformationBean)throws Exception;
	
	/**列表
	 * @param companyinformationBean
	 * @throws Exception
	 */
	public List<CompanyInformationBean> list(CompanyInformationBean companyinformationBean)throws Exception;

	/**根据ID查询
	 * @param companyInformationId
	 * @throws Exception
	 */
	public CompanyInformationBean findById(String companyInformationId)throws Exception;

	/**根据公司名称查询公司信息
	 * @param companyInformation
	 * @throws Exception
	 */
	public CompanyInformationBean findCompanyInformationByCompanyName(CompanyInformationBean companyInformation)throws Exception;

	/**根据customerId与原件持有人查询出持有人所拥有的原件
	 * @param cutomerId,userName
	 * @throws Exception
	 */
	public List<OriginalBean> findOriginalNumberByCustomerIdAndHolder(@Param("customerId") String customerId, @Param("holder") String holder)throws Exception;

	/**
	 * 根据customerId 查询公司原件信息
	 * return
	 * Author luosc
	 * param
	 * Date 2019-08-09 14:48
	 */
	public CompanyInformationBean findCompanyInformationByCustomerId(String customerId);

}

