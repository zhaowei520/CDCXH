package com.mzkj.service.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.vo.Result;
import com.mzkj.vo.system.StaffQueryVo;
import com.mzkj.vo.system.StaffVo;

import java.util.List;

/** 
 * 说明： 员工接口
 * 创建人：CDCXH
 * 创建时间：2019-07-01
 * @version
 */
public interface StaffManager{

	/**新增
	 * @param staffVo
	 * @throws Exception
	 */
	public StaffVo save(StaffVo staffVo)throws Exception;
	
	/**删除
	 * @param staffId
	 * @throws Exception
	 */
	public void delete(String staffId)throws Exception;
	
	/**修改
	 * @param staffVo
	 * @throws Exception
	 */
	public void edit(StaffVo staffVo)throws Exception;
	
	/**列表
	 * @param staffQueryVo
	 * @throws Exception
	 */
	public PageInfo<StaffQueryVo> list(StaffQueryVo staffQueryVo)throws Exception;

    /**列表
     * @param staffQueryVo
     * @throws Exception
     */
    public List<StaffQueryVo> listAllByDepartId(StaffQueryVo staffQueryVo);
}

