package com.mzkj.service.template;

import com.github.pagehelper.Page;
import com.fh.util.PageData;

/** 
 * 说明： 薪资模板表接口
 * 创建人：CDCXH
 * 创建时间：2019-03-21
 * @version
 */
public interface TemplateManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param pd
	 * @throws Exception
	 */
	public Page<PageData> list(PageData pd)throws Exception;
	
}

