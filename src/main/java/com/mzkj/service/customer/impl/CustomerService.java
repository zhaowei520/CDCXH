package com.mzkj.service.customer.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.CustomerBean;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.customer.CustomerQueryVo;
import com.mzkj.vo.customer.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import com.mzkj.service.customer.CustomerManager;
import com.mzkj.mapper.customer.CustomerMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明： 客户管理
 * 创建人：CDCXH
 * 创建时间：2019-05-15
 * @version
 */
@Service("customerService")
public class CustomerService implements CustomerManager{

    @Autowired
    private CustomerMapper customerMapper;

	/**新增
	 * @param customerVo
	 * @throws Exception
	 */
    @Override
	public CustomerVo save(CustomerVo customerVo)throws Exception{
        CustomerBean customerBean = ConvertUtil.objectCopyParams(customerVo, CustomerBean.class);
        customerMapper.save(customerBean);
        customerVo = ConvertUtil.objectCopyParams(customerBean, CustomerVo.class);
        return customerVo;
	}

	/**删除
	 * @param customerId
	 * @throws Exception
	 */
    @Override
	public void delete(String customerId)throws Exception{
        customerMapper.delete(customerId);
	}

	/**修改
	 * @param customerVo
	 * @throws Exception
	 */
    @Override
	public void edit(CustomerVo customerVo)throws Exception{
        CustomerBean customerBean = ConvertUtil.objectCopyParams(customerVo, CustomerBean.class);
        customerMapper.edit(customerBean);
	}

	/**列表
	 * @param customerQueryVo
	 * @throws Exception
	 */
    @Override
	public PageInfo<CustomerQueryVo> list(CustomerQueryVo customerQueryVo)throws Exception{
        //将vo转DO并将分页信息传到pageHelper
        CustomerBean customerBean = PageUtil.startPageAndObjectCopyParams(customerQueryVo, CustomerBean.class);
        //设置租户ID
        customerBean.setTenantId(Jurisdiction.getTenant());
        List<CustomerBean> customerPageBean = customerMapper.list(customerBean);
        //DO转VO
        List<CustomerQueryVo> customerQueryVoList = (List<CustomerQueryVo>) ConvertUtil.castListObjectToTargetList(customerPageBean,CustomerQueryVo.class);
        PageInfo<CustomerQueryVo> pageInfo = new PageInfo<>(customerQueryVoList);
         return pageInfo;
	}

	/**
	 * 查询所有客户列表
	 * return
	 * Author luosc
	 * param
	 * Date 2019-05-15 10:08
	 */
	@Override
	public List<CustomerQueryVo> listAll(CustomerQueryVo customerQueryVo) throws Exception {
		//将vo转DO并
		CustomerBean customerBean = ConvertUtil.objectCopyParams(customerQueryVo, CustomerBean.class);
		//设置租户ID
		customerBean.setTenantId(Jurisdiction.getTenant());
		List<CustomerBean> customerBeanList = customerMapper.listAll(customerBean);
		//DO转VO
		List<CustomerQueryVo> customerQueryVoList = new ArrayList<>();
		for (CustomerBean customer:customerBeanList
			 ) {
			CustomerQueryVo customerQueryVo1 = ConvertUtil.objectCopyParams(customer, CustomerQueryVo.class);
			customerQueryVoList.add(customerQueryVo1);
		}
		return customerQueryVoList;
	}

	/**
	 * 通过公司名称查询公司信息
	 * return
	 * Author dzw
	 * param
	 * Date 2019-08-6
	 */
	public List<CustomerBean> queryCompanyInformationByCompanyName(CustomerBean customerBean)throws Exception {
		return customerMapper.queryCompanyInformationByCompanyName(customerBean);
	};

	/**
	 * 查询激活和自己创建的公司信息
	 * return
	 * Author dzw
	 * param
	 * Date 2019-08-6
	 */
	public List<CustomerBean> queryAllCompanyInformation()throws Exception {
		CustomerBean customerBean = new CustomerBean();
		customerBean.setCreater(Jurisdiction.getUsername());
		customerBean.setState("1");//1为正式客户
		return customerMapper.queryAllCompanyInformation(customerBean);
	};
}

