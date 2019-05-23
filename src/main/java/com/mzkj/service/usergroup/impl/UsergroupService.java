package com.mzkj.service.usergroup.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.MasterAccessOperationMappingBean;
import com.mzkj.bean.PrivilegeBean;
import com.mzkj.bean.PrivilegeOfUsergroupBean;
import com.mzkj.bean.PrivilegeUnselected2UsergroupBean;
import com.mzkj.bean.UserBean;
import com.mzkj.bean.UserOfUsergroupBean;
import com.mzkj.bean.UserUnselected2UsergroupBean;
import com.mzkj.bean.UsergroupBean;
import com.mzkj.bean.UsergroupDeleteBean;
import com.mzkj.mapper.masterAccessOperation.MasterAccessOperationMapper;
import com.mzkj.mapper.privilege.PrivilegeMapper;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.mapper.usergroup.UsergroupMapper;
import com.mzkj.service.usergroup.UsergroupManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.DateUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.DeleteStatus;
import com.mzkj.util.enums.RelatingType;
import com.mzkj.vo.privilege.PrivilegeVo;
import com.mzkj.vo.usergroup.PrivilegeOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.PrivilegeUnselected2UsergroupQueryVo;
import com.mzkj.vo.usergroup.UserOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UserUnselected2UsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("usergroupService")
public class UsergroupService implements UsergroupManager {

    @Autowired
    private UsergroupMapper usergroupMapper;

    @Autowired
    MasterAccessOperationMapper masterAccessOperationMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    public MasterAccessOperationMapper getMasterAccessOperationMapper() {
        return masterAccessOperationMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public UsergroupMapper getUsergroupMapper() {
        return usergroupMapper;
    }

    public PrivilegeMapper getPrivilegeMapper() {
        return privilegeMapper;
    }
    /**
     * 列表
     */
    public PageInfo list(UsergroupQueryVo usergroupQueryVo) throws Exception {
        UsergroupBean usergroupBean =
                convertVO2Bean(usergroupQueryVo, UsergroupBean.class);
        List<UsergroupBean> usergroupBeans = getUsergroupMapper().datalistPage(usergroupBean);
        PageInfo pageInfo = new PageInfo(usergroupBeans);
        pageInfo.setList(usergroupBeans);
        return pageInfo;
    }

    /**
     *
     */
    public void save(UsergroupVo usergroupVo) {
        UsergroupBean usergroupBean = ConvertUtil.objectCopyParams(usergroupVo, UsergroupBean.class);
        usergroupBean.setCreateUser(getUsername());
        usergroupBean.setCreateDate(DateUtil.getTime());
        usergroupBean.setUpdateUser(getUsername());
        usergroupBean.setUpdateDate(DateUtil.getTime());
        usergroupBean.setTenantId(getTenantId());
        usergroupBean.setDeleted(DeleteStatus.NOT_YET.getCode());
        getUsergroupMapper().save(usergroupBean);
    }

    public void update(UsergroupVo usergroupVo) {
        UsergroupBean usergroupBean = ConvertUtil.objectCopyParams(usergroupVo, UsergroupBean.class);
        usergroupBean.setUpdateUser(getUsername());
        usergroupBean.setUpdateDate(DateUtil.getTime());
        getUsergroupMapper().update(usergroupBean);
    }

    public UsergroupBean findById(String id) {
        UsergroupBean usergroupBean = getUsergroupMapper().findById(id);
        return usergroupBean;
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

    public PageInfo findUsersByUsergroup(UserOfUsergroupQueryVo userOfUsergroupQueryVo) {
        UserOfUsergroupBean userBean = convertVO2Bean(userOfUsergroupQueryVo, UserOfUsergroupBean.class);
        List<UserBean> userBeans = getUserMapper().findUsersByUsergroup(userBean);
        PageInfo pageInfo = new PageInfo(userBeans);
        pageInfo.setList(userBeans);
        return pageInfo;
    }

    public PageInfo findPrivilegesByUsergroup(PrivilegeOfUsergroupQueryVo privilegeByUsergroupQueryVo) {
        PrivilegeOfUsergroupBean privilegesByUsergroupBean = convertVO2Bean(privilegeByUsergroupQueryVo, PrivilegeOfUsergroupBean.class);
        List<PrivilegeOfUsergroupBean> privilegesByUsergroupBeans = getMasterAccessOperationMapper().findPrivilegesByUsergroup(privilegesByUsergroupBean);
        PageInfo pageInfo = new PageInfo(privilegesByUsergroupBeans);
        return pageInfo;
    }

    public void addUser2Usergroup(String usergroupId, String[] userIds, String[] operationParams) {
        String masterType = RelatingType.USERGROUP.getCode();
        String masterValue = usergroupId;
        String accessType = RelatingType.USER.getCode();
        String[] accessValues = userIds;
        String[] operations = operationParams;
        List<MasterAccessOperationMappingBean> masterAccessOperationMappingBeanList
                = doMasterAccessOperationMappingBeanList(masterType, masterValue, accessType, accessValues, operations);
        getMasterAccessOperationMapper().addAccess2Master(masterAccessOperationMappingBeanList);
    }

    public void addPrivilege2Usergroup(String usergroupId, String[] privileges, String[] operationParams) {
        String masterType = RelatingType.USERGROUP.getCode();
        String masterValue = usergroupId;
        String accessType = RelatingType.PRIVILEGE.getCode();
        String[] accessValues = privileges;
        String[] operations = operationParams;
        List<MasterAccessOperationMappingBean> masterAccessOperationMappingBeanList
                = doMasterAccessOperationMappingBeanList(masterType, masterValue, accessType, accessValues, operations);
        getMasterAccessOperationMapper().addAccess2Master(masterAccessOperationMappingBeanList);
    }

    public void deleteUsersOfUsergroup(String[] mappingIds) {
        String tenantId = getTenantId();
        getMasterAccessOperationMapper().deleteMasterAccessOperationMapping(mappingIds, tenantId, getUsername());
    }

    public void deletePrivilegesOfUsergroup(String[] mappingIds) {
        String tenantId = getTenantId();
        getMasterAccessOperationMapper().deleteMasterAccessOperationMapping(mappingIds, tenantId, getUsername());
    }

    @Override
    public PageInfo findUsersUnselected(UserUnselected2UsergroupQueryVo userUnselected2UsergroupQueryVo) {
        UserUnselected2UsergroupBean userUnselected2UsergroupBean = convertVO2Bean(userUnselected2UsergroupQueryVo, UserUnselected2UsergroupBean.class);
        userUnselected2UsergroupBean.setTenantId(getTenantId());
        List<UserBean> userBeans = getUserMapper().findUsersUnselected(userUnselected2UsergroupBean);
        PageInfo pageInfo = new PageInfo(userBeans);
        return pageInfo;
    }

    @Override
    public PageInfo findPrivilegesUnselected(PrivilegeUnselected2UsergroupQueryVo privilegeUnselected2UsergroupQueryVo) {
        PrivilegeUnselected2UsergroupBean privilegeUnselected2UsergroupBean = convertVO2Bean(privilegeUnselected2UsergroupQueryVo, PrivilegeUnselected2UsergroupBean.class);
        privilegeUnselected2UsergroupBean.setTenantId(getTenantId());
        List<PrivilegeBean> privilegeBeans = getPrivilegeMapper().findPrivilegesUnselected(privilegeUnselected2UsergroupBean);
        PageInfo pageInfo = new PageInfo(privilegeBeans);
        List<PrivilegeVo> userQueryVos =
                (List<PrivilegeVo>) ConvertUtil.castListObjectToTargetList(privilegeBeans, PrivilegeVo.class);
        pageInfo.setList(userQueryVos);
        return pageInfo;
    }


    public String getUsername() {
        return Jurisdiction.getUsername();
    }


    public UserUnselected2UsergroupBean convertVO2Bean(UserUnselected2UsergroupQueryVo userUnselected2UsergroupQueryVo, Class<UserUnselected2UsergroupBean> target) {
        UserUnselected2UsergroupBean userUnselected2UsergroupBean =
                PageUtil.startPageAndObjectCopyParams(userUnselected2UsergroupQueryVo, target);
        return userUnselected2UsergroupBean;
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

    public UsergroupBean convertVO2Bean(UsergroupVo usergroupVo, Class<UsergroupBean> target) {
        UsergroupBean usergroupBean =
                PageUtil.startPageAndObjectCopyParams(usergroupVo, target);
        return usergroupBean;
    }

    public UserOfUsergroupBean convertVO2Bean(UserOfUsergroupQueryVo userVo, Class<UserOfUsergroupBean> target) {
        UserOfUsergroupBean userOfUsergroupBean =
                PageUtil.startPageAndObjectCopyParams(userVo, target);
        return userOfUsergroupBean;
    }

    public PrivilegeUnselected2UsergroupBean convertVO2Bean(PrivilegeUnselected2UsergroupQueryVo privilegesUnselected2UsergroupQueryVo, Class<PrivilegeUnselected2UsergroupBean> target) {
        PrivilegeUnselected2UsergroupBean privilegeUnselected2UsergroupBean =
                PageUtil.startPageAndObjectCopyParams(privilegesUnselected2UsergroupQueryVo, target);
        return privilegeUnselected2UsergroupBean;
    }

    public MasterAccessOperationMappingBean newMasterAccessOperationMapping(String masterType,
                                                                            String masterValue, String accessType, String accessValue,
                                                                            String operation, String masterAccessOperationMappingId) {
        MasterAccessOperationMappingBean masterAccessOperationMappingBean = new MasterAccessOperationMappingBean();
        masterAccessOperationMappingBean.setMasterType(masterType);
        masterAccessOperationMappingBean.setMasterValue(masterValue);
        masterAccessOperationMappingBean.setAccessType(accessType);
        masterAccessOperationMappingBean.setAccessValue(accessValue);
        masterAccessOperationMappingBean.setOperation(operation);
        masterAccessOperationMappingBean.setMasterAccessOperationMappingId(masterAccessOperationMappingId);
        return masterAccessOperationMappingBean;
    }

    public List<MasterAccessOperationMappingBean> doMasterAccessOperationMappingBeanList(String masterType
            , String masterValue, String accessType, String[] accessValues, String[] operations) {
        List<MasterAccessOperationMappingBean> masterAccessOperationMappingBeans = new ArrayList<MasterAccessOperationMappingBean>();
        for (int i = 0; i < accessValues.length; i++) {
            String masterAccessOperationMappingId = UuidUtil.get32UUID();
            MasterAccessOperationMappingBean masterAccessOperationMappingBean = new MasterAccessOperationMappingBean();
            masterAccessOperationMappingBean.setMasterAccessOperationMappingId(masterAccessOperationMappingId);
            masterAccessOperationMappingBean.setMasterType(masterType);
            masterAccessOperationMappingBean.setMasterValue(masterValue);
            masterAccessOperationMappingBean.setAccessType(accessType);
            masterAccessOperationMappingBean.setAccessValue(accessValues[i]);
            masterAccessOperationMappingBean.setOperation(operations[i]);
            masterAccessOperationMappingBean.setTenantId(getTenantId());
            masterAccessOperationMappingBean.setCreateUser(getUsername());
            masterAccessOperationMappingBean.setCreateDate(DateUtil.getTime());
            masterAccessOperationMappingBean.setUpdateUser(getUsername());
            masterAccessOperationMappingBean.setUpdateDate(DateUtil.getTime());
            masterAccessOperationMappingBean.setDeleted(DeleteStatus.NOT_YET.getCode());
            masterAccessOperationMappingBeans.add(masterAccessOperationMappingBean);
        }
        return masterAccessOperationMappingBeans;
    }

    public String getTenantId() {
        return Jurisdiction.getTenant();
    }
}
