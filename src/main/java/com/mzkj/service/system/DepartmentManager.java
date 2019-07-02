package com.mzkj.service.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.system.DepartmentListVo;
import com.mzkj.vo.system.DepartmentQueryVo;
import com.mzkj.vo.system.DepartmentVo;

import java.util.List;

/** 
 * 说明： 部门管理接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
public interface DepartmentManager{

	/**新增
	 * @param departmentVo
	 * @throws Exception
	 */
	public DepartmentVo save(DepartmentVo departmentVo)throws Exception;
	
	/**删除
	 * @param DEPARTMENT_ID
	 * @throws Exception
	 */
	public void delete(String DEPARTMENT_ID)throws Exception;
	
	/**修改
	 * @param departmentVo
	 * @throws Exception
	 */
	public void edit(DepartmentVo departmentVo)throws Exception;
	
	/**列表
	 * @param departmentQueryVo
	 * @throws Exception
	 */
	public PageInfo<DepartmentQueryVo> list(DepartmentQueryVo departmentQueryVo)throws Exception;

    /**查询当前登录人所负责的部门层级列表
     * @param departmentListVo
     * @throws Exception
     */
    DepartmentListVo listHierarchy(DepartmentListVo departmentListVo) throws Exception;
}

