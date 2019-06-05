package com.mzkj.mapper.general;

import com.mzkj.bean.GeneralContractBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 其它合同管理接口
 * 创建人：CDCXH
 * 创建时间：2019-06-03
 * @version
 */
@Mapper
public interface GeneralContractMapper{

	/**新增
	 * @param generalContractBean
	 * @throws Exception
	 */
	public void save(GeneralContractBean generalContractBean)throws Exception;

	public GeneralContractBean findById(String generalContractId)throws Exception;


	public GeneralContractBean findByBusinessId(String businessId)throws Exception;
	/**删除
	 * @param generalContractId
	 * @throws Exception
	 */
	public void delete(String generalContractId)throws Exception;
	
	/**修改
	 * @param generalContractBean
	 * @throws Exception
	 */
	public void edit(GeneralContractBean generalContractBean)throws Exception;
	
	/**列表
	 * @param generalContractBean
	 * @throws Exception
	 */
	public List<GeneralContractBean> list(GeneralContractBean generalContractBean)throws Exception;
	
}

