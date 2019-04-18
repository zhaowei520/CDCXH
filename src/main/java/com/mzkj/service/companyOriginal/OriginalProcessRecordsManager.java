package com.mzkj.service.companyOriginal;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.companyOriginal.OriginalProcessRecordsQueryVo;
import com.mzkj.vo.companyOriginal.OriginalProcessRecordsVo;

/** 
 * 说明： 原件流转记录接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
public interface OriginalProcessRecordsManager{

	/**新增
	 * @param originalprocessrecordsVo
	 * @throws Exception
	 */
	public OriginalProcessRecordsVo save(OriginalProcessRecordsVo originalprocessrecordsVo)throws Exception;
	
	/**删除
	 * @param ORIGINALPROCESSRECORDS_ID
	 * @throws Exception
	 */
	public void delete(String ORIGINALPROCESSRECORDS_ID)throws Exception;
	
	/**修改
	 * @param originalprocessrecordsVo
	 * @throws Exception
	 */
	public void edit(OriginalProcessRecordsVo originalprocessrecordsVo)throws Exception;
	
	/**列表
	 * @param originalprocessrecordsQueryVo
	 * @throws Exception
	 */
	public PageInfo<OriginalProcessRecordsQueryVo> list(OriginalProcessRecordsQueryVo originalprocessrecordsQueryVo)throws Exception;
	
}

