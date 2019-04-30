package com.mzkj.service.process.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.GShangChangeBean;
import com.mzkj.convert.FollowUpConvert;
import com.mzkj.domain.MyPageInfo;
import com.mzkj.service.followUp.FollowUpManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.GShangChangeProcessQueryVo;
import com.mzkj.vo.process.GShangChangeQueryVo;
import com.mzkj.vo.process.GShangChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mzkj.service.process.GShangChangeManager;
import com.mzkj.mapper.process.GShangChangeMapper;

import java.util.List;
import java.util.Map;

/**
 * 说明： 工商变更
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@Service("gShangChangeService")
public class GShangChangeService implements GShangChangeManager {

    @Autowired
    private GShangChangeMapper gShangChangeMapper;

    @Autowired
    private FollowUpManager followUpService;
    /**
     * 新增
     *
     * @param gShangChangeVo
     * @throws Exception
     */
    @Override
    public GShangChangeVo save(GShangChangeVo gShangChangeVo) throws Exception {
        GShangChangeBean gShangChangeBean =
            ConvertUtil.objectCopyParams(gShangChangeVo, GShangChangeBean.class);
        gShangChangeMapper.save(gShangChangeBean);
        gShangChangeVo = ConvertUtil.objectCopyParams(gShangChangeBean, GShangChangeVo.class);
        return gShangChangeVo;
    }

    /**
     * 删除
     *
     * @param gShangChangeId
     * @throws Exception
     */
    @Override
    public void delete(String gShangChangeId) throws Exception {
        gShangChangeMapper.delete(gShangChangeId);
    }

    /**
     * 修改
     *
     * @param gShangChangeVo
     * @throws Exception
     */
    @Override
    public void edit(GShangChangeVo gShangChangeVo) throws Exception {
        GShangChangeBean gShangChangeBean =
            ConvertUtil.objectCopyParams(gShangChangeVo, GShangChangeBean.class);
        gShangChangeMapper.edit(gShangChangeBean);
    }

    /**
     * 列表
     *
     * @param gShangChangeQueryVo
     * @throws Exception
     */
    @Override
    public PageInfo<GShangChangeQueryVo> list(GShangChangeQueryVo gShangChangeQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        GShangChangeBean gShangChangeBean =
            PageUtil.startPageAndObjectCopyParams(gShangChangeQueryVo, GShangChangeBean.class);
        //设置租户ID
        gShangChangeBean.setTenantId(Jurisdiction.getTenant());
        List<GShangChangeBean> gShangChangePageBean = gShangChangeMapper.list(gShangChangeBean);
        //DO转VO
        List<GShangChangeQueryVo> gShangChangeQueryVoList =
            (List<GShangChangeQueryVo>) ConvertUtil.castListObjectToTargetList(gShangChangePageBean, GShangChangeQueryVo.class);
        PageInfo<GShangChangeQueryVo> pageInfo = new PageInfo<>(gShangChangeQueryVoList);
        return pageInfo;
    }

    @Override
    public MyPageInfo<String,Integer,FollowUpQueryVo> listProcessByUser(FollowUpQueryVo followUpQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        GShangChangeBean gShangChangeBean =
            FollowUpConvert.followUpVoToGShangChangeProcessBean(followUpQueryVo);
        //设置租户ID
        gShangChangeBean.setTenantId(Jurisdiction.getTenant());
        gShangChangeBean.setSignMan(Jurisdiction.getU_name());
        List<GShangChangeBean> gShangChangeBeanPageBean =
            gShangChangeMapper.listProcessByUser(gShangChangeBean);
        MyPageInfo<String,Integer,FollowUpQueryVo> myPageInfo = new MyPageInfo(gShangChangeBeanPageBean);
        //DO转VO
        List<FollowUpQueryVo> followUpQueryVoList =
            FollowUpConvert.gShangChangeProcessBeanToFollowUpVo(gShangChangeBeanPageBean);
        //统计所有工单数量
        Map<String, Integer> allProcessNumber = followUpService.countAllProcessNumber();
        myPageInfo.setMap(allProcessNumber);
        myPageInfo.setList(followUpQueryVoList);
        myPageInfo.setPageSize(followUpQueryVo.getPageSize());
        myPageInfo.setPageNum(followUpQueryVo.getPageNum());
        return myPageInfo;
    }


    @Override
    public Integer countProcessNumber() throws Exception {
        return gShangChangeMapper.countProcessNumber(Jurisdiction.getTenant(), Jurisdiction.getU_name());
    }
}

