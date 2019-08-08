package com.mzkj.mapper.system;

import com.mzkj.bean.StaffBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 员工接口
 * 创建人：CDCXH
 * 创建时间：2019-07-01
 * @version
 */
@Mapper
public interface StaffMapper{

	/**新增
	 * @param staffBean
	 * @throws Exception
	 */
	public void save(StaffBean staffBean)throws Exception;
	
	/**删除
	 * @param staffId
	 * @throws Exception
	 */
	public void delete(String staffId)throws Exception;
	
	/**修改
	 * @param staffBean
	 * @throws Exception
	 */
	public void edit(StaffBean staffBean)throws Exception;
	
	/**列表
	 * @param staffBean
	 * @throws Exception
	 */
	public List<StaffBean> list(StaffBean staffBean)throws Exception;

    /**根据部门id查询员工列表
     * @param staffBean
     * @throws Exception
     */
    public List<StaffBean> listAllByDepartId(StaffBean staffBean);

    /**根据id查询员工
     * @param staffBean
     * @throws Exception
     */
    public StaffBean findOneById(StaffBean staffBean);

	/**根据username查询员工信息
	 * @param staffBean
	 * @throws Exception
	 */
	public StaffBean findOneByUserName(StaffBean staffBean);
}

