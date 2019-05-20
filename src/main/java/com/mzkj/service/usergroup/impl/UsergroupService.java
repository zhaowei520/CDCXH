package com.mzkj.service.usergroup.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mzkj.bean.PrivilegeOfUsergroupBean;
import com.mzkj.bean.UserBean;
import com.mzkj.bean.UserOfUsergroupBean;
import com.mzkj.bean.UsergroupBean;
import com.mzkj.bean.UsergroupDeleteBean;
import com.mzkj.mapper.masterAccessOperation.MasterAccessOperationMapper;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.mapper.usergroup.UsergroupMapper;
import com.mzkj.service.usergroup.UsergroupManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.system.UserVo;
import com.mzkj.vo.usergroup.PrivilegeOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UserOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupQueryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usergroupService")
public class UsergroupService implements UsergroupManager {

    @Autowired
    private UsergroupMapper usergroupMapper;

    @Autowired
    MasterAccessOperationMapper masterAccessOperationMapper;
    @Autowired
    private UserMapper userMapper;

    public MasterAccessOperationMapper getMasterAccessOperationMapper() {
        return masterAccessOperationMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

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

    public UsergroupQueryVo findById(UsergroupQueryVo usergroupQueryVo) {
        UsergroupBean usergroupBean =
                convertVO2Bean(usergroupQueryVo, UsergroupBean.class);
        UsergroupBean usergroupBeanresult = getUsergroupMapper().findById(usergroupBean);
        UsergroupQueryVo usergroupQueryVoResult = ConvertUtil.objectCopyParams(usergroupBeanresult, UsergroupQueryVo.class);
        return usergroupQueryVoResult;
    }

    public JSONArray findByParentId(String parentID) {
        List<UsergroupBean> usergroupBeanChildren = getUsergroupMapper().findByParentId(parentID);
        if (usergroupBeanChildren == null || usergroupBeanChildren.size() == 0) {
            return null;
        }
        JSONArray usergroups = new JSONArray();
        for (UsergroupBean usergroupBean : usergroupBeanChildren) {
            JSONObject usergroup = new JSONObject();
            usergroup.put("label", usergroupBean.getName());
            usergroup.put("key", usergroupBean.getUsergroupId());
            JSONArray usergroupChildren = this.findByParentId(usergroupBean.getUsergroupId());
            if (usergroupChildren != null) {
                usergroup.put("nodes", usergroupChildren);
            }
            usergroups.add(usergroup);
        }
        return usergroups;
    }

    public void delete(List<String> ids) {
        getUsergroupMapper().delete(getUsergroupDeleteBean(ids));
    }

    public UsergroupDeleteBean getUsergroupDeleteBean(List<String> ids) {
        UsergroupDeleteBean usergroupDeleteBean = new UsergroupDeleteBean();
        usergroupDeleteBean.setIds(ids);
        usergroupDeleteBean.setUpdateUser(getUsername());
        return usergroupDeleteBean;
    }

    public List<UserVo> findUsersByUsergroup(UserVo userVo) {
        UserBean userBean = convertVO2Bean(userVo, UserBean.class);
        List<UserBean> userBeans = getUserMapper().findUsersByUsergroup(userBean);
        List<UserVo> userVos =
                (List<UserVo>) ConvertUtil.castListObjectToTargetList(userBeans, UserVo.class);
        return userVos;
    }

    public List<PrivilegeOfUsergroupQueryVo> findPrivilegesByUsergroup(PrivilegeOfUsergroupQueryVo privilegeByUsergroupQueryVo) {
        PrivilegeOfUsergroupBean privilegesByUsergroupBean = convertVO2Bean(privilegeByUsergroupQueryVo, PrivilegeOfUsergroupBean.class);
        List<PrivilegeOfUsergroupBean> privilegesByUsergroupBeans = getMasterAccessOperationMapper().findPrivilegesByUsergroup(privilegesByUsergroupBean);
        List<PrivilegeOfUsergroupQueryVo> privilegeByUsergroupQueryVos =
                (List<PrivilegeOfUsergroupQueryVo>) ConvertUtil.castListObjectToTargetList(privilegesByUsergroupBeans, PrivilegeOfUsergroupQueryVo.class);
        return privilegeByUsergroupQueryVos;
    }

    public void addUser2Usergroup(UserOfUsergroupQueryVo userOfUsergroupQueryVo) {
        UserOfUsergroupBean userOfUsergroupBean = convertVO2Bean(userOfUsergroupQueryVo, UserOfUsergroupBean.class);
        getMasterAccessOperationMapper().addUser2Usergroup(userOfUsergroupBean);
    }

    public void addPrivilege2Usergroup(PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo) {
        PrivilegeOfUsergroupBean privilegeOfUsergroupBean = convertVO2Bean(privilegeOfUsergroupQueryVo, PrivilegeOfUsergroupBean.class);
        getMasterAccessOperationMapper().addPrivilege2Usergroup(privilegeOfUsergroupBean);
    }

    public String getUsername() {
        return Jurisdiction.getUsername();
    }

    /**
     *
     */

    public UserOfUsergroupBean convertVO2Bean(UserOfUsergroupQueryVo userOfUsergroupQueryVo, Class<UserOfUsergroupBean> target) {
        UserOfUsergroupBean userOfUsergroupBean =
                PageUtil.startPageAndObjectCopyParams(userOfUsergroupQueryVo, target);
        return userOfUsergroupBean;
    }

    public PrivilegeOfUsergroupBean convertVO2Bean(PrivilegeOfUsergroupQueryVo privilegeByUsergroupQueryVo, Class<PrivilegeOfUsergroupBean> target) {
        PrivilegeOfUsergroupBean privilegesByUsergroupBean =
                PageUtil.startPageAndObjectCopyParams(privilegeByUsergroupQueryVo, target);
        return privilegesByUsergroupBean;
    }
    public UsergroupBean convertVO2Bean(UsergroupQueryVo usergroupQueryVo, Class<UsergroupBean> target) {
        UsergroupBean usergroupBean =
                PageUtil.startPageAndObjectCopyParams(usergroupQueryVo, target);
        return usergroupBean;
    }

    public UserBean convertVO2Bean(UserVo userVo, Class<UserBean> target) {
        UserBean userBean =
                PageUtil.startPageAndObjectCopyParams(userVo, target);
        return userBean;
    }



}
