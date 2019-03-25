package com.mzkj.service.template.impl;

import com.mzkj.mapper.template.TemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.util.PageData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.mzkj.service.template.TemplateManager;

/** 
 * 说明： 薪资模板表
 * 创建人：CDCXH
 * 创建时间：2019-03-21
 * @version
 */
@Service("templateService")
public class TemplateService implements TemplateManager{

    @Autowired
    private TemplateMapper templateMapper;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
    @Override
	public void save(PageData pd)throws Exception{
        templateMapper.save(pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
    @Override
	public void delete(PageData pd)throws Exception{
        templateMapper.delete(pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
    @Override
	public void edit(PageData pd)throws Exception{
        templateMapper.edit(pd);
	}
	
	/**列表
	 * @param pd
	 * @throws Exception
	 */
    @Override
	public Page<PageData> list(PageData pd)throws Exception{
        PageHelper.startPage(Integer.parseInt(pd.getString("pageNo")), Integer.parseInt(pd.getString("pageSize")));
		return templateMapper.list(pd);
	}
	
}

