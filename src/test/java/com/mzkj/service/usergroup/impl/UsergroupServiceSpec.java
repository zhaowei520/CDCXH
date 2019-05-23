package com.mzkj.service.usergroup.impl;

import com.google.common.collect.ImmutableList;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.MasterAccessOperationMappingBean;
import com.mzkj.bean.PrivilegeOfUsergroupBean;
import com.mzkj.bean.PrivilegeUnselected2UsergroupBean;
import com.mzkj.bean.UserBean;
import com.mzkj.bean.UserOfUsergroupBean;
import com.mzkj.bean.UserUnselected2UsergroupBean;
import com.mzkj.bean.UsergroupBean;
import com.mzkj.bean.UsergroupDeleteBean;
import com.mzkj.mapper.masterAccessOperation.MasterAccessOperationMapper;
import com.mzkj.mapper.privilege.PrivilegeMapper;
import com.mzkj.mapper.system.UserMapper;
import com.mzkj.mapper.usergroup.UsergroupMapper;
import com.mzkj.util.enums.AccessOperation;
import com.mzkj.util.enums.RelatingType;
import com.mzkj.vo.usergroup.PrivilegeOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.PrivilegeUnselected2UsergroupQueryVo;
import com.mzkj.vo.usergroup.UserOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UserUnselected2UsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupVo;

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

    private String usergroupId;
    private String tenantId;

    private String username;

    private UsergroupVo usergroupVo;
    private String[] userIds = {"123", "345"};
    private String[] operationParams = {"write", "read"};
    private String[] privilegeIds = {"123", "345"};

    @Before
    public void before() {
        usergroupId = "123";
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
        doReturn(usergroupBeanResult).when(usergroupMapper).findById(usergroupId);

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


        tenantId = "CDCXH";
        doReturn(tenantId).when(usergroupService).getTenantId();

        username = "duanhui";
        doReturn(username).when(usergroupService).getUsername();

        usergroupVo = new UsergroupVo();
    }

    @Test
    public void whenListThenInvokeMapperList() throws Exception {
        usergroupService.list(usergroupQueryVo);
        verify(usergroupMapper, times(1)).datalistPage(usergroupBean);
    }

    @Test
    public void whenListThenReturnUsergroupBeans() throws Exception {
        doReturn(usergroupBeans).when(usergroupMapper).datalistPage(usergroupBean);
        PageInfo pageInfo = usergroupService.list(usergroupQueryVo);
        Assert.assertEquals(usergroupQueryVosExpected.size(), pageInfo.getList().size());
    }

    @Test
    public void whenMapperSaveThrowsExceptionThenServiceThrowsException() {
        UsergroupVo usergroupVo = new UsergroupVo();
        doThrow(Exception.class).when(usergroupMapper).save(usergroupBean);
//        expectedException.expect(Exception.class);
        //TODO
        usergroupService.save(usergroupVo);

    }

    @Test
    public void whenInvokeServiceSaveThenInvokeMapperSave() {

        doReturn(usergroupBean).when(usergroupService).convertVO2Bean(usergroupVo, UsergroupBean.class);
        usergroupService.save(usergroupVo);
        //TODO
//        verify(usergroupMapper, times(1)).save(usergroupBean);
    }

    @Test
    public void whenMapperThrowsExceptionThenServiceThrowsException() {
        doThrow(Exception.class).when(usergroupMapper).update(usergroupBean);
        //TODO
//        expectedException.expect(Exception.class);
        usergroupService.update(usergroupVo);
    }

    @Test
    public void whenConvertThrowsExceptionThenServiceThrowsException() {
        //TODO 待补充
        //        expectedException.expect(RuntimeException.class);
        usergroupService.update(usergroupVo);
    }

    @Test
    public void whenFindByIdThenInvokeMapperFindById() {
        usergroupService.findById(usergroupId);
        verify(usergroupMapper, times(1)).findById(usergroupId);
    }

    @Test
    public void whenFindByIdThenReturnPojo() {
        UsergroupBean usergroupBean = usergroupService.findById(usergroupId);
        Assert.assertNotNull(usergroupBean);
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
        UserOfUsergroupQueryVo userVo = new UserOfUsergroupQueryVo();
        UserOfUsergroupBean userBean = new UserOfUsergroupBean();
        doReturn(userBean).when(usergroupService).convertVO2Bean(userVo, UserOfUsergroupBean.class);
        usergroupService.findUsersByUsergroup(userVo);
        verify(userMapper, times(1)).findUsersByUsergroup(userBean);
    }

    @Test
    public void whenFindUserByUsergroupThenReturnData() {
        UserOfUsergroupQueryVo userVo = new UserOfUsergroupQueryVo();
        UserOfUsergroupBean userBean = new UserOfUsergroupBean();
        List<UserBean> userBeanResult = ImmutableList.of(new UserBean());
        doReturn(userBean).when(usergroupService).convertVO2Bean(userVo, UserOfUsergroupBean.class);
        doReturn(userBeanResult).when(userMapper).findUsersByUsergroup(userBean);
        PageInfo pageInfo = usergroupService.findUsersByUsergroup(userVo);
        Assert.assertNotNull(pageInfo.getList());
    }

    @Test
    public void whenFindPrivilegesByUsergroupThenInvokeMapper() {
        usergroupService.findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
        verify(masterAccessOperationMapper, times(1)).findPrivilegesByUsergroup(privilegesByUsergroupBean);
    }

    @Test
    public void whenFindPrivilegesByUsergroupThenReturnData() {
        PageInfo pageInfo = usergroupService.findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
        Assert.assertNotNull(pageInfo);
    }

    @Test
    public void whenAddUser2UsergroupThenInvokeMapper() {
        String[] accessValues = userIds;
        String masterType = RelatingType.USERGROUP.getCode();
        String masterValue = usergroupId;
        String accessType = RelatingType.USER.getCode();
        String[] operations = operationParams;
        List<MasterAccessOperationMappingBean> masterAccessOperationMappingBeans = ImmutableList.of(new MasterAccessOperationMappingBean());
        doReturn(masterAccessOperationMappingBeans).when(usergroupService).doMasterAccessOperationMappingBeanList(masterType
                , masterValue, accessType, accessValues, operations);
        usergroupService.addUser2Usergroup(usergroupId, userIds, operationParams);
        verify(masterAccessOperationMapper, times(1)).addAccess2Master(masterAccessOperationMappingBeans);
    }

    @Test
    public void whenDoMasterAccessOperationMappingListThenGetList() {
        String[] accessValue = {"1", "2"};
        String masterType = "";
        String masterValue = "";
        String accessType = "";
        String[] operations = {AccessOperation.WRITE.getCode(), AccessOperation.WRITE.getCode()};
        doReturn("duanhui").when(usergroupService).getUsername();
        List<MasterAccessOperationMappingBean> masterAccessOperationMappingBeans =
                usergroupService.doMasterAccessOperationMappingBeanList(masterType, masterValue, accessType, accessValue, operations);
        Assert.assertEquals(masterAccessOperationMappingBeans.size(), accessValue.length);
    }

    @Test
    public void whenDoMasterAccessOperationMappingThenGetOne() {
        String masterType = "";
        String masterValue = "";
        String accessType = "";
        String accessValue = "";
        String operation = "";
        String masterAccessOperationMappingId = "";
        MasterAccessOperationMappingBean masterAccessOperationMappingBean
                = usergroupService.newMasterAccessOperationMapping(masterType, masterValue,
                accessType, accessValue, operation, masterAccessOperationMappingId);
        Assert.assertEquals(masterAccessOperationMappingBean.getMasterType(), masterType);
        Assert.assertEquals(masterAccessOperationMappingBean.getMasterValue(), masterType);
        Assert.assertEquals(masterAccessOperationMappingBean.getAccessType(), masterType);
        Assert.assertEquals(masterAccessOperationMappingBean.getAccessValue(), masterType);
        Assert.assertEquals(masterAccessOperationMappingBean.getOperation(), operation);
        Assert.assertEquals(masterAccessOperationMappingBean.getMasterAccessOperationMappingId(), masterAccessOperationMappingId);
    }

    @Test
    public void whenAddPrivilege2UsergroupThrowsExceptionThenThrowExceptionOut() {
        String masterType = RelatingType.USERGROUP.getCode();
        String masterValue = usergroupId;
        String accessType = RelatingType.PRIVILEGE.getCode();
        String[] accessValues = privilegeIds;
        String[] operations = operationParams;
        doThrow(RuntimeException.class).when(usergroupService)
                .doMasterAccessOperationMappingBeanList(masterType, masterValue, accessType, accessValues, operations);
        expectedException.expect(RuntimeException.class);
        usergroupService.addPrivilege2Usergroup(usergroupId, privilegeIds, operationParams);
    }

    @Test
    public void whenDeleteUsersOfUsergroupThenInvokeMapper() {
        String[] mappingIds = {"11", "22"};
        usergroupService.deleteUsersOfUsergroup(mappingIds);
        verify(masterAccessOperationMapper, times(1)).deleteMasterAccessOperationMapping(mappingIds, tenantId, username);
    }

    @Test
    public void whenDeletePrivilegesOfUsergroupThenInvokeMapper() {
        String[] mappingIds = {"11", "22"};
        usergroupService.deletePrivilegesOfUsergroup(mappingIds);
        verify(masterAccessOperationMapper, times(1)).deleteMasterAccessOperationMapping(mappingIds, tenantId, username);
    }

    @Test
    public void whenFindUsersUnselectedThenInvokeMapper() {
        UserUnselected2UsergroupQueryVo userUnselected2UsergroupQueryVo = new UserUnselected2UsergroupQueryVo();
        UserUnselected2UsergroupBean userUnselected2UsergroupBean = new UserUnselected2UsergroupBean();
        doReturn(userUnselected2UsergroupBean).when(usergroupService).convertVO2Bean(userUnselected2UsergroupQueryVo, UserUnselected2UsergroupBean.class);
        usergroupService.findUsersUnselected(userUnselected2UsergroupQueryVo);
        verify(userMapper, times(1)).findUsersUnselected(userUnselected2UsergroupBean);
    }

    @Test
    public void whenFindPrivilegesUnselectedThenInvokeMapper() {
        PrivilegeMapper privilegeMapper = mock(PrivilegeMapper.class);
        doReturn(privilegeMapper).when(usergroupService).getPrivilegeMapper();

        PrivilegeUnselected2UsergroupQueryVo privilegesUnselected2UsergroupQueryVo
                = new PrivilegeUnselected2UsergroupQueryVo();
        PrivilegeUnselected2UsergroupBean privilegesUnselected2UsergroupBean
                = new PrivilegeUnselected2UsergroupBean();
        doReturn(privilegesUnselected2UsergroupBean).when(usergroupService)
                .convertVO2Bean(privilegesUnselected2UsergroupQueryVo, PrivilegeUnselected2UsergroupBean.class);
        usergroupService.findPrivilegesUnselected(privilegesUnselected2UsergroupQueryVo);
        verify(privilegeMapper, times(1)).findPrivilegesUnselected(privilegesUnselected2UsergroupBean);
    }


}
