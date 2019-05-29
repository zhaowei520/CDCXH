package com.mzkj.service.user.impl;

import com.google.common.collect.ImmutableList;

import com.mzkj.bean.PrivilegeBean;
import com.mzkj.mapper.masterAccessOperation.MasterAccessOperationMapper;
import com.mzkj.mapper.privilege.PrivilegeMapper;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.service.privilege.impl.PrivilegeService;
import com.mzkj.vo.privilege.PrivilegeQueryVo;
import com.mzkj.vo.privilege.PrivilegeVo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserServiceSpec {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    MasterAccessOperationMapper masterAccessOperationMapper;
    PrivilegeVo privilegeVo;
    String privilegeId = "123";
    UserMapper userMapper;
    private PrivilegeBean privilegeBean;
    private PrivilegeQueryVo privilegeQueryVo;
    private PrivilegeService privilegeService;
    private PrivilegeMapper privilegeMapper;
    private List<PrivilegeBean> privilegeBeans;

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
    public void whenFindPrivilegesByUserThenInvokeService() {
        String userId = null;
        privilegeService.findPrivilegesByUser(userId);
        verify(privilegeMapper, times(1)).findPrivilegesByUser(userId);
    }
}
