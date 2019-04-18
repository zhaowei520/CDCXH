package com.mzkj.service.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.system.DictionariesQueryVo;
import com.mzkj.vo.system.DictionariesVo;

/** 
 * 说明： 字典接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
public interface DictionariesManager{

	/**新增
	 * @param dictionariesVo
	 * @throws Exception
	 */
	public DictionariesVo save(DictionariesVo dictionariesVo)throws Exception;
	
	/**删除
	 * @param DICTIONARIES_ID
	 * @throws Exception
	 */
	public void delete(String DICTIONARIES_ID)throws Exception;
	
	/**修改
	 * @param dictionariesVo
	 * @throws Exception
	 */
	public void edit(DictionariesVo dictionariesVo)throws Exception;
	
	/**列表
	 * @param dictionariesQueryVo
	 * @throws Exception
	 */
	public PageInfo<DictionariesQueryVo> list(DictionariesQueryVo dictionariesQueryVo)throws Exception;
	
}

