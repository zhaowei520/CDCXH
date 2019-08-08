package com.mzkj.service.process.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.StaffBean;
import com.mzkj.bean.TallyBean;
import com.mzkj.convert.FollowUpConvert;
import com.mzkj.domain.MyPageInfo;
import com.mzkj.service.followUp.FollowUpManager;
import com.mzkj.service.system.StaffManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.TallyQueryVo;
import com.mzkj.vo.process.TallyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mzkj.service.process.TallyManager;
import com.mzkj.mapper.process.TallyMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 说明： 代理记账
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@Service("tallyService")
public class TallyService implements TallyManager {

    @Autowired
    private TallyMapper tallyMapper;

    @Autowired
    private FollowUpManager followUpService;

    @Autowired
    private StaffManager staffService;
    /**
     * 新增
     *
     * @param tallyVo
     * @throws Exception
     */
    @Override
    public TallyVo save(TallyVo tallyVo) throws Exception {
        TallyBean tallyBean = ConvertUtil.objectCopyParams(tallyVo, TallyBean.class);
        tallyMapper.save(tallyBean);
        tallyVo = ConvertUtil.objectCopyParams(tallyBean, TallyVo.class);
        return tallyVo;
    }

    /**
     * 删除
     *
     * @param tallyId
     * @throws Exception
     */
    @Override
    public void delete(String tallyId) throws Exception {
        tallyMapper.delete(tallyId);
    }

    /**
     * 修改
     *
     * @param tallyVo
     * @throws Exception
     */
    @Override
    public void edit(TallyVo tallyVo) throws Exception {
        TallyBean tallyBean = ConvertUtil.objectCopyParams(tallyVo, TallyBean.class);
        tallyMapper.edit(tallyBean);
    }

    /**
     * 列表
     *
     * @param tallyQueryVo
     * @throws Exception
     */
    @Override
    public PageInfo<TallyQueryVo> list(TallyQueryVo tallyQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        TallyBean tallyBean = PageUtil.startPageAndObjectCopyParams(tallyQueryVo, TallyBean.class);
        //设置租户ID
        tallyBean.setTenantId(Jurisdiction.getTenant());
        List<TallyBean> tallyPageBean = tallyMapper.list(tallyBean);
        //DO转VO
        List<TallyQueryVo> tallyQueryVoList =
            (List<TallyQueryVo>) ConvertUtil.castListObjectToTargetList(tallyPageBean, TallyQueryVo.class);
        PageInfo<TallyQueryVo> pageInfo = new PageInfo<>(tallyQueryVoList);
        return pageInfo;
    }

    @Override
    public MyPageInfo<String, Integer, FollowUpQueryVo> listProcessByDepartmentId(FollowUpQueryVo followUpQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        TallyBean tallyBean =
                FollowUpConvert.followUpVoToTallyProcessBean(followUpQueryVo);
        //设置租户ID
        tallyBean.setTenantId(Jurisdiction.getTenant());
        PageHelper.startPage(followUpQueryVo);
        List<TallyBean> tallyPageBean;
        //如果没有勾选部门，那么就查找当前登录人的数据
        if(!StringUtils.isEmpty(tallyBean.getDepartmentId())){
            tallyPageBean = tallyMapper.listProcessByDepartmentId(tallyBean);
        }else{
            tallyBean.setSignPerson(Jurisdiction.getU_name());
            tallyPageBean = tallyMapper.listProcessByUser(tallyBean);
        }
        MyPageInfo<String,Integer,FollowUpQueryVo> myPageInfo = new MyPageInfo(tallyPageBean);
        //DO转VO
        List<FollowUpQueryVo> followUpQueryVoList =
                FollowUpConvert.tallyProcessBeanToFollowUpVo(tallyPageBean);
        //统计所有工单数量
        Map<String, Integer> allProcessNumber;
        if(!StringUtils.isEmpty(tallyBean.getDepartmentId())){
            allProcessNumber = followUpService.countAllProcessNumberByDepartmentId(followUpQueryVo);
        }else{
            followUpQueryVo.setStaffName(Jurisdiction.getU_name());
            allProcessNumber = followUpService.countAllProcessNumber(followUpQueryVo);
        }
        myPageInfo.setMap(allProcessNumber);
        myPageInfo.setList(followUpQueryVoList);
        myPageInfo.setPageSize(followUpQueryVo.getPageSize());
        myPageInfo.setPageNum(followUpQueryVo.getPageNum());
        return myPageInfo;
    }

    @Override
    public Integer countProcessNumberByDepartment(FollowUpQueryVo followUpQueryVo) throws Exception {
        TallyBean tallyBean =
                FollowUpConvert.followUpVoToTallyProcessBean(followUpQueryVo);
        //设置租户ID
        tallyBean.setTenantId(Jurisdiction.getTenant());
        Integer count = tallyMapper.countProcessNumberByDepartment(tallyBean);
        return count;
    }

    @Override
    public MyPageInfo<String,Integer,FollowUpQueryVo> listProcessByUser(FollowUpQueryVo followUpQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        TallyBean tallyBean = FollowUpConvert.followUpVoToTallyProcessBean(followUpQueryVo);
        //设置租户ID
        tallyBean.setTenantId(Jurisdiction.getTenant());
        tallyBean.setSignPerson(Jurisdiction.getU_name());
        if(StringUtils.isEmpty(followUpQueryVo.getStaffId())){
            tallyBean.setSignPerson(Jurisdiction.getU_name());
            followUpQueryVo.setStaffName(Jurisdiction.getU_name());
        }else{
            String staffId = followUpQueryVo.getStaffId();
            StaffBean staffBean = new StaffBean();
            staffBean.setStaffId(staffId);
            StaffBean oneById = staffService.findOneById(staffBean);
            tallyBean.setSignPerson(oneById.getName());
            followUpQueryVo.setStaffName(oneById.getName());
        }
        PageHelper.startPage(followUpQueryVo);
        List<TallyBean> tallyBeanPageBean = tallyMapper.listProcessByUser(tallyBean);
        //DO转VO
        MyPageInfo<String,Integer,FollowUpQueryVo> myPageInfo = new MyPageInfo(tallyBeanPageBean);
        List<FollowUpQueryVo> followUpQueryVoList =
            FollowUpConvert.tallyProcessBeanToFollowUpVo(tallyBeanPageBean);
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
        return tallyMapper.countProcessNumber(Jurisdiction.getTenant(), name);
    }
}

