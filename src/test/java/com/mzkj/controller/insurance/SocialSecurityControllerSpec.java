package com.mzkj.controller.insurance;

import com.google.common.collect.ImmutableList;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.facade.vo.Result;
import com.mzkj.facade.vo.SocialSecurityQueryVo;
import com.mzkj.facade.vo.SocialSecurityVo;
import com.mzkj.service.insurance.impl.SocialSecurityService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @Author: luosc
 * @Description:
 * @Date:created in 13:38 2019-05-13
 */
public class SocialSecurityControllerSpec {

    SocialSecurityController socialSecurityController;
    SocialSecurityService socialSecurityService;
    SocialSecurityQueryVo socialSecurityQueryVo;
    SocialSecurityVo socialSecurityVo;
    String id;
    @Before
    public void before() {
        socialSecurityController = spy(SocialSecurityController.class);
        socialSecurityService = mock(SocialSecurityService.class);
        doReturn("admin").when(socialSecurityController).getUsernameFromSession();
        doReturn(true).when(socialSecurityController).verifyPermissionFromSession();
        doReturn(socialSecurityService).when(socialSecurityController).getSocialSecurityService();
        doReturn(new HashMap()).when(socialSecurityController).getButtonPermissionFromSession();
        socialSecurityVo = new SocialSecurityVo();
        socialSecurityVo.setTenantId("CDCXH");
        socialSecurityVo.setCreateUser("admin");
        id="";
    }

    /**
     * 保存时调用SocialSecurityManager,并返回result.getStatus==200
     * return
     * Author luosc
     * param
     * Date 2019-05-13 13:41
     */
    @Test
    public void whenSaveSocialSecurityThenInvokeServiceSaveAndReturnSuccessSpec() throws Exception {
        Result<SocialSecurityVo> result = socialSecurityController.save(socialSecurityVo);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 删除
     * return
     * Author luosc
     * param
     * Date 2019-05-14 8:59
     */
    @Test
    public void whenDeleteSocialSecurityThenInvokeServiceDeleteAndReturnSuccessSpec() throws Exception{
        Result result = socialSecurityController.delete(id);
        verify(socialSecurityService, times(1)).delete(id);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 修改
     * return
     * Author luosc
     * param
     * Date 2019-05-14 10:03
     */
    @Test
    public void whenEditSocialSecurityThenInvokeServiceEditeAndReturnSuccessSpec() throws Exception{
        Result result = socialSecurityController.edit(socialSecurityVo);
        verify(socialSecurityService, times(1)).edit(socialSecurityVo);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 根据ID查询
     * return
     * Author luosc
     * param
     * Date 2019-05-14 10:15
     */
    @Test
    public void whenFindSocialSecurityByIdThenInvokeServiceFindByIdAndReturnSuccessSpec() throws Exception{
        Result result = socialSecurityController.findById(id);
        verify(socialSecurityService, times(1)).findById(id);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void whenGetPageListSocialSecurityThenInvokeServiceListAndReturnSuccessSpes()throws Exception {
        List<SocialSecurityQueryVo> socialSecurityQueryVos = ImmutableList.of(new SocialSecurityQueryVo(), new SocialSecurityQueryVo());
        PageInfo<SocialSecurityQueryVo> pageInfoExpected = new PageInfo<>();
        pageInfoExpected.setList(socialSecurityQueryVos);
        doReturn(pageInfoExpected).when(socialSecurityService).list(socialSecurityQueryVo);
        Result<PageData> result = socialSecurityController.list(socialSecurityQueryVo);
        Assert.assertEquals(pageInfoExpected, result.getData().get("varList"));
    }
}
