package com.mzkj.mapper.system;

import com.mzkj.bean.DepartmentBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 部门管理接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@Mapper
public interface DepartmentMapper{

	/**新增
	 * @param departmentBean
	 * @throws Exception
	 */
	public void save(DepartmentBean departmentBean)throws Exception;
	
	/**删除
	 * @param DEPARTMENT_ID
	 * @throws Exception
	 */
	public void delete(String DEPARTMENT_ID)throws Exception;
	
	/**修改
	 * @param departmentBean
	 * @throws Exception
	 */
	public void edit(DepartmentBean departmentBean)throws Exception;
	
	/**列表
	 * @param departmentBean
	 * @throws Exception
	 */
	public List<DepartmentBean> list(DepartmentBean departmentBean)throws Exception;
	
}

