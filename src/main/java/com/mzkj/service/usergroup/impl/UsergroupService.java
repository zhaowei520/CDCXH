package com.mzkj.service.usergroup.impl;

import com.mzkj.bean.UsergroupBean;
import com.mzkj.mapper.usergroup.UsergroupMapper;
import com.mzkj.service.usergroup.UsergroupManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.usergroup.UsergroupQueryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usergroupService")
public class UsergroupService implements UsergroupManager {

    @Autowired
    private UsergroupMapper usergroupMapper;

    public UsergroupMapper getUsergroupMapper() {
        return usergroupMapper;
    }

    /**
     * 列表
     */
    public List<UsergroupQueryVo> list(UsergroupQueryVo usergroupQueryVo) throws Exception {
        UsergroupBean usergroupBean =
                convertVO2Bean(usergroupQueryVo, UsergroupBean.class);
        List<UsergroupBean> usergroupBeans = getUsergroupMapper().datalistPage(usergroupBean);
        List<UsergroupQueryVo> usergroupQueryVos =
                (List<UsergroupQueryVo>) ConvertUtil.castListObjectToTargetList(usergroupBeans, UsergroupQueryVo.class);
        return usergroupQueryVos;
    }

    /**
     *
     */
    public void save(UsergroupQueryVo usergroupQueryVo) {
        UsergroupBean usergroupBean =
                convertVO2Bean(usergroupQueryVo, UsergroupBean.class);
        getUsergroupMapper().save(usergroupBean);
    }


    /**
     *
     */
    public void update(UsergroupQueryVo usergroupQueryVo) {
        UsergroupBean usergroupBean =
                convertVO2Bean(usergroupQueryVo, UsergroupBean.class);
        getUsergroupMapper().update(usergroupBean);
    }

    /**
     *
     */
    public UsergroupBean convertVO2Bean(UsergroupQueryVo usergroupQueryVo, Class<UsergroupBean> target) {
        UsergroupBean usergroupBean =
                PageUtil.startPageAndObjectCopyParams(usergroupQueryVo, UsergroupBean.class);
        return usergroupBean;
    }


}
