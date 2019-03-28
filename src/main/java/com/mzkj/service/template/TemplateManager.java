package com.mzkj.service.template;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.template.TemplateQueryVo;
import com.mzkj.vo.template.TemplateVo;

/** 
 * 说明： 薪资模板表接口
 * 创建人：CDCXH
 * 创建时间：2019-03-21
 * @version
 */
public interface TemplateManager{

	/**新增
	 * @param templateVo
	 * @throws Exception
	 */
	public TemplateVo save(TemplateVo templateVo)throws Exception;
	
	/**删除
	 * @param TEMPLATE_ID
	 * @throws Exception
	 */
	public void delete(String TEMPLATE_ID)throws Exception;
	
	/**修改
	 * @param templateVo
	 * @throws Exception
	 */
	public void edit(TemplateVo templateVo)throws Exception;
	
	/**列表
	 * @param templateVo
	 * @throws Exception
	 */
	public PageInfo<TemplateQueryVo> list(TemplateQueryVo templateVo)throws Exception;
	
}

