package com.mzkj.mapper.menu;

import com.fh.entity.system.Menu;
import com.fh.util.PageData;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 说明： 菜单mapper接口
 * 创建人：zw
 * 创建时间：2019-03-26
 * @version
 */
@Mapper
public interface MenuMapper {

	public List<Menu> listSubMenuByParentId(PageData pd)throws Exception;
	
}

