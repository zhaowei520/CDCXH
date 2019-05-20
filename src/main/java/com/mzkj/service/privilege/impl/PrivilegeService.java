package com.mzkj.service.privilege.impl;

import com.mzkj.bean.PrivilegeBean;
import com.mzkj.mapper.privilege.PrivilegeMapper;
import com.mzkj.service.privilege.PrivilegeManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.privilege.PrivilegeQueryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("privilegeService")
public class PrivilegeService implements PrivilegeManager {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    public PrivilegeMapper getPrivilegeMapper() {
        return privilegeMapper;
    }

    public List<PrivilegeQueryVo> datalistPage(PrivilegeQueryVo privilegeQueryVo) {
        PrivilegeBean privilegeBean = convertVO2Bean(privilegeQueryVo, PrivilegeBean.class);
        List<PrivilegeBean> privilegeBeans = getPrivilegeMapper().datalistPage(privilegeBean);
        List<PrivilegeQueryVo> privilegeQueryVos =
                (List<PrivilegeQueryVo>) ConvertUtil.castListObjectToTargetList(privilegeBeans, PrivilegeQueryVo.class);
        return privilegeQueryVos;
    }

    public void update(PrivilegeQueryVo privilegeQueryVo) {
        PrivilegeBean privilegeBean = convertVO2Bean(privilegeQueryVo, PrivilegeBean.class);
        getPrivilegeMapper().update(privilegeBean);
    }

    public PrivilegeQueryVo findById(PrivilegeQueryVo privilegeQueryVo) {
        PrivilegeBean privilegeBean =
                convertVO2Bean(privilegeQueryVo, PrivilegeBean.class);
        PrivilegeBean privilegeBeanResult = getPrivilegeMapper().findById(privilegeBean);
        PrivilegeQueryVo privilegeQueryVoResult = ConvertUtil.objectCopyParams(privilegeBeanResult, PrivilegeQueryVo.class);
        return privilegeQueryVoResult;
    }

    public void insert(PrivilegeQueryVo privilegeQueryVo) {
        PrivilegeBean privilegeBean =
                convertVO2Bean(privilegeQueryVo, PrivilegeBean.class);
        getPrivilegeMapper().insert(privilegeBean);
    }

    public PrivilegeBean convertVO2Bean(PrivilegeQueryVo privilegeQueryVo, Class<PrivilegeBean> privilegeBeanClass) {
        PrivilegeBean privilegeBean =
                PageUtil.startPageAndObjectCopyParams(privilegeQueryVo, PrivilegeBean.class);
        return privilegeBean;
    }

}
