package com.mzkj.mapper.template;

import com.fh.util.PageData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.TemplateBean;
import com.mzkj.vo.Template.TemplateVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 薪资模板表接口
 * 创建人：CDCXH
 * 创建时间：2019-03-21
 * @version
 */
@Mapper
public interface TemplateMapper{

	/**新增
	 * @param templateBean
	 * @throws Exception
	 */
	public void save(TemplateBean templateBean)throws Exception;
	
	/**删除
	 * @param TEMPLATE_ID
	 * @throws Exception
	 */
	public void delete(String TEMPLATE_ID)throws Exception;
	
	/**修改
	 * @param templateBean
	 * @throws Exception
	 */
	public void edit(TemplateBean templateBean)throws Exception;
	
	/**列表
	 * @param templateBean
	 * @throws Exception
	 */
	public List<TemplateBean> list(TemplateBean templateBean)throws Exception;
	
}

