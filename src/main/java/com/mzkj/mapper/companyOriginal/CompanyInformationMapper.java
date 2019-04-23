package com.mzkj.mapper.companyOriginal;

import com.mzkj.bean.CompanyInformationBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
}

