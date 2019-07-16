package com.mzkj.service.process.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.CommerceBean;
import com.mzkj.bean.StaffBean;
import com.mzkj.convert.FollowUpConvert;
import com.mzkj.domain.MyPageInfo;
import com.mzkj.service.followUp.FollowUpManager;
import com.mzkj.service.system.StaffManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.CommerceQueryVo;
import com.mzkj.vo.process.CommerceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mzkj.service.process.CommerceManager;
import com.mzkj.mapper.process.CommerceMapper;

import java.util.List;
import java.util.Map;

/**
 * 说明： 工商注册
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@Service("commerceService")
public class CommerceService implements CommerceManager {

    @Autowired
    private CommerceMapper commerceMapper;

    @Autowired
    private FollowUpManager followUpService;

    @Autowired
    private StaffManager staffService;

    /**
     * 新增
     *
     * @param commerceVo
     * @throws Exception
     */
    @Override
    public CommerceVo save(CommerceVo commerceVo) throws Exception {
        CommerceBean commerceBean = ConvertUtil.objectCopyParams(commerceVo, CommerceBean.class);
        commerceMapper.save(commerceBean);
        commerceVo = ConvertUtil.objectCopyParams(commerceBean, CommerceVo.class);
        return commerceVo;
    }

    /**
     * 删除
     *
     * @param commerceId
     * @throws Exception
     */
    @Override
    public void delete(String commerceId) throws Exception {
        commerceMapper.delete(commerceId);
    }

    /**
     * 修改
     *
     * @param commerceVo
     * @throws Exception
     */
    @Override
    public void edit(CommerceVo commerceVo) throws Exception {
        CommerceBean commerceBean = ConvertUtil.objectCopyParams(commerceVo, CommerceBean.class);
        commerceMapper.edit(commerceBean);
    }

    /**
     * 列表
     *
     * @param commerceQueryVo
     * @throws Exception
     */
    @Override
    public PageInfo<CommerceQueryVo> list(CommerceQueryVo commerceQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        CommerceBean commerceBean =
            PageUtil.startPageAndObjectCopyParams(commerceQueryVo, CommerceBean.class);
        //设置租户ID
        commerceBean.setTenantId(Jurisdiction.getTenant());
        List<CommerceBean> commercePageBean = commerceMapper.list(commerceBean);
        //DO转VO
        List<CommerceQueryVo> commerceQueryVoList =
            (List<CommerceQueryVo>) ConvertUtil.castListObjectToTargetList(commercePageBean, CommerceQueryVo.class);
        PageInfo<CommerceQueryVo> pageInfo = new PageInfo<>(commerceQueryVoList);
        return pageInfo;
    }

    @Override
    public MyPageInfo<String, Integer, FollowUpQueryVo> listProcessByDepartmentId(FollowUpQueryVo followUpQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        CommerceBean commerceBean =
                FollowUpConvert.followUpVoToCommerceProcessBean(followUpQueryVo);
        //设置租户ID
        commerceBean.setTenantId(Jurisdiction.getTenant());
        PageHelper.startPage(followUpQueryVo);
        List<CommerceBean> commercePageBean = commerceMapper.listProcessByDepartmentId(commerceBean);
        MyPageInfo<String,Integer,FollowUpQueryVo> myPageInfo = new MyPageInfo(commercePageBean);
        //DO转VO
        List<FollowUpQueryVo> followUpQueryVoList =
                FollowUpConvert.commerceProcessBeanToFollowUpVo(commercePageBean);
        //统计所有工单数量
        Map<String, Integer> allProcessNumber = followUpService.countAllProcessNumberByDepartmentId(followUpQueryVo);
        myPageInfo.setMap(allProcessNumber);
        myPageInfo.setList(followUpQueryVoList);
        myPageInfo.setPageSize(followUpQueryVo.getPageSize());
        myPageInfo.setPageNum(followUpQueryVo.getPageNum());
        return myPageInfo;
    }

    @Override
    public Integer countProcessNumberByDepartment(FollowUpQueryVo followUpQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        CommerceBean commerceBean =
                FollowUpConvert.followUpVoToCommerceProcessBean(followUpQueryVo);
        //设置租户ID
        commerceBean.setTenantId(Jurisdiction.getTenant());
        //PageHelper.startPage(followUpQueryVo);
        Integer count = commerceMapper.countProcessNumberByDepartment(commerceBean);

        return count;
    }

    @Override
    public MyPageInfo<String,Integer,FollowUpQueryVo> listProcessByUser(FollowUpQueryVo followUpQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        CommerceBean commerceBean =
            FollowUpConvert.followUpVoToCommerceProcessBean(followUpQueryVo);
        //设置租户ID
        commerceBean.setTenantId(Jurisdiction.getTenant());
        if(StringUtils.isEmpty(followUpQueryVo.getStaffId())){
            commerceBean.setSaler(Jurisdiction.getU_name());
            followUpQueryVo.setStaffName(Jurisdiction.getU_name());
        }else{
            String staffId = followUpQueryVo.getStaffId();
            StaffBean staffBean = new StaffBean();
            staffBean.setStaffId(staffId);
            StaffBean oneById = staffService.findOneById(staffBean);
            commerceBean.setSaler(oneById.getName());
            followUpQueryVo.setStaffName(oneById.getName());
        }
        PageHelper.startPage(followUpQueryVo);
        List<CommerceBean> commercePageBean = commerceMapper.listProcessByUser(commerceBean);
        MyPageInfo<String,Integer,FollowUpQueryVo> myPageInfo = new MyPageInfo(commercePageBean);
        //DO转VO
        List<FollowUpQueryVo> followUpQueryVoList =
            FollowUpConvert.commerceProcessBeanToFollowUpVo(commercePageBean);
        //统计所有工单数量
        Map<String, Integer> allProcessNumber = followUpService.countAllProcessNumber(followUpQueryVo);
        myPageInfo.setMap(allProcessNumber);
        myPageInfo.setList(followUpQueryVoList);
        myPageInfo.setPageSize(followUpQueryVo.getPageSize());
        myPageInfo.setPageNum(followUpQueryVo.getPageNum());
        return myPageInfo;
    }

    @Override
    public Integer countProcessNumber(String name) throws Exception {
        return commerceMapper.countProcessNumber(Jurisdiction.getTenant(), name);
    }

}

