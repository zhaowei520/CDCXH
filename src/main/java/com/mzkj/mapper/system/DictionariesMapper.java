package com.mzkj.mapper.system;

import com.mzkj.bean.DictionariesBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 字典接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@Mapper
public interface DictionariesMapper{

	/**新增
	 * @param dictionariesBean
	 * @throws Exception
	 */
	public void save(DictionariesBean dictionariesBean)throws Exception;
	
	/**删除
	 * @param DICTIONARIES_ID
	 * @throws Exception
	 */
	public void delete(String DICTIONARIES_ID)throws Exception;
	
	/**修改
	 * @param dictionariesBean
	 * @throws Exception
	 */
	public void edit(DictionariesBean dictionariesBean)throws Exception;
	
	/**列表
	 * @param dictionariesBean
	 * @throws Exception
	 */
	public List<DictionariesBean> list(DictionariesBean dictionariesBean)throws Exception;
	
}

