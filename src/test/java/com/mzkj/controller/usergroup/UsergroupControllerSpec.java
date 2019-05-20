package com.mzkj.controller.usergroup;

import com.google.common.collect.ImmutableList;

import com.alibaba.fastjson.JSONArray;
import com.mzkj.bean.PrivilegeOfUsergroupBean;
import com.mzkj.service.usergroup.impl.UsergroupService;
import com.mzkj.util.Const;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.system.UserVo;
import com.mzkj.vo.usergroup.PrivilegeOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UserOfUsergroupQueryVo;
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
    private PrivilegeOfUsergroupQueryVo privilegeByUsergroupQueryVo;

    @Before
    public void before() {
        usergroupQueryVo = new UsergroupQueryVo();
        usergroupController = spy(UsergroupController.class);
        usergroupService = mock(UsergroupService.class);
        privilegeByUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
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

    @Test
    public void whenFindByIdThenInvokeServiceFindById() {
        usergroupController.findById(usergroupQueryVo);
        verify(usergroupService, times(1)).findById(usergroupQueryVo);
    }

    @Test
    public void whenFindByIdExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(usergroupService).findById(usergroupQueryVo);
        Result result = usergroupController.findById(usergroupQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("查询指定用户组报错", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdSuccessfullyThenReturnTrueResult() {
        Result result = usergroupController.findById(usergroupQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("查询指定用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdThenReturnPojo() {
        UsergroupQueryVo usergroupQueryVoResult = new UsergroupQueryVo();
        doReturn(usergroupQueryVoResult).when(usergroupService).findById(usergroupQueryVo);
        Result result = usergroupController.findById(usergroupQueryVo);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void whenTreeThenThenInvokeServiceTree() {
        usergroupController.findByParentId();
        verify(usergroupService, times(1)).findByParentId(Const.ROOF_ID_FOR_TREE);
    }

    @Test
    public void whenTreeExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(usergroupService).findByParentId(Const.ROOF_ID_FOR_TREE);
        Result result = usergroupController.findByParentId();
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("查询树状用户组异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenTreeSuccessfullyThenReturnTrueResult() {
        Result result = usergroupController.findByParentId();
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("查询树状用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenTreeThenReturnTree() {
        JSONArray treeResult = new JSONArray();
        doReturn(treeResult).when(usergroupService).findByParentId(Const.ROOF_ID_FOR_TREE);
        Result result = usergroupController.findByParentId();
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void whenDeleteThenInvokeServiceDelete() {
        List<String> ids = ImmutableList.of("1", "2");
        usergroupController.delete(ids);
        verify(usergroupService, times(1)).delete(ids);
    }

    @Test
    public void whenDeleteExceptionThenReturnFalseResult() {
        List<String> ids = ImmutableList.of("1", "2");
        doThrow(RuntimeException.class).when(usergroupService).delete(ids);
        Result result = usergroupController.delete(ids);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("删除用户组异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenDeleteSuccessfullyThenReturnTrueResult() {
        List<String> ids = ImmutableList.of("1", "2");
        Result result = usergroupController.delete(ids);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("删除用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersByUserGroupThenInvokeServiceFindUsersByUserGroup() {
        UserVo userVo = new UserVo();
        usergroupController.findUsersByUsergroup(userVo);
        verify(usergroupService, times(1)).findUsersByUsergroup(userVo);
    }

    @Test
    public void whenFindUsersByUserGroupExceptionThenReturnFalseResult() {
        UserVo userVo = new UserVo();
        doThrow(RuntimeException.class).when(usergroupService).findUsersByUsergroup(userVo);
        Result result = usergroupController.findUsersByUsergroup(userVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("查询用户组对应用户异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersByUserGroupSuccessfullyThenReturnTrueResult() {
        UserVo userVo = new UserVo();
        Result result = usergroupController.findUsersByUsergroup(userVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("查询用户组对应用户成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersByUserGroupThenReturnResult() {
        UserVo userVo = new UserVo();
        List<UserVo> userVos = ImmutableList.of(userVo);
        doReturn(userVos).when(usergroupService).findUsersByUsergroup(userVo);
        Result result = usergroupController.findUsersByUsergroup(userVo);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void whenFindPrivilegesByUsergroupThenInvokeServiceFindPrivilegesByUsergroup() {
        usergroupController.findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
        verify(usergroupService, times(1)).findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
    }

    @Test
    public void whenFindPrivilegesByUsergroupFailThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(usergroupService).findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
        Result result = usergroupController.findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("查询用户组对应权限异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindPrivilegesByUsergroupThenReturnTrueResult() {
        Result result = usergroupController.findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("查询用户组对应权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenAddUser2UsergroupThenInvokeServiceAddUser2Usergroup() {
        UserOfUsergroupQueryVo userByUsergroupQueryVo = new UserOfUsergroupQueryVo();
        usergroupController.addUser2Usergroup(userByUsergroupQueryVo);
        verify(usergroupService, times(1)).addUser2Usergroup(userByUsergroupQueryVo);
    }

    @Test
    public void whenAddUser2UsergroupFailThenReturnFalseResult() {
        UserOfUsergroupQueryVo userByUsergroupQueryVo = new UserOfUsergroupQueryVo();
        doThrow(RuntimeException.class).when(usergroupService).addUser2Usergroup(userByUsergroupQueryVo);
        Result result = usergroupController.addUser2Usergroup(userByUsergroupQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("添加用户到用户组异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenAddUser2UsergroupSuccessfullyThenReturnTrueResult() {
        UserOfUsergroupQueryVo userByUsergroupQueryVo = new UserOfUsergroupQueryVo();
        Result result = usergroupController.addUser2Usergroup(userByUsergroupQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("添加用户到用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenAddPrivilege2UsergroupThenInvokeService() {
        PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
        PrivilegeOfUsergroupBean privilegeOfUsergroupBean = new PrivilegeOfUsergroupBean();
        doReturn(privilegeOfUsergroupBean).when(usergroupService).convertVO2Bean(privilegeOfUsergroupQueryVo, PrivilegeOfUsergroupBean.class);
        usergroupController.addPrivilege2Usergroup(privilegeOfUsergroupQueryVo);
        verify(usergroupService, times(1)).addPrivilege2Usergroup(privilegeOfUsergroupQueryVo);
    }

    @Test
    public void whenAddPrivilege2UsergroupFailThenReturnFalseResult() {
        PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
        doThrow(RuntimeException.class).when(usergroupService).addPrivilege2Usergroup(privilegeOfUsergroupQueryVo);
        Result result = usergroupController.addPrivilege2Usergroup(privilegeOfUsergroupQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("添加权限到用户组异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenAddPrivilege2UsergroupSuccessfullyThenReturnTrueResult() {
        PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
        PrivilegeOfUsergroupBean privilegeOfUsergroupBean = new PrivilegeOfUsergroupBean();
        doReturn(privilegeOfUsergroupBean).when(usergroupService).convertVO2Bean(privilegeOfUsergroupQueryVo, PrivilegeOfUsergroupBean.class);
        Result result = usergroupController.addPrivilege2Usergroup(privilegeOfUsergroupQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("添加权限到用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }
}
