package com.mzkj.service.usergroup.impl;

import com.google.common.collect.ImmutableList;

import com.alibaba.fastjson.JSONArray;
import com.mzkj.bean.PrivilegeOfUsergroupBean;
import com.mzkj.bean.UserBean;
import com.mzkj.bean.UserOfUsergroupBean;
import com.mzkj.bean.UsergroupBean;
import com.mzkj.bean.UsergroupDeleteBean;
import com.mzkj.mapper.masterAccessOperation.MasterAccessOperationMapper;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.mapper.usergroup.UsergroupMapper;
import com.mzkj.vo.system.UserVo;
import com.mzkj.vo.usergroup.PrivilegeOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UserOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupQueryVo;

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

public class UsergroupServiceSpec {


    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private UsergroupQueryVo usergroupQueryVo;
    private UsergroupBean usergroupBean;
    private UsergroupService usergroupService;
    private UsergroupMapper usergroupMapper;
    private List<UsergroupQueryVo> usergroupQueryVosExpected;
    private List<UsergroupBean> usergroupBeans;
    PrivilegeOfUsergroupBean privilegesByUsergroupBean;
    PrivilegeOfUsergroupQueryVo privilegeByUsergroupQueryVo;
    MasterAccessOperationMapper masterAccessOperationMapper;
    private UsergroupBean usergroupBeanResult;
    private String roof = "ROOF";
    private UsergroupBean usergroupBeanJack;
    private UsergroupBean usergroupBeanPaul;
    private UserMapper userMapper;

    @Before
    public void before() {
        usergroupQueryVo = new UsergroupQueryVo();
        usergroupBean = new UsergroupBean();
        usergroupService = spy(UsergroupService.class);
        usergroupMapper = mock(UsergroupMapper.class);
        userMapper = mock(UserMapper.class);
        doReturn(usergroupMapper).when(usergroupService).getUsergroupMapper();
        doReturn(userMapper).when(usergroupService).getUserMapper();
        doReturn(usergroupBean).when(usergroupService).convertVO2Bean(usergroupQueryVo, UsergroupBean.class);
        usergroupQueryVosExpected = ImmutableList.of(new UsergroupQueryVo(), new UsergroupQueryVo());
//        usergroupBeans = ImmutableList.of(new UsergroupBean());
        usergroupBeanResult = new UsergroupBean();
        doReturn(usergroupBeanResult).when(usergroupMapper).findById(usergroupBean);

        usergroupBeanJack = new UsergroupBean();
        usergroupBeanJack.setUsergroupId("1");
        usergroupBeanJack.setParentId(roof);
        usergroupBeanJack.setName("Jack");

        usergroupBeanPaul = new UsergroupBean();
        usergroupBeanPaul.setUsergroupId("2");
        usergroupBeanPaul.setParentId(roof);
        usergroupBeanPaul.setName("Paul");

        usergroupBeans = ImmutableList.of(usergroupBeanJack, usergroupBeanPaul);

        privilegesByUsergroupBean = new PrivilegeOfUsergroupBean();
        privilegeByUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
        masterAccessOperationMapper = mock(MasterAccessOperationMapper.class);
        doReturn(masterAccessOperationMapper).when(usergroupService).getMasterAccessOperationMapper();
        doReturn(privilegesByUsergroupBean).when(usergroupService).convertVO2Bean(privilegeByUsergroupQueryVo, PrivilegeOfUsergroupBean.class);
    }

    @Test
    public void whenListThenInvokeMapperList() throws Exception {
        usergroupService.list(usergroupQueryVo);
        verify(usergroupMapper, times(1)).datalistPage(usergroupBean);
    }

    @Test
    public void whenListThenReturnUsergroupBeans() throws Exception {
        doReturn(usergroupBeans).when(usergroupMapper).datalistPage(usergroupBean);
        List<UsergroupQueryVo> usergroupQueryVosActual = usergroupService.list(usergroupQueryVo);
        Assert.assertEquals(usergroupQueryVosExpected.size(), usergroupQueryVosActual.size());
    }

    @Test
    public void whenMapperSaveThrowsExceptionThenServiceThrowsException() {
        doThrow(Exception.class).when(usergroupMapper).save(usergroupBean);
        expectedException.expect(Exception.class);
        usergroupService.save(usergroupQueryVo);

    }

    @Test
    public void whenInvokeServiceSaveThenInvokeMapperSave() {
        usergroupService.save(usergroupQueryVo);
        verify(usergroupMapper, times(1)).save(usergroupBean);
    }

    @Test
    public void whenMapperThrowsExceptionThenServiceThrowsException() {
        doThrow(Exception.class).when(usergroupMapper).update(usergroupBean);
        expectedException.expect(Exception.class);
        usergroupService.update(usergroupQueryVo);
    }

    @Test
    public void whenConvertThrowsExceptionThenServiceThrowsException() {
        doThrow(RuntimeException.class).when(usergroupService).convertVO2Bean(usergroupQueryVo, UsergroupBean.class);
        expectedException.expect(RuntimeException.class);
        usergroupService.update(usergroupQueryVo);
    }

    @Test
    public void whenFindByIdThenInvokeMapperFindById() {
        usergroupService.findById(usergroupQueryVo);
        verify(usergroupMapper, times(1)).findById(usergroupBean);
    }

    @Test
    public void whenFindByIdThenReturnPojo() {
        UsergroupQueryVo usergroupQueryVoResult = usergroupService.findById(usergroupQueryVo);
        Assert.assertNotNull(usergroupQueryVoResult);
    }

    @Test
    public void whenTreeThenInvokeMapperFindByParentId() {
        usergroupService.findByParentId(roof);
        verify(usergroupMapper, times(1)).findByParentId(roof);
    }

    @Test
    public void whenTreeThenReturnJSONArray() {
        doReturn(usergroupBeans).when(usergroupMapper).findByParentId(roof);
        doReturn(null).when(usergroupService).findByParentId(usergroupBeanJack.getUsergroupId());
        doReturn(null).when(usergroupService).findByParentId(usergroupBeanPaul.getUsergroupId());
        JSONArray usergroupBeanChildren = usergroupService.findByParentId(roof);
        Assert.assertEquals(usergroupBeans.size(), usergroupBeanChildren.size());
    }

    @Test
    public void whenDeleteThenInvokeMapperDelete() {
        List<String> ids = ImmutableList.of("1", "2");
        UsergroupDeleteBean usergroupDeleteBean = new UsergroupDeleteBean();
        usergroupDeleteBean.setIds(ids);
//        doReturn("duanhui").when(usergroupService).getUsername();
        doReturn(usergroupDeleteBean).when(usergroupService).getUsergroupDeleteBean(ids);
        usergroupService.delete(ids);
        verify(usergroupMapper, times(1)).delete(usergroupDeleteBean);
    }

    @Test
    public void whenFindUserByUsergroupThenInvokeMapper() {
        UserVo userVo = new UserVo();
        UserBean userBean = new UserBean();
        doReturn(userBean).when(usergroupService).convertVO2Bean(userVo, UserBean.class);
        usergroupService.findUsersByUsergroup(userVo);
        verify(userMapper, times(1)).findUsersByUsergroup(userBean);
    }

    @Test
    public void whenFindUserByUsergroupThenReturnData() {
        UserVo userVo = new UserVo();
        UserBean userBean = new UserBean();
        List<UserBean> userBeanResult = ImmutableList.of(new UserBean());
        doReturn(userBean).when(usergroupService).convertVO2Bean(userVo, UserBean.class);
        doReturn(userBeanResult).when(userMapper).findUsersByUsergroup(userBean);
        List<UserVo> userVoResultActual = usergroupService.findUsersByUsergroup(userVo);
        Assert.assertNotNull(userVoResultActual);
    }

    @Test
    public void whenFindPrivilegesByUsergroupThenInvokeMapper() {
        usergroupService.findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
        verify(masterAccessOperationMapper, times(1)).findPrivilegesByUsergroup(privilegesByUsergroupBean);
    }

    @Test
    public void whenFindPrivilegesByUsergroupThenReturnData() {
        List<PrivilegeOfUsergroupQueryVo> privilegeByUsergroupQueryVos = usergroupService.findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
        Assert.assertNotNull(privilegeByUsergroupQueryVos);
    }

    @Test
    public void whenAddUser2UsergroupThenInvokeMapper() {
        UserOfUsergroupQueryVo userOfUsergroupQueryVo = new UserOfUsergroupQueryVo();
        UserOfUsergroupBean userOfUsergroupBean = new UserOfUsergroupBean();
        doReturn(userOfUsergroupBean).when(usergroupService).convertVO2Bean(userOfUsergroupQueryVo, UserOfUsergroupBean.class);
        usergroupService.addUser2Usergroup(userOfUsergroupQueryVo);
        verify(masterAccessOperationMapper, times(1)).addUser2Usergroup(userOfUsergroupBean);
    }

    @Test
    public void whenAddPrivilege2UsergroupThrowsExceptionThenThrowExceptionOut() {
        PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo = new PrivilegeOfUsergroupQueryVo();
        PrivilegeOfUsergroupBean privilegeOfUsergroupBean = new PrivilegeOfUsergroupBean();
        doReturn(privilegeOfUsergroupBean).when(usergroupService).convertVO2Bean(privilegeOfUsergroupQueryVo, PrivilegeOfUsergroupBean.class);
        doThrow(RuntimeException.class).when(masterAccessOperationMapper).addPrivilege2Usergroup(privilegeOfUsergroupBean);
        expectedException.expect(RuntimeException.class);
        usergroupService.addPrivilege2Usergroup(privilegeOfUsergroupQueryVo);

    }
}
