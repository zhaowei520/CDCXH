package com.mzkj.service.companyOriginal;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.CompanyInformationBean;
import com.mzkj.bean.OriginalBean;
import com.mzkj.facade.vo.Result;
import com.mzkj.vo.companyOriginal.CompanyInformationQueryVo;
import com.mzkj.vo.companyOriginal.CompanyInformationVo;

import java.util.List;
import java.util.Map;

/** 
 * 说明： 原件管理客户信息接口
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 * @version
 */
public interface CompanyInformationManager{

	/**新增
	 * @param companyinformationVoList
	 * @throws Exception
	 */
	public Result save(List<CompanyInformationVo> companyinformationVoList)throws Exception;
	
	/**删除
	 * @param CompanyInformationId
	 * @throws Exception
	 */
	public void delete(String CompanyInformationId)throws Exception;
	
	/**修改
	 * @param companyinformationVo
	 * @throws Exception
	 */
	public void edit(CompanyInformationVo companyinformationVo)throws Exception;
	
	/**列表
	 * @param companyinformationQueryVo
	 * @throws Exception
	 */
	public PageInfo<CompanyInformationQueryVo> list(CompanyInformationQueryVo companyinformationQueryVo)throws Exception;


	/**
	 * 根据ID查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-23 8:47
	 */
	public CompanyInformationVo findById(String CompanyInformationId)throws Exception;

	public Map<String, Integer> holdCountAndToBeConfirmedCountAndOutgoingCountAndLoanInCount() throws Exception;

	/*
	* 从Excel数据里获取并执行批量更新或保存
	* */
	public StringBuffer readExcelBusinessDatasSaveOrUpdate(List<PageData> excelDatas) throws Exception;

	/*
	 * 从Excel数据里获取并执行批量更新或保存
	 * */
	public StringBuffer readExcelFinanceDatasSaveOrUpdate(List<PageData> excelDatas) throws Exception;
}

