package com.mzkj.mapper.companyOriginal;

import com.mzkj.bean.OriginalProcessRecordsBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 原件流转记录接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@Mapper
public interface OriginalProcessRecordsMapper{

	/**新增
	 * @param originalprocessrecordsBean
	 * @throws Exception
	 */
	public void save(OriginalProcessRecordsBean originalprocessrecordsBean)throws Exception;
	
	/**删除
	 * @param ORIGINALPROCESSRECORDS_ID
	 * @throws Exception
	 */
	public void delete(String ORIGINALPROCESSRECORDS_ID)throws Exception;
	
	/**修改
	 * @param originalprocessrecordsBean
	 * @throws Exception
	 */
	public void edit(OriginalProcessRecordsBean originalprocessrecordsBean)throws Exception;
	
	/**列表
	 * @param originalprocessrecordsBean
	 * @throws Exception
	 */
	public List<OriginalProcessRecordsBean> list(OriginalProcessRecordsBean originalprocessrecordsBean)throws Exception;
	
}

