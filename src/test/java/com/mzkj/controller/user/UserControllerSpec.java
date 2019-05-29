package com.mzkj.controller.user;

import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.privilege.impl.PrivilegeService;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserControllerSpec {

    @Test
    public void whenFindPrivilegesByUserThenInvokeService() {
        PrivilegeService privilegeService = mock(PrivilegeService.class);
        SYSUserController userController = spy(SYSUserController.class);
        String userId = null;
        doReturn(privilegeService).when(userController).getPrivilegeService();
        userController.findAllPrivilegesByUser(userId);
        verify(privilegeService, times(1)).findPrivilegesByUser(userId);
    }

    @Test
    public void whenFindPrivilegesByUserFailThenReturnFalseResult() {
        PrivilegeService privilegeService = mock(PrivilegeService.class);
        SYSUserController userController = spy(SYSUserController.class);
        String userId = null;
        doReturn(privilegeService).when(userController).getPrivilegeService();
        doThrow(Exception.class).when(privilegeService).findPrivilegesByUser(userId);
        Result result = userController.findAllPrivilegesByUser(userId);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("根据用户查询权限报错", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());

    }

}
