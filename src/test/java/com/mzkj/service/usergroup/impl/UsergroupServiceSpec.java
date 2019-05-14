package com.mzkj.service.usergroup.impl;

import com.google.common.collect.ImmutableList;

import com.mzkj.bean.UsergroupBean;
import com.mzkj.mapper.usergroup.UsergroupMapper;
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

    @Before
    public void before() {
        usergroupQueryVo = new UsergroupQueryVo();
        usergroupBean = new UsergroupBean();
        usergroupService = spy(UsergroupService.class);
        usergroupMapper = mock(UsergroupMapper.class);
        doReturn(usergroupMapper).when(usergroupService).getUsergroupMapper();
        doReturn(usergroupBean).when(usergroupService).convertVO2Bean(usergroupQueryVo, UsergroupBean.class);
        usergroupQueryVosExpected = ImmutableList.of(new UsergroupQueryVo());
        usergroupBeans = ImmutableList.of(new UsergroupBean());
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
}
