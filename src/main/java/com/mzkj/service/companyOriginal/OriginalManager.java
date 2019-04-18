package com.mzkj.service.companyOriginal;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.companyOriginal.OriginalQueryVo;
import com.mzkj.vo.companyOriginal.OriginalVo;

/** 
 * 说明： 公司原件详情接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
public interface OriginalManager{

	/**新增
	 * @param originalVo
	 * @throws Exception
	 */
	public OriginalVo save(OriginalVo originalVo)throws Exception;
	
	/**删除
	 * @param ORIGINAL_ID
	 * @throws Exception
	 */
	public void delete(String ORIGINAL_ID)throws Exception;
	
	/**修改
	 * @param originalVo
	 * @throws Exception
	 */
	public void edit(OriginalVo originalVo)throws Exception;
	
	/**列表
	 * @param originalQueryVo
	 * @throws Exception
	 */
	public PageInfo<OriginalQueryVo> list(OriginalQueryVo originalQueryVo)throws Exception;
	
}

