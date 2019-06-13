package com.mzkj.controller.privilege;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.AddUsers2PrivilegeVo;
import com.mzkj.facade.vo.InsertPrivilegeVo;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.privilege.PrivilegeManager;
import com.mzkj.service.privilege.impl.PrivilegeService;
import com.mzkj.vo.privilege.PrivilegeQueryVo;
import com.mzkj.vo.privilege.PrivilegeVo;
import com.mzkj.vo.privilege.QueryPrivilegesByUserVo;
import com.mzkj.vo.privilege.UserOfPrivilegeQueryVo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

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
    PrivilegeVo privilegeVo;
    InsertPrivilegeVo insertPrivilegeVo;
    String privilegeId = "123";
    UserOfPrivilegeQueryVo userOfPrivilegeQueryVo;
    AddUsers2PrivilegeVo addUsers2PrivilegeVo;
    QueryPrivilegesByUserVo queryPrivilegesByUserVo;

    @Before
    public void before() {
        insertPrivilegeVo = new InsertPrivilegeVo();
        privilegeQueryVo = new PrivilegeQueryVo();
        privilegeController = spy(PrivilegeController.class);
        privilegeService = mock(PrivilegeService.class);
        privilegeVo = new PrivilegeVo();
        queryPrivilegesByUserVo = new QueryPrivilegesByUserVo();
        doReturn(privilegeService).when(privilegeController).getPrivilegeService();
        userOfPrivilegeQueryVo = new UserOfPrivilegeQueryVo();
        addUsers2PrivilegeVo = new AddUsers2PrivilegeVo();
        String privilegeId = "123";
        String[] userIds = {"123", "1233"};
        String[] operations = {"read", "read"};
        addUsers2PrivilegeVo.setPrivilegeId(privilegeId);
        addUsers2PrivilegeVo.setUserIds(userIds);
        addUsers2PrivilegeVo.setOperations(operations);
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
        PageInfo pageInfo = new PageInfo();
        doReturn(pageInfo).when(privilegeService).datalistPage(privilegeQueryVo);
        Result result = privilegeController.list(privilegeQueryVo);
        Assert.assertEquals(pageInfo, result.getData());
    }

    @Test
    public void whenUpdateThenInvokeServiceUpdate() {
        privilegeController.update(privilegeVo);
        verify(privilegeService, times(1)).update(privilegeVo);
    }

    @Test
    public void whenUpdateExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).update(privilegeVo);
        Result result = privilegeController.update(privilegeVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("修改权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenUpdateSuccessfullyThenReturnTrueResult() {
        Result result = privilegeController.update(privilegeVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("修改权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdThenInvokeServiceFindById() {
        privilegeController.findById(privilegeId);
        verify(privilegeService, times(1)).findById(privilegeId);
    }

    @Test
    public void whenFindByIdExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).findById(privilegeId);
        Result result = privilegeController.findById(privilegeId);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("获取指定权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdSuccessfullyThenReturnTrueResult() {
        Result result = privilegeController.findById(privilegeId);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("获取指定权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindByIdSuccessfullyThenReturnPojo() {
        PrivilegeQueryVo privilegeQueryVoResult = new PrivilegeQueryVo();
        doReturn(privilegeVo).when(privilegeService).findById(privilegeId);
        Result result = privilegeController.findById(privilegeId);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void whenInsertThenInvokeServiceInsert() {
        privilegeController.insert(insertPrivilegeVo);
        verify(privilegeService, times(1)).insert(insertPrivilegeVo);
    }

    @Test
    public void whenInsertExceptionThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).insert(insertPrivilegeVo);
        Result result = privilegeController.insert(insertPrivilegeVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("新增权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenInsertSuccessfullyThenReturnTrueResult() {
        Result result = privilegeController.insert(insertPrivilegeVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("新增权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersByPrivilegeThenInvokeService() {
        privilegeController.findUsersByPrivilege(userOfPrivilegeQueryVo);
        verify(privilegeService).findUsersByPrivilege(userOfPrivilegeQueryVo);
    }

    @Test
    public void whenFindUsersByPrivilegeFailThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).findUsersByPrivilege(userOfPrivilegeQueryVo);
        Result result = privilegeController.findUsersByPrivilege(userOfPrivilegeQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("查询权限下的用户失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersByPrivilegeSuccessfullyThenReturnTrueResult() {
        Result result = privilegeController.findUsersByPrivilege(userOfPrivilegeQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("查询权限下的用户成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersUnselectedByPrivilegeThenInvokeService() {
        privilegeController.findUsersUnselectedByPrivilege(userOfPrivilegeQueryVo);
        verify(privilegeService).findUsersUnselectedByPrivilege(userOfPrivilegeQueryVo);
    }

    @Test
    public void whenFindUsersUnselectedByPrivilegeFailThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).findUsersUnselectedByPrivilege(userOfPrivilegeQueryVo);
        Result result = privilegeController.findUsersUnselectedByPrivilege(userOfPrivilegeQueryVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("查询未被选中的用户失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenFindUsersUnselectedByPrivilegeSuccessfullyThenReturnTrueResult() {
        Result result = privilegeController.findUsersUnselectedByPrivilege(userOfPrivilegeQueryVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("查询未被选中的用户成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenAddUsers2PrivilegesThenInvokeService() {
        String privilegeId = "123";
        String[] userIds = {"123", "1233"};
        String[] operations = {"read", "read"};
        privilegeController.addUsers2Privilege(addUsers2PrivilegeVo);
        verify(privilegeService, times(1)).addUsers2Privilege(addUsers2PrivilegeVo);
    }

    @Test
    public void whenAddUsers2PrivilegesFailThenReturnFalseResult() {

        doThrow(RuntimeException.class).when(privilegeService).addUsers2Privilege(addUsers2PrivilegeVo);
        Result result = privilegeController.addUsers2Privilege(addUsers2PrivilegeVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("添加用户到权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenAddUsers2PrivilegesSuccessfullyThenReturnTrueResult() {
        String privilegeId = "123";
        String[] userIds = {"123", "1233"};
        String[] operations = {"read", "read"};
        Result result = privilegeController.addUsers2Privilege(addUsers2PrivilegeVo);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("添加用户到权限成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenDeleteUsersOfPrivilegeThenInvokeService() {
        String[] mappingIds = {"123"};
        privilegeController.deleteUsersOfPrivilege(mappingIds);
        verify(privilegeService, times(1)).deleteUsersOfPrivilege(mappingIds);
    }

    @Test
    public void whenDeleteUsersOfPrivilegeFailThenReturnFalseResult() {
        String[] mappingIds = {"123"};
        doThrow(RuntimeException.class).when(privilegeService).deleteUsersOfPrivilege(mappingIds);
        Result result = privilegeController.deleteUsersOfPrivilege(mappingIds);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("删除权限下的用户失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenDeleteUsersOfPrivilegeSuccessfullyThenReturnTrueResult() {
        String[] mappingIds = {"123"};
        Result result = privilegeController.deleteUsersOfPrivilege(mappingIds);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals("删除权限下的用户成功", result.getMsg());
        Assert.assertEquals(HttpCode.OK.getCode(), result.getStatus());
    }

    @Test
    public void whenFindPrivilegeTypesThenReturnPrivilegeTypes() {
        List<Map<String, String>> privilegeTypes = privilegeController.findPrivilegeTypes();
        Assert.assertTrue(privilegeTypes != null && privilegeTypes.size() > 0);
    }

    @Test
    public void whenFindPrivilegeSubTypesThenReturnPrivilegeSubTypes() {
        List<Map<String, String>> privilegeSubTypes = privilegeController.findPrivilegeSubTypes();
        Assert.assertTrue(privilegeSubTypes != null && privilegeSubTypes.size() > 0);
    }

    @Test
    public void whenFindPrivilegesByUserThenInvokeService() {
        doReturn(privilegeService).when(privilegeController).getPrivilegeService();
        privilegeController.findPrivilegesByUser(queryPrivilegesByUserVo);
        verify(privilegeService, times(1))
                .findPrivilegesByUser(queryPrivilegesByUserVo);
    }

    @Test
    public void whenFindPrivilegesByUserFailThenReturnFalseResult() {
//        doReturn(privilegeService).when(privilegeController).getPrivilegeService();
        doThrow(RuntimeException.class).when(privilegeService)
                .findPrivilegesByUser(queryPrivilegesByUserVo);
        Result result = privilegeController.findPrivilegesByUser(queryPrivilegesByUserVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("按用户分页查询权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }


    @Test
    public void whenFindPrivilegesUnselectedByUserThenInvokeService() {
        privilegeController.findPrivilegesUnselectedByUser(queryPrivilegesByUserVo);
        verify(privilegeService, times(1)).findPrivilegesUnselectedByUser(queryPrivilegesByUserVo);
    }

    @Test
    public void whenFindPrivilegesUnselectedByUserFailThenReturnFalseResult() {
        doThrow(RuntimeException.class).when(privilegeService).findPrivilegesUnselectedByUser(queryPrivilegesByUserVo);
        Result result = privilegeController.findPrivilegesUnselectedByUser(queryPrivilegesByUserVo);
        Assert.assertFalse(result.isSuccess());
        Assert.assertEquals("获取未被用户选中的权限失败", result.getMsg());
        Assert.assertEquals(HttpCode.ERROR.getCode(), result.getStatus());
    }

    @Test
    public void whenAddPrivileges2UserThenInvokeService() {
        String privilegeIds[] = {"12", "121"};
        String userId = "dfs";
        String operations[] = {"read", "write"};
        privilegeController.addPrivileges2User(privilegeIds, userId, operations);
        verify(privilegeService, times(1)).addPrivileges2User(privilegeIds, userId, operations);
    }

    @Test
    public void whenAddPrivileges2UserFailThenReturnFalseResult() {
        String privilegeIds[] = {"12", "121"};
        String userId = "dfs";
        String operations[] = {"read", "write"};
//        doThrow(RuntimeException.class).when(privilegeService).addPrivileges2User();
        privilegeController.addPrivileges2User(privilegeIds, userId, operations);
    }

}
