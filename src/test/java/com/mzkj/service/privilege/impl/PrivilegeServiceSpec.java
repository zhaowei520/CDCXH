package com.mzkj.service.privilege.impl;

import com.google.common.collect.ImmutableList;

import com.mzkj.bean.PrivilegeBean;
import com.mzkj.mapper.privilege.PrivilegeMapper;
import com.mzkj.vo.privilege.PrivilegeQueryVo;

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

    @Before
    public void before() {
        privilegeBeans = ImmutableList.of(new PrivilegeBean());
        privilegeBean = new PrivilegeBean();
        privilegeQueryVo = new PrivilegeQueryVo();
        privilegeService = spy(PrivilegeService.class);
        privilegeMapper = mock(PrivilegeMapper.class);
        doReturn(privilegeBean).when(privilegeService).convertVO2Bean(privilegeQueryVo, PrivilegeBean.class);
        doReturn(privilegeMapper).when(privilegeService).getPrivilegeMapper();
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
        List<PrivilegeQueryVo> privilegeQueryVos = privilegeService.datalistPage(privilegeQueryVo);
        Assert.assertEquals(privilegeQueryVos.size(), privilegeBeans.size());
    }

    @Test
    public void whenUpdateThenInvokeMapperUpdate() {
        privilegeService.update(privilegeQueryVo);
        verify(privilegeMapper, times(1)).update(privilegeBean);
    }

    @Test
    public void whenFindByIdThenInvokeMapperFindById() {
        privilegeService.findById(privilegeQueryVo);
        verify(privilegeMapper, times(1)).findById(privilegeBean);
    }

    @Test
    public void whenFindByIdSuccessfullyThenReturnPojo() {
        PrivilegeBean privilegeBeanResult = new PrivilegeBean();
        doReturn(privilegeBeanResult).when(privilegeMapper).findById(privilegeBean);
        PrivilegeQueryVo privilegeQueryVoResult = privilegeService.findById(privilegeQueryVo);
        Assert.assertNotNull(privilegeQueryVoResult);
    }

    @Test
    public void whenInsertThenInvokeMapperInsert() {
        privilegeService.insert(privilegeQueryVo);
        verify(privilegeMapper, times(1)).insert(privilegeBean);
    }

    @Test
    public void whenDeleteThenInvokeMapperDelete() {

    }
}
