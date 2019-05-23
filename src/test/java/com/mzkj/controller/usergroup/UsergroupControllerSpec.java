package com.mzkj.controller.usergroup;

import com.google.common.collect.ImmutableList;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.PrivilegeOfUsergroupBean;
import com.mzkj.bean.UsergroupBean;
import com.mzkj.service.usergroup.impl.UsergroupService;
import com.mzkj.util.Const;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.usergroup.PrivilegeOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.PrivilegeUnselected2UsergroupQueryVo;
import com.mzkj.vo.usergroup.UserOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UserUnselected2UsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupVo;

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
    String usergroupId = "123";
    String[] userIds = {"123", "345"}, operations = {"write", "read"}, privilegeIds = {"567", "789"};
    private UsergroupVo usergroupVo;

    @Before
    public void before() {
        usergroupQueryVo = new UsergroupQueryVo();
        usergroupController = spy(UsergroupController.class);
        usergroupService = mock(UsergroupService.class);
        privilegeByUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
        doReturn(usergroupService).when(usergroupController).getUsergroupService();

        usergroupVo = new UsergroupVo();
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
        PageInfo pageInfo = new PageInfo();
        doReturn(pageInfo).when(usergroupService).list(usergroupQueryVo);
        Result result = usergroupController.list(usergroupQueryVo);
        Assert.assertEquals(pageInfo, result.getData());
    }

    @Test
    public void whenSaveThenInvoleServiceSave() {
        UsergroupVo usergroupVo = new UsergroupVo();
        usergroupController.save(usergroupVo);
        verify(usergroupService, times(1)).save(usergroupVo);
    }

    @Test
    public void whenServiceThrowsExceptionThenControllerReturnsFalseResult() {
        doThrow(Exception.class).when(usergroupService).save(usergroupVo);
        Result result = usergroupController.save(usergroupVo);
        Assert.assertFalse(result.isSuccess());
    }


    @Test
    public void whenSaveSuccessfullyThenControllerReturnTrueResult() {
        Result result = usergroupController.save(usergroupVo);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void whenSaveSuccessfullyThenRetureTryeResult() {
        Result<UsergroupQueryVo> result = usergroupController.save(usergroupVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("保存用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenUpdateThenInvokeServiceUpdate() {
        usergroupController.update(usergroupVo);
        verify(usergroupService, times(1)).update(usergroupVo);
    }

    @Test
    public void whenUpdateThrowsExceptionThenReturnErrorResult() {
        doThrow(Exception.class).when(usergroupService).update(usergroupVo);
        Result result = usergroupController.update(usergroupVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("更新用户组报错", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenUpdateSuccessfullyThenReturnSuccessResult() {
        Result result = usergroupController.update(usergroupVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("更新用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdThenInvokeServiceFindById() {
        usergroupController.findById(usergroupId);
        verify(usergroupService, times(1)).findById(usergroupId);
    }

    @Test
    public void whenFindByIdExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(usergroupService).findById(usergroupId);
        Result result = usergroupController.findById(usergroupId);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("查询指定用户组报错", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdSuccessfullyThenReturnTrueResult() {
        Result result = usergroupController.findById(usergroupId);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("查询指定用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdThenReturnPojo() {
        UsergroupBean usergroupBean = new UsergroupBean();
        doReturn(usergroupBean).when(usergroupService).findById(usergroupId);
        Result result = usergroupController.findById(usergroupId);
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
        UserOfUsergroupQueryVo userVo = new UserOfUsergroupQueryVo();
        usergroupController.findUsersByUsergroup(userVo);
        verify(usergroupService, times(1)).findUsersByUsergroup(userVo);
    }

    @Test
    public void whenFindUsersByUserGroupExceptionThenReturnFalseResult() {
        UserOfUsergroupQueryVo userVo = new UserOfUsergroupQueryVo();
        doThrow(RuntimeException.class).when(usergroupService).findUsersByUsergroup(userVo);
        Result result = usergroupController.findUsersByUsergroup(userVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("查询用户组对应用户异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersByUserGroupSuccessfullyThenReturnTrueResult() {
        UserOfUsergroupQueryVo userVo = new UserOfUsergroupQueryVo();
        Result result = usergroupController.findUsersByUsergroup(userVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("查询用户组对应用户成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersByUserGroupThenReturnResult() {
        UserOfUsergroupQueryVo userVo = new UserOfUsergroupQueryVo();
        PageInfo pageInfo = new PageInfo();
        doReturn(pageInfo).when(usergroupService).findUsersByUsergroup(userVo);
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
        usergroupController.addUser2Usergroup(usergroupId, userIds, operations);
        verify(usergroupService, times(1)).addUser2Usergroup(usergroupId, userIds, operations);
    }

    @Test
    public void whenAddUser2UsergroupFailThenReturnFalseResult() {
        UserOfUsergroupQueryVo userByUsergroupQueryVo = new UserOfUsergroupQueryVo();
        doThrow(RuntimeException.class).when(usergroupService).addUser2Usergroup(usergroupId, userIds, operations);
        Result result = usergroupController.addUser2Usergroup(usergroupId, userIds, operations);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("添加用户到用户组异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenAddUser2UsergroupSuccessfullyThenReturnTrueResult() {
        UserOfUsergroupQueryVo userByUsergroupQueryVo = new UserOfUsergroupQueryVo();
        Result result = usergroupController.addUser2Usergroup(usergroupId, userIds, operations);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("添加用户到用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenAddPrivilege2UsergroupThenInvokeService() {
        PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
        PrivilegeOfUsergroupBean privilegeOfUsergroupBean = new PrivilegeOfUsergroupBean();
        doReturn(privilegeOfUsergroupBean).when(usergroupService).convertVO2Bean(privilegeOfUsergroupQueryVo, PrivilegeOfUsergroupBean.class);
        usergroupController.addPrivilege2Usergroup(usergroupId, privilegeIds, operations);
        verify(usergroupService, times(1)).addPrivilege2Usergroup(usergroupId, privilegeIds, operations);
    }

    @Test
    public void whenAddPrivilege2UsergroupFailThenReturnFalseResult() {
        PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
        doThrow(RuntimeException.class).when(usergroupService).addPrivilege2Usergroup(usergroupId, privilegeIds, operations);
        Result result = usergroupController.addPrivilege2Usergroup(usergroupId, privilegeIds, operations);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("添加权限到用户组异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenAddPrivilege2UsergroupSuccessfullyThenReturnTrueResult() {
        PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
        PrivilegeOfUsergroupBean privilegeOfUsergroupBean = new PrivilegeOfUsergroupBean();
        doReturn(privilegeOfUsergroupBean).when(usergroupService).convertVO2Bean(privilegeOfUsergroupQueryVo, PrivilegeOfUsergroupBean.class);
        Result result = usergroupController.addPrivilege2Usergroup(usergroupId, privilegeIds, operations);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("添加权限到用户组成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenDeleteUsersOfUsergroupThenInvokeService() {
        String[] mappingIds = {"1", "2"};
        usergroupController.deleteUsersOfUsergroup(mappingIds);
        verify(usergroupService, times(1)).deleteUsersOfUsergroup(mappingIds);
    }

    @Test
    public void whenDeleteUsersOfUsergroupExceptionThenReturnFalseResult() {
        String[] mappingIds = {"1", "2"};
        doThrow(RuntimeException.class).when(usergroupService).deleteUsersOfUsergroup(mappingIds);
        Result result = usergroupController.deleteUsersOfUsergroup(mappingIds);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("删除用户组用户异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenDeleteUsersOfUsergroupSuccessfullyThenReturnTrueResult() {
        String[] mappingIds = {"1", "2"};
        Result result = usergroupController.deleteUsersOfUsergroup(mappingIds);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("删除用户组用户成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenDeletePrivilegesOfUsergroupThenInvokeService() {
        String[] mappingIds = {"1", "2"};
        usergroupController.deletePrivilegesOfUsergroup(mappingIds);
        verify(usergroupService, times(1)).deletePrivilegesOfUsergroup(mappingIds);
    }

    @Test
    public void whenDeletePrivilegesOfUsergroupFailThenReturnFalseResult() {
        String[] mappingIds = {"1", "2"};
        doThrow(RuntimeException.class).when(usergroupService).deletePrivilegesOfUsergroup(mappingIds);
        Result result = usergroupController.deletePrivilegesOfUsergroup(mappingIds);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("删除用户组权限异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenDeletePrivilegesOfUsergroupSuccessfullyThenReturnTrueResult() {
        String[] mappingIds = {"1", "2"};
        Result result = usergroupController.deletePrivilegesOfUsergroup(mappingIds);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("删除用户组权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersUnselectedThenInvokeService() {
        String usergroupId = "123";
        UserUnselected2UsergroupQueryVo userUnselected2UsergroupQueryVo = new UserUnselected2UsergroupQueryVo();
        usergroupController.findUsersUnselected(userUnselected2UsergroupQueryVo);
        verify(usergroupService, times(1)).findUsersUnselected(userUnselected2UsergroupQueryVo);
    }

    @Test
    public void whenFindUsersUnselectedExceptionThenReturnFalseResult() {
        String usergroupId = "123";
        UserUnselected2UsergroupQueryVo userUnselected2UsergroupQueryVo = new UserUnselected2UsergroupQueryVo();
        doThrow(RuntimeException.class).when(usergroupService).findUsersUnselected(userUnselected2UsergroupQueryVo);
        Result result = usergroupController.findUsersUnselected(userUnselected2UsergroupQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("获取用户组未选用户异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersUnselectedSuccessfullyThenReturnTrueResult() {
        String usergroupId = "123";
        UserUnselected2UsergroupQueryVo userUnselected2UsergroupQueryVo = new UserUnselected2UsergroupQueryVo();
        Result result = usergroupController.findUsersUnselected(userUnselected2UsergroupQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("获取用户组未选用户成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindPrivilegesUnselectedThenInvokeService() {
        PrivilegeUnselected2UsergroupQueryVo privilegesUnselected2UsergroupQueryVo =
                new PrivilegeUnselected2UsergroupQueryVo();
        usergroupController.findPrivilegesUnselected(privilegesUnselected2UsergroupQueryVo);
        verify(usergroupService, times(1)).findPrivilegesUnselected(privilegesUnselected2UsergroupQueryVo);
    }

    @Test
    public void whenFindPrivilegesUnselectedFailThenInvokeService() {
        PrivilegeUnselected2UsergroupQueryVo privilegesUnselected2UsergroupQueryVo =
                new PrivilegeUnselected2UsergroupQueryVo();
        doThrow(RuntimeException.class).when(usergroupService).findPrivilegesUnselected(privilegesUnselected2UsergroupQueryVo);
        Result result = usergroupController.findPrivilegesUnselected(privilegesUnselected2UsergroupQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("获取用户组未选权限异常", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindPrivilegesUnselectedSuccessfullyThenReturnTrueResult() {
        PrivilegeUnselected2UsergroupQueryVo privilegesUnselected2UsergroupQueryVo =
                new PrivilegeUnselected2UsergroupQueryVo();
        Result result = usergroupController.findPrivilegesUnselected(privilegesUnselected2UsergroupQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("获取用户组未选权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }


}
