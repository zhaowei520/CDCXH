package com.mzkj.controller.housingfund;

import com.google.common.collect.ImmutableList;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.housingfund.impl.HousingFundService;
import com.mzkj.vo.housingfund.HousingFundQueryVo;
import com.mzkj.vo.housingfund.HousingFundVo;

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
 * @Date:created in 10:27 2019-05-14
 */
public class HousingFundControllerSpec {
    HousingFundController housingFundController;
    HousingFundService housingFundService;
    HousingFundQueryVo housingFundQueryVo;
    HousingFundVo housingFundVo;
    String id;


    @Before
    public void before() {
        housingFundController = spy(HousingFundController.class);
        housingFundService = mock(HousingFundService.class);
        doReturn("admin").when(housingFundController).getUsernameFromSession();
        doReturn(true).when(housingFundController).verifyPermissionFromSession();
        doReturn(housingFundService).when(housingFundController).getHousingFundService();
        doReturn(new HashMap()).when(housingFundController).getButtonPermissionFromSession();
        housingFundVo = new HousingFundVo();
        housingFundVo.setTenantId("CDCXH");
        housingFundVo.setCreateUser("admin");
        id = "";
    }

    @Test
    public void whenSaveHousingFundThenInvokeServiceSaveAndReturnSuccessSpec() throws Exception {
        Result<HousingFundVo>  result = housingFundController.save(housingFundVo);
        verify(housingFundService, times(1)).save(housingFundVo);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void whenDeleteHousingFundThenInvokeServiceDeleteAndReturnSuccessSpec() throws Exception {
        Result result = housingFundController.delete(id);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void whenEditHousingFundThenInvokeServiceEditeAndReturnSuccessSpec() throws Exception {
        Result result = housingFundController.edit(housingFundVo);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void whenFindHousingFundByIdThenInvokeServiceFindByIdAndReturnSuccessSpec() throws Exception {
        Result result = housingFundController.findByID(id);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void whenGetHousingFundPageListThenInvokeServiceListAndReturnSuccessSpes()throws Exception {
        List<HousingFundQueryVo> housingFundQueryVos = ImmutableList.of(new HousingFundQueryVo(), new HousingFundQueryVo());
        PageInfo<HousingFundQueryVo> pageInfoExpected = new PageInfo<>();
        pageInfoExpected.setList(housingFundQueryVos);
        doReturn(pageInfoExpected).when(housingFundService).list(housingFundQueryVo);
        Result<PageData> result = housingFundController.list(housingFundQueryVo);
        Assert.assertEquals(pageInfoExpected, result.getData().get("varList"));
    }
}
