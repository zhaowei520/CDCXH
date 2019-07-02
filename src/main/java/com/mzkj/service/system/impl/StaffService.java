package com.mzkj.service.system.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.StaffBean;
import com.mzkj.facade.vo.Result;
import com.mzkj.mapper.system.StaffMapper;
import com.mzkj.service.system.StaffManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.mzkj.vo.system.StaffQueryVo;
import com.mzkj.vo.system.StaffVo;


import java.util.List;

/**
 * 说明： 员工
 * 创建人：CDCXH
 * 创建时间：2019-07-01
 * @version
 */
@Service("staffService")
public class StaffService implements StaffManager {

    @Autowired
    private StaffMapper staffMapper;

	/**新增
	 * @param staffVo
	 * @throws Exception
	 */
    @Override
	public StaffVo save(StaffVo staffVo)throws Exception{
        StaffBean staffBean = ConvertUtil.objectCopyParams(staffVo, StaffBean.class);
        staffMapper.save(staffBean);
        staffVo = ConvertUtil.objectCopyParams(staffBean, StaffVo.class);
        return staffVo;
	}

	/**删除
	 * @param staffId
	 * @throws Exception
	 */
    @Override
	public void delete(String staffId)throws Exception{
        staffMapper.delete(staffId);
	}

	/**修改
	 * @param staffVo
	 * @throws Exception
	 */
    @Override
	public void edit(StaffVo staffVo)throws Exception{
        StaffBean staffBean = ConvertUtil.objectCopyParams(staffVo, StaffBean.class);
        staffMapper.edit(staffBean);
	}

	/**列表
	 * @param staffQueryVo
	 * @throws Exception
	 */
    @Override
	public PageInfo<StaffQueryVo> list(StaffQueryVo staffQueryVo)throws Exception{
        //将vo转DO并将分页信息传到pageHelper
        StaffBean staffBean = PageUtil.startPageAndObjectCopyParams(staffQueryVo, StaffBean.class);
        //设置租户ID
        staffBean.setTenantId(Jurisdiction.getTenant());
        List<StaffBean> staffPageBean = staffMapper.list(staffBean);
        //DO转VO
        List<StaffQueryVo> staffQueryVoList = (List<StaffQueryVo>) ConvertUtil.castListObjectToTargetList(staffPageBean,StaffQueryVo.class);
        PageInfo<StaffQueryVo> pageInfo = new PageInfo<>(staffQueryVoList);
         return pageInfo;
	}

    @Override
    public List<StaffQueryVo> listAllByDepartId(StaffQueryVo staffQueryVo) {
        StaffBean staffBean = ConvertUtil.objectCopyParams(staffQueryVo, StaffBean.class);
        List<StaffBean> staffBeans = staffMapper.listAllByDepartId(staffBean);
        //DO转VO
        List<StaffQueryVo> staffQueryVoList = (List<StaffQueryVo>) ConvertUtil.castListObjectToTargetList(staffBeans,StaffQueryVo.class);
        return staffQueryVoList;
    }

}

