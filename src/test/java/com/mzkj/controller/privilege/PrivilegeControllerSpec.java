package com.mzkj.controller.privilege;

import com.google.common.collect.ImmutableList;

import com.mzkj.service.privilege.PrivilegeManager;
import com.mzkj.service.privilege.impl.PrivilegeService;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.privilege.PrivilegeQueryVo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PrivilegeControllerSpec {

    PrivilegeQueryVo privilegeQueryVo;
    PrivilegeController privilegeController;
    PrivilegeManager privilegeService;

    @Before
    public void before() {
        privilegeQueryVo = new PrivilegeQueryVo();
        privilegeController = spy(PrivilegeController.class);
        privilegeService = mock(PrivilegeService.class);
        doReturn(privilegeService).when(privilegeController).getPrivilegeService();
    }

    @Test
    public void whenListThenInvokeServiceList() {
        privilegeController.list(privilegeQueryVo);
        verify(privilegeService, times(1)).datalistPage(privilegeQueryVo);
    }

    @Test
    public void whenListExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).datalistPage(privilegeQueryVo);
        Result result = privilegeController.list(privilegeQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("查询权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenListThenReturnTrueResult() {
        Result result = privilegeController.list(privilegeQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("查询权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenListThenReturnDataResult() {
        List<PrivilegeQueryVo> privilegeQueryVos = ImmutableList.of(new PrivilegeQueryVo());
        doReturn(privilegeQueryVos).when(privilegeService).datalistPage(privilegeQueryVo);
        Result result = privilegeController.list(privilegeQueryVo);
        Assert.assertEquals(privilegeQueryVos, result.getData());
    }

    @Test
    public void whenUpdateThenInvokeServiceUpdate() {
        privilegeController.update(privilegeQueryVo);
        verify(privilegeService, times(1)).update(privilegeQueryVo);
    }

    @Test
    public void whenUpdateExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).update(privilegeQueryVo);
        Result result = privilegeController.update(privilegeQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("修改权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenUpdateSuccessfullyThenReturnTrueResult() {
        Result result = privilegeController.update(privilegeQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("修改权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdThenInvokeServiceFindById() {
        privilegeController.findById(privilegeQueryVo);
        verify(privilegeService, times(1)).findById(privilegeQueryVo);
    }

    @Test
    public void whenFindByIdExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).findById(privilegeQueryVo);
        Result result = privilegeController.findById(privilegeQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("获取指定权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdSuccessfullyThenReturnTrueResult() {
        Result result = privilegeController.findById(privilegeQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("获取指定权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdSuccessfullyThenReturnPojo() {
        PrivilegeQueryVo privilegeQueryVoResult = new PrivilegeQueryVo();
        doReturn(privilegeQueryVoResult).when(privilegeService).findById(privilegeQueryVo);
        Result result = privilegeController.findById(privilegeQueryVo);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void whenInsertThenInvokeServiceInsert() {
        privilegeController.insert(privilegeQueryVo);
        verify(privilegeService, times(1)).insert(privilegeQueryVo);
    }

    @Test
    public void whenInsertExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).insert(privilegeQueryVo);
        Result result = privilegeController.insert(privilegeQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("新增权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenInsertSuccessfullyThenReturnTrueResult() {
        Result result = privilegeController.insert(privilegeQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("新增权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

}
