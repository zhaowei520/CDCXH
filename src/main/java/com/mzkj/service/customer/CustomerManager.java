package com.mzkj.service.customer;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.customer.CustomerQueryVo;
import com.mzkj.vo.customer.CustomerVo;

import java.util.List;

/** 
 * 说明： 客户管理接口
 * 创建人：CDCXH
 * 创建时间：2019-05-15
 * @version
 */
public interface CustomerManager{

	/**新增
	 * @param customerVo
	 * @throws Exception
	 */
	public CustomerVo save(CustomerVo customerVo)throws Exception;
	
	/**删除
	 * @param customerId
	 * @throws Exception
	 */
	public void delete(String customerId)throws Exception;
	
	/**修改
	 * @param customerVo
	 * @throws Exception
	 */
	public void edit(CustomerVo customerVo)throws Exception;
	
	/**列表
	 * @param customerQueryVo
	 * @throws Exception
	 */
	public PageInfo<CustomerQueryVo> list(CustomerQueryVo customerQueryVo)throws Exception;

	/**
	 * 查询所有客户
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-15 9:59
	 */
	public List<CustomerQueryVo> listAll(CustomerQueryVo customerQueryVo)throws Exception;
	
}

