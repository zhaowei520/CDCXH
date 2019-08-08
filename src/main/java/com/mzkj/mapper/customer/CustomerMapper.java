package com.mzkj.mapper.customer;

import com.mzkj.bean.CustomerBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** 
 * 说明： 客户管理接口
 * 创建人：CDCXH
 * 创建时间：2019-05-15
 * @version
 */
@Mapper
public interface CustomerMapper{

	/**新增
	 * @param customerBean
	 * @throws Exception
	 */
	public void save(CustomerBean customerBean)throws Exception;
	
	/**删除
	 * @param customerId
	 * @throws Exception
	 */
	public void delete(String customerId)throws Exception;
	
	/**修改
	 * @param customerBean
	 * @throws Exception
	 */
	public void edit(CustomerBean customerBean)throws Exception;
	
	/**列表
	 * @param customerBean
	 * @throws Exception
	 */
	public List<CustomerBean> list(CustomerBean customerBean)throws Exception;

	/**
	 * 获取所有客户列表
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-15 10:06
	 */
	public List<CustomerBean> listAll(CustomerBean customerBean)throws Exception;

    /**
     * 通过公司名称查询公司信息
     * return
     * Author dzw
     * param
     * Date 2019-08-6
     */
    public List<CustomerBean> queryCompanyInformationByCompanyName(CustomerBean customerBean)throws Exception;
}

