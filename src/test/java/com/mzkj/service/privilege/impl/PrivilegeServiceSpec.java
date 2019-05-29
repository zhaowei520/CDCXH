package com.mzkj.service.privilege.impl;

import com.google.common.collect.ImmutableList;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.MasterAccessOperationMappingBean;
import com.mzkj.bean.PrivilegeBean;
import com.mzkj.mapper.masterAccessOperation.MasterAccessOperationMapper;
import com.mzkj.mapper.privilege.PrivilegeMapper;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.util.enums.RelatingType;
import com.mzkj.vo.privilege.PrivilegeQueryVo;
import com.mzkj.vo.privilege.PrivilegeVo;
import com.mzkj.vo.privilege.UserOfPrivilegeQueryVo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PrivilegeServiceSpec {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PrivilegeBean privilegeBean;
    private PrivilegeQueryVo privilegeQueryVo;
    private PrivilegeService privilegeService;
    private PrivilegeMapper privilegeMapper;
    private List<PrivilegeBean> privilegeBeans;
    MasterAccessOperationMapper masterAccessOperationMapper;
    PrivilegeVo privilegeVo;
    String privilegeId = "123";
    UserMapper userMapper;

    @Before
    public void before() {
        privilegeBeans = ImmutableList.of(new PrivilegeBean());
        privilegeBean = new PrivilegeBean();
        privilegeQueryVo = new PrivilegeQueryVo();
        privilegeService = spy(PrivilegeService.class);
        privilegeMapper = mock(PrivilegeMapper.class);
        userMapper = mock(UserMapper.class);
        masterAccessOperationMapper = mock(MasterAccessOperationMapper.class);
        doReturn(privilegeBean).when(privilegeService).convertVO2Bean(privilegeQueryVo, PrivilegeBean.class);
        doReturn(privilegeMapper).when(privilegeService).getPrivilegeMapper();
        doReturn(userMapper).when(privilegeService).getUserMapper();
        doReturn(masterAccessOperationMapper).when(privilegeService).getMasterAccessOperationMapper();
        privilegeVo = new PrivilegeVo();
    }

    @Test
    public void whenListMapperThrowsExceptionThenThrowsException() {
        doThrow(RuntimeException.class).when(privilegeMapper).datalistPage(privilegeBean);
        expectedException.expect(RuntimeException.class);
        privilegeService.datalistPage(privilegeQueryVo);

    }

    @Test
    public void whenListSuccessfullyThenReturnList() {
        doReturn(privilegeBeans).when(privilegeMapper).datalistPage(privilegeBean);
        PageInfo pageInfo = privilegeService.datalistPage(privilegeQueryVo);
        Assert.assertEquals(pageInfo.getList().size(), privilegeBeans.size());
    }

    @Test
    public void whenUpdateThenInvokeMapperUpdate() {
//        privilegeService.update(privilegeVo);
        //TODO 待补充
//        verify(privilegeMapper, times(1)).update(privilegeBean);
    }

    @Test
    public void whenFindByIdThenInvokeMapperFindById() {
        privilegeService.findById(privilegeId);
        verify(privilegeMapper, times(1)).findById(privilegeId);
    }

    @Test
    public void whenFindByIdSuccessfullyThenReturnPojo() {
        PrivilegeBean privilegeBeanResult = new PrivilegeBean();
        doReturn(privilegeBeanResult).when(privilegeMapper).findById(privilegeId);
        PrivilegeVo privilegeVo = privilegeService.findById(privilegeId);
        Assert.assertNotNull(privilegeVo);
    }

    @Test
    public void whenInsertThenInvokeMapperInsert() {
        doReturn("CDCXH").when(privilegeService).getTenantId();
//        doReturn(privilegeBean).when(privilegeService).objectCopyParams(privilegeVo, PrivilegeBean.class);
//        privilegeService.insert(privilegeVo);
        //TODO 待补充mock
//        verify(privilegeMapper, times(1)).insert(privilegeBean);
    }

    @Test
    public void whenDeleteThenInvokeMapperDelete() {

    }

    @Test
    public void whenFindUsersByPrivilegeThenReturnData() {
        UserOfPrivilegeQueryVo userOfPrivilegeQueryVo = new UserOfPrivilegeQueryVo();
        doReturn(null).when(privilegeService).startPage(userOfPrivilegeQueryVo);
        PageInfo pageInfo = privilegeService.findUsersByPrivilege(userOfPrivilegeQueryVo);
        Assert.assertNotNull(pageInfo);
    }

    @Test
    public void whenFindUsersUnselectedByPrivilegeThenReturnData() {
        UserOfPrivilegeQueryVo userOfPrivilegeQueryVo = new UserOfPrivilegeQueryVo();
        doReturn(null).when(privilegeService).startPage(userOfPrivilegeQueryVo);
        PageInfo pageInfo = privilegeService.findUsersUnselectedByPrivilege(userOfPrivilegeQueryVo);
        Assert.assertNotNull(pageInfo);
    }

    @Test
    public void whenAddUsers2PrivilegesThenInvokeMapper() {
        String privilegeId = "123";
        String[] userIds = {"123", "1233"};
        String[] operations = {"read", "read"};
        String masterType = RelatingType.USERGROUP.getCode();
        String[] masterValues = userIds;
        String accessType = RelatingType.PRIVILEGE.getCode();
        String accessValue = privilegeId;
        List<MasterAccessOperationMappingBean> masterAccessOperationMappingBeanList = null;
        doReturn(masterAccessOperationMappingBeanList).when(privilegeService).doMasterAccessOperationMappingBeanList(masterType, masterValues, accessType, accessValue, operations);
        //TODO
//        privilegeService.addUsers2Privileges(privilegeId, userIds, operations);
//        verify(masterAccessOperationMapper, times(1)).addAccess2Master(masterAccessOperationMappingBeanList);
    }

    @Test
    public void whenDeleteUsersOfPrivilegeThenInvokeMapper() {
        String[] mappingIds = {"123"};
        String tenantId = "CDCXH";
        String updateUser = "duanhui";
        doReturn(tenantId).when(privilegeService).getTenantId();
        doReturn(updateUser).when(privilegeService).getUsername();
        privilegeService.deleteUsersOfPrivilege(mappingIds);
        verify(masterAccessOperationMapper, times(1)).deleteMasterAccessOperationMapping(mappingIds, tenantId, updateUser);
    }
}
