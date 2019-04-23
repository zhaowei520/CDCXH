package com.mzkj.mapper.companyOriginal;

import com.mzkj.bean.OriginalBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 公司原件详情接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@Mapper
public interface OriginalMapper{

	/**新增
	 * @param originalBean
	 * @throws Exception
	 */
	public void save(OriginalBean originalBean)throws Exception;
	
	/**删除
	 * @param ORIGINAL_ID
	 * @throws Exception
	 */
	public void delete(String ORIGINAL_ID)throws Exception;
	
	/**修改
	 * @param originalBean
	 * @throws Exception
	 */
	public void edit(OriginalBean originalBean)throws Exception;
	
	/**列表
	 * @param originalBean
	 * @throws Exception
	 */
	public List<OriginalBean> list(OriginalBean originalBean)throws Exception;

	/**
	 * 根据公司信息ID查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-23 13:59
	 */
	public List<OriginalBean> findByCompanyInformationId(String companyInformationId) throws Exception;
	
}

