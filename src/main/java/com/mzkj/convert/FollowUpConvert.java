package com.mzkj.convert;

import com.github.pagehelper.PageHelper;
import com.mzkj.bean.CommerceBean;
import com.mzkj.bean.GShangChangeBean;
import com.mzkj.bean.TallyBean;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.CommerceProcessQueryVo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 跟进业务转换
 * @Author: zw
 * @Date: 2019/4/23 15:45
 * @Version: 1.0
 */
public class FollowUpConvert {

    public static List<FollowUpQueryVo> commerceProcessBeanToFollowUpVo(List<CommerceBean> commerceBeanList) {
        List<FollowUpQueryVo> followUpQueryVolist = new ArrayList<>();
        if (commerceBeanList != null && commerceBeanList.size() > 0) {
            for (CommerceBean commerceBean : commerceBeanList) {
                FollowUpQueryVo followUpQueryVo = new FollowUpQueryVo();
                followUpQueryVo.setActName(commerceBean.getActName());
                followUpQueryVo.setBusinessTypes(commerceBean.getBusinessTypes());
                //工商注册公司名称特殊处理
                if(StringUtils.isEmpty(commerceBean.getCompanyName())){
                    followUpQueryVo.setCompanyName(commerceBean.getCustomer());
                }else{
                    followUpQueryVo.setCompanyName(commerceBean.getCompanyName());
                }
                followUpQueryVo.setContractDate(commerceBean.getSigningDate());
                followUpQueryVo.setcUser(commerceBean.getcUser());
                followUpQueryVo.setCustomer(commerceBean.getCustomer());
                followUpQueryVo.setNote(commerceBean.getNote());
                followUpQueryVo.setProcInstId(commerceBean.getProcInstId());
                followUpQueryVo.setSignPerson(commerceBean.getSaler());
                followUpQueryVo.setTenantId(commerceBean.getTenantId());
                followUpQueryVo.setActAssignee(commerceBean.getActAssignee());
                followUpQueryVo.setExcutionId(commerceBean.getExcutionId());
                followUpQueryVolist.add(followUpQueryVo);
            }
        }
        return followUpQueryVolist;
    }

    public static CommerceBean followUpVoToCommerceProcessBean(FollowUpQueryVo followUpQueryVo) {
        CommerceBean commerceBean = new CommerceBean();
        if (followUpQueryVo != null ) {
            PageHelper.startPage(followUpQueryVo);

            commerceBean.setActAssignee(followUpQueryVo.getActAssignee());
            commerceBean.setActName(followUpQueryVo.getActName());
            commerceBean.setBusinessTypes(followUpQueryVo.getBusinessTypes());
            commerceBean.setCompanyName(followUpQueryVo.getCompanyName());
            commerceBean.setSigningDate(followUpQueryVo.getContractDate());
            commerceBean.setcUser(followUpQueryVo.getcUser());
            commerceBean.setCustomer(followUpQueryVo.getCustomer());
            commerceBean.setNote(followUpQueryVo.getNote());
            commerceBean.setProcInstId(followUpQueryVo.getProcInstId());
            commerceBean.setSaler(followUpQueryVo.getSignPerson());
        }
        return commerceBean;
    }


    public static List<FollowUpQueryVo> tallyProcessBeanToFollowUpVo(List<TallyBean> tallyBeanList) {
        List<FollowUpQueryVo> followUpQueryVolist = new ArrayList<>();
        if (tallyBeanList != null && tallyBeanList.size() > 0) {
            for (TallyBean tallyBean : tallyBeanList) {
                FollowUpQueryVo followUpQueryVo = new FollowUpQueryVo();
                followUpQueryVo.setActName(tallyBean.getActName());
                followUpQueryVo.setBusinessTypes(tallyBean.getBusinessTypes());
                followUpQueryVo.setCompanyName(tallyBean.getCompanyName());
                followUpQueryVo.setContractDate(tallyBean.getContractDate());
                followUpQueryVo.setcUser(tallyBean.getcUser());
                followUpQueryVo.setCustomer(tallyBean.getLegalRepresentative());
                followUpQueryVo.setNote(tallyBean.getNote());
                followUpQueryVo.setProcInstId(tallyBean.getProcInstId());
                followUpQueryVo.setSignPerson(tallyBean.getSignPerson());
                followUpQueryVo.setTenantId(tallyBean.getTenantId());
                followUpQueryVo.setActAssignee(tallyBean.getActAssignee());
                followUpQueryVo.setExcutionId(tallyBean.getExcutionId());
                followUpQueryVolist.add(followUpQueryVo);
            }
        }
        return followUpQueryVolist;
    }

    public static TallyBean followUpVoToTallyProcessBean(FollowUpQueryVo followUpQueryVo) {
        TallyBean tallyBean = new TallyBean();
        if (followUpQueryVo != null ) {
            tallyBean.setActAssignee(followUpQueryVo.getActAssignee());
            tallyBean.setActName(followUpQueryVo.getActName());
            tallyBean.setBusinessTypes(followUpQueryVo.getBusinessTypes());
            tallyBean.setCompanyName(followUpQueryVo.getCompanyName());
            tallyBean.setContractDate(followUpQueryVo.getContractDate());
            tallyBean.setcUser(followUpQueryVo.getcUser());
            tallyBean.setLegalRepresentative(followUpQueryVo.getCustomer());
            tallyBean.setNote(followUpQueryVo.getNote());
            tallyBean.setProcInstId(followUpQueryVo.getProcInstId());
            tallyBean.setSignPerson(followUpQueryVo.getSignPerson());
        }
        return tallyBean;
    }


    public static List<FollowUpQueryVo> gShangChangeProcessBeanToFollowUpVo(List<GShangChangeBean> gShangChangeBeanList) {
        List<FollowUpQueryVo> followUpQueryVolist = new ArrayList<>();
        if (gShangChangeBeanList != null && gShangChangeBeanList.size() > 0) {
            for (GShangChangeBean gShangChangeBean : gShangChangeBeanList) {
                FollowUpQueryVo followUpQueryVo = new FollowUpQueryVo();
                followUpQueryVo.setActName(gShangChangeBean.getActName());
                followUpQueryVo.setBusinessTypes(gShangChangeBean.getBusinessTypes());
                followUpQueryVo.setCompanyName(gShangChangeBean.getCompanyName());
                followUpQueryVo.setContractDate(gShangChangeBean.getSignDate());
                followUpQueryVo.setcUser(gShangChangeBean.getCreateer());
                followUpQueryVo.setCustomer(gShangChangeBean.getDelegateMan());
                followUpQueryVo.setNote(gShangChangeBean.getNote());
                followUpQueryVo.setProcInstId(gShangChangeBean.getProcInstId());
                followUpQueryVo.setSignPerson(gShangChangeBean.getSignMan());
                followUpQueryVo.setTenantId(gShangChangeBean.getTenantId());
                followUpQueryVo.setActAssignee(gShangChangeBean.getActAssignee());
                followUpQueryVo.setExcutionId(gShangChangeBean.getExcutionId());
                followUpQueryVolist.add(followUpQueryVo);
            }
        }
        return followUpQueryVolist;
    }

    public static GShangChangeBean followUpVoToGShangChangeProcessBean(FollowUpQueryVo followUpQueryVo) {
        GShangChangeBean gShangChangeBean = new GShangChangeBean();
        if (followUpQueryVo != null ) {
            gShangChangeBean.setActAssignee(followUpQueryVo.getActAssignee());
            gShangChangeBean.setActName(followUpQueryVo.getActName());
            gShangChangeBean.setBusinessTypes(followUpQueryVo.getBusinessTypes());
            gShangChangeBean.setCompanyName(followUpQueryVo.getCompanyName());
            gShangChangeBean.setSignDate(followUpQueryVo.getContractDate());
            gShangChangeBean.setCreateer(followUpQueryVo.getcUser());
            gShangChangeBean.setDelegateMan(followUpQueryVo.getCustomer());
            gShangChangeBean.setNote(followUpQueryVo.getNote());
            gShangChangeBean.setProcInstId(followUpQueryVo.getProcInstId());
            gShangChangeBean.setSignMan(followUpQueryVo.getSignPerson());
        }
        return gShangChangeBean;
    }
}
