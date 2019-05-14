package com.mzkj.controller.usergroup;

import com.google.common.collect.ImmutableList;

import com.mzkj.service.usergroup.impl.UsergroupService;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.usergroup.UsergroupQueryVo;

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

public class UsergroupControllerSpec {


    private UsergroupQueryVo usergroupQueryVo;
    private UsergroupController usergroupController;
    private UsergroupService usergroupService;

    @Before
    public void before() {
        usergroupQueryVo = new UsergroupQueryVo();
        usergroupController = spy(UsergroupController.class);
        usergroupService = mock(UsergroupService.class);
        doReturn(usergroupService).when(usergroupController).getUsergroupService();
    }

    @Test
    public void whenListThenInvokeServiceListSpec() throws Exception {
        usergroupController.list(usergroupQueryVo);
        verify(usergroupService, times(1)).list(usergroupQueryVo);
    }

    @Test
    public void whenServiceListExceptionThenReturnFalseSpec() throws Exception {
        doThrow(RuntimeException.class).when(usergroupService).list(usergroupQueryVo);
        Assert.assertFalse(usergroupController.list(usergroupQueryVo).isSuccess());
    }

    @Test
    public void whenSearchThenReturnList() throws Exception {
        List<UsergroupQueryVo> usergroupQueryVosExpected = ImmutableList.of(new UsergroupQueryVo(), new UsergroupQueryVo());
        doReturn(usergroupQueryVosExpected).when(usergroupService).list(usergroupQueryVo);
        Result result = usergroupController.list(usergroupQueryVo);
        Assert.assertEquals(usergroupQueryVosExpected, result.getData());
    }

    @Test
    public void whenSaveThenInvoleServiceSave() {
        usergroupController.save(usergroupQueryVo);
        verify(usergroupService, times(1)).save(usergroupQueryVo);
    }

    @Test
    public void whenServiceThrowsExceptionThenControllerReturnsFalseResult() {
        doThrow(Exception.class).when(usergroupService).save(usergroupQueryVo);
        Result result = usergroupController.save(usergroupQueryVo);
        Assert.assertFalse(result.isSuccess());
    }


    @Test
    public void whenSaveSuccessfullyThenControllerReturnTrueResult() {
        Result result = usergroupController.save(usergroupQueryVo);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void whenSaveSuccessfullyThenRetureWithID() {
        Result<UsergroupQueryVo> result = usergroupController.save(usergroupQueryVo);
        Assert.assertNotNull(result.getData().getUsergroupId());
    }

    @Test
    public void whenUpdateThenInvokeServiceUpdate() {
        usergroupController.update(usergroupQueryVo);
        verify(usergroupService, times(1)).update(usergroupQueryVo);
    }

    @Test
    public void whenUpdateThrowsExceptionThenReturnErrorResult() {
        doThrow(Exception.class).when(usergroupService).update(usergroupQueryVo);
        Result result = usergroupController.update(usergroupQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("更新用户组报错", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenUpdateSuccessfullyThenReturnSuccessResult() {
        Result result = usergroupController.update(usergroupQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("更新用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }
}
