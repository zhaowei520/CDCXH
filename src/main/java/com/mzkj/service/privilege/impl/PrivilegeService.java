package com.mzkj.service.privilege.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.MasterAccessOperationMappingBean;
import com.mzkj.bean.PrivilegeBean;
import com.mzkj.domain.Privilege;
import com.mzkj.facade.vo.AddUsers2PrivilegeVo;
import com.mzkj.facade.vo.InsertPrivilegeVo;
import com.mzkj.mapper.masterAccessOperation.MasterAccessOperationMapper;
import com.mzkj.mapper.privilege.PrivilegeMapper;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.service.privilege.PrivilegeManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.DateUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.DeleteStatus;
import com.mzkj.util.enums.RelatingType;
import com.mzkj.vo.BaseVo;
import com.mzkj.vo.privilege.PrivilegeQueryVo;
import com.mzkj.vo.privilege.PrivilegeVo;
import com.mzkj.vo.privilege.QueryPrivilegesByUserVo;
import com.mzkj.vo.privilege.UserOfPrivilegeQueryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("privilegeService")
public class PrivilegeService implements PrivilegeManager {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    MasterAccessOperationMapper masterAccessOperationMapper;

    @Autowired
    private UserMapper userMapper;

    public static <T> T objectCopyParams(Object obj, Class<T> target) {
        return ConvertUtil.objectCopyParams(obj, target);
    }

    public PrivilegeMapper getPrivilegeMapper() {
        return privilegeMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public MasterAccessOperationMapper getMasterAccessOperationMapper() {
        return masterAccessOperationMapper;
    }

    public PageInfo datalistPage(PrivilegeQueryVo privilegeQueryVo) {
        PrivilegeBean privilegeBean = convertVO2Bean(privilegeQueryVo, PrivilegeBean.class);
        List<PrivilegeBean> privilegeBeans = getPrivilegeMapper().datalistPage(privilegeBean);
        PageInfo pageInfo = new PageInfo(privilegeBeans);
        return pageInfo;
    }

    public void update(PrivilegeVo privilegeVo) {
        PrivilegeBean privilegeBean = objectCopyParams(privilegeVo, PrivilegeBean.class);
        privilegeBean.setUpdateUser(getUsername());
        privilegeBean.setUpdateDate(DateUtil.getTime());
        getPrivilegeMapper().update(privilegeBean);
    }

    public PrivilegeVo findById(String id) {
        PrivilegeBean privilegeBeanResult = getPrivilegeMapper().findById(id);
        PrivilegeVo privilegeVo = objectCopyParams(privilegeBeanResult, PrivilegeVo.class);
        return privilegeVo;
    }

    public String insert(InsertPrivilegeVo insertPrivilegeVo) {
        PrivilegeBean privilegeBean = new PrivilegeBean();
        privilegeBean.setName(insertPrivilegeVo.getName());
        privilegeBean.setType(insertPrivilegeVo.getType());
        privilegeBean.setSubType(insertPrivilegeVo.getSubType());
        privilegeBean.setTenantId(getTenantId());
        privilegeBean.setPrivilegeId(UuidUtil.get32UUID());
        privilegeBean.setCreateUser(getUsername());
        privilegeBean.setCreateDate(DateUtil.getTime());
        privilegeBean.setUpdateUser(getUsername());
        privilegeBean.setUpdateDate(DateUtil.getTime());
        privilegeBean.setDeleted(DeleteStatus.NOT_YET.getCode());
        getPrivilegeMapper().insert(privilegeBean);
        return privilegeBean.getPrivilegeId();
    }

    @Override
    public PageInfo findUsersByPrivilege(UserOfPrivilegeQueryVo userOfPrivilegeQueryVo) {
        startPage(userOfPrivilegeQueryVo);
        String privilegeId = userOfPrivilegeQueryVo.getPrivilegeId();
        List<PrivilegeBean> privilegeBeans = getUserMapper().findUsersByPrivilege(privilegeId);
        PageInfo pageInfo = new PageInfo(privilegeBeans);
        return pageInfo;
    }

    @Override
    public PageInfo findUsersUnselectedByPrivilege(UserOfPrivilegeQueryVo userOfPrivilegeQueryVo) {
        startPage(userOfPrivilegeQueryVo);
        String privilegeId = userOfPrivilegeQueryVo.getPrivilegeId();
        List<PrivilegeBean> privilegeBeans = getUserMapper().findUsersUnselectedByPrivilege(privilegeId);
        PageInfo pageInfo = new PageInfo(privilegeBeans);
        return pageInfo;
    }

    @Override
    public void addUsers2Privilege(AddUsers2PrivilegeVo addUsers2PrivilegeVo) {
        String masterType = RelatingType.USER.getCode();
        String[] masterValues = addUsers2PrivilegeVo.getUserIds();
        String accessType = RelatingType.PRIVILEGE.getCode();
        String accessValue = addUsers2PrivilegeVo.getPrivilegeId();
        String[] operations = addUsers2PrivilegeVo.getOperations();
        List<MasterAccessOperationMappingBean> masterAccessOperationMappingBeanList
                = doMasterAccessOperationMappingBeanList(masterType, masterValues, accessType, accessValue, operations);
        getMasterAccessOperationMapper().addAccess2Master(masterAccessOperationMappingBeanList);
    }

    @Override
    public void deleteUsersOfPrivilege(String[] mappingIds) {
        getMasterAccessOperationMapper().deleteMasterAccessOperationMapping(mappingIds, getTenantId(), getUsername());
    }

    public List<Privilege> findPrivilegesByUser(String userId) {
        return getPrivilegeMapper().findPrivilegesByUser(userId);
    }

    public PageInfo findPrivilegesByUser(QueryPrivilegesByUserVo queryPrivilegesByUserVo) {
        startPage(queryPrivilegesByUserVo);
        List<PrivilegeBean> privilegeBeans = getPrivilegeMapper().findPrivilegesByUserInPage(queryPrivilegesByUserVo);
        PageInfo<List<PrivilegeBean>> pageInfo = new PageInfo(privilegeBeans);
        return pageInfo;
    }

    public PageInfo findPrivilegesUnselectedByUser(QueryPrivilegesByUserVo queryPrivilegesByUserVo) {
        startPage(queryPrivilegesByUserVo);
        List<PrivilegeBean> privilegeBeans = getPrivilegeMapper().findPrivilegesUnselectedByUser(queryPrivilegesByUserVo);
        PageInfo<List<PrivilegeBean>> pageInfo = new PageInfo(privilegeBeans);
        return pageInfo;
    }

    public void addPrivileges2User(String[] privilegeIds, String userId, String[] operations) {
    }

    public List<MasterAccessOperationMappingBean> doMasterAccessOperationMappingBeanList(String masterType
            , String[] masterValue, String accessType, String accessValues, String[] operations) {
        List<MasterAccessOperationMappingBean> masterAccessOperationMappingBeans = new ArrayList<MasterAccessOperationMappingBean>();
        for (int i = 0; i < masterValue.length; i++) {
            String masterAccessOperationMappingId = UuidUtil.get32UUID();
            MasterAccessOperationMappingBean masterAccessOperationMappingBean = new MasterAccessOperationMappingBean();
            masterAccessOperationMappingBean.setMasterAccessOperationMappingId(masterAccessOperationMappingId);
            masterAccessOperationMappingBean.setMasterType(masterType);
            masterAccessOperationMappingBean.setMasterValue(masterValue[i]);
            masterAccessOperationMappingBean.setAccessType(accessType);
            masterAccessOperationMappingBean.setAccessValue(accessValues);
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

    public PrivilegeBean convertVO2Bean(PrivilegeQueryVo privilegeQueryVo, Class<PrivilegeBean> privilegeBeanClass) {
        PrivilegeBean privilegeBean =
                PageUtil.startPageAndObjectCopyParams(privilegeQueryVo, PrivilegeBean.class);
        return privilegeBean;
    }

    public Page startPage(BaseVo baseVo) {
        return PageHelper.startPage(baseVo);
    }

    public PrivilegeBean convertVO2Bean(PrivilegeVo privilegeVo, Class<PrivilegeBean> privilegeBeanClass) {
        PrivilegeBean privilegeBean =
                PageUtil.startPageAndObjectCopyParams(privilegeVo, PrivilegeBean.class);
        return privilegeBean;
    }

    public String getTenantId() {
        return Jurisdiction.getTenant();
    }

    public String getUsername() {
        return Jurisdiction.getUsername();
    }

}
