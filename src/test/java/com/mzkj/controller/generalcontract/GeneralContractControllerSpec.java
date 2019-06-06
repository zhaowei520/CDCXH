package com.mzkj.controller.generalcontract;

import com.google.common.collect.ImmutableList;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.controller.general.GeneralContractController;
import com.mzkj.facade.vo.GeneralContractVo;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.general.impl.GeneralContractService;
import com.mzkj.vo.general.GeneralContractQueryVo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * @Author: luosc
 * @Description:
 * @Date:created in 10:32 2019-06-06
 */
public class GeneralContractControllerSpec {
    GeneralContractController generalContractController;
    GeneralContractService generalContractService;
    GeneralContractVo generalContractVo;
    GeneralContractQueryVo generalContractQueryVo;
    String id = "";
    String businessId = "";

    @Before
    public void before() {
        generalContractController = spy(GeneralContractController.class);
        generalContractService = mock(GeneralContractService.class);
        doReturn("admin").when(generalContractController).getUsernameFromSession();
        doReturn(true).when(generalContractController).verifyPermissionFromSession();
        doReturn(generalContractService).when(generalContractController).getGeneralContractService();
        doReturn(new HashMap()).when(generalContractController).getButtonPermissionFromSession();
        generalContractVo = new GeneralContractVo();
        generalContractVo.setTenantId("CDCXH");
        generalContractVo.setCreateUser("admin");
        id = "";
        businessId = "";
    }

    /**
     * 保存时执行
     * return
     * Author luosc
     * param
     * Date 2019-06-06 10:48
     */
    @Test
    public void whenSaveGeneralContractThenInvokeServiceSaveAndReturnSuccessSpec() throws Exception {
        Result<GeneralContractVo> result = generalContractController.save(generalContractVo);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 从页面保存数据时执行
     * return
     * Author luosc
     * param
     * Date 2019-06-06 10:54
     */
    @Test
    public void whenSaveFromPageGeneralContractThenInvokeServiceSaveAndReturnSuccessSpec() throws Exception {
        Result<GeneralContractVo> result = generalContractController.saveFromPage(generalContractVo);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 根据ID查询
     * return
     * Author luosc
     * param
     * Date 2019-06-06 10:58
     */
    @Test
    public void whenFindByIdGeneralGeneralContractThenInvokeServiceSaveAndReturnSuccessSpec() throws Exception {
        Result<GeneralContractQueryVo> result = generalContractController.findById(id);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 根据businessId查询
     * return
     * Author luosc
     * param
     * Date 2019-06-06 11:01
     */
    @Test
    public void whenFindByBusinessIdGeneralContractThenInvokeServiceSaveAndReturnSuccessSpec() throws Exception {
        Result<GeneralContractQueryVo> result = generalContractController.findByBusinessId(businessId);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 删除合同管理
     * return
     * Author luosc
     * param
     * Date 2019-06-06 11:16
     */
    @Test
    public void whenDeleteGeneralContractThenInvokeServiceDeleteAndReturnSuccessSpec() throws Exception {
        Result result = generalContractController.delete(id);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 接口调用修改
     * return
     * Author luosc
     * param
     * Date 2019-06-06 11:18
     */
    @Test
    public void whenEditGeneralContractThenInvokeServiceEditAndReturnSuccessSpec() throws Exception {
        Result result = generalContractController.edit(generalContractVo);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 修改
     * return
     * Author luosc
     * param
     * Date 2019-06-06 11:18
     */
    @Test
    public void whenEditFromPageGeneralContractThenInvokeServiceEditAndReturnSuccessSpec() throws Exception {
        Result result = generalContractController.editFormPage(generalContractVo);
        Assert.assertTrue(result.isSuccess());
    }

    /**
     * 查询列表
     * return
     * Author luosc
     * param
     * Date 2019-06-06 11:08
     */
    @Test
    public void whenGetListGeneralContractThenInvokeServiceSaveAndReturnSuccessSpec() throws Exception {
        List<GeneralContractQueryVo> generalContractQueryVos = ImmutableList.of(new GeneralContractQueryVo(), new GeneralContractQueryVo());
        PageInfo<GeneralContractQueryVo> pageInfoExpected = new PageInfo<>();
        pageInfoExpected.setList(generalContractQueryVos);
        doReturn(pageInfoExpected).when(generalContractService).list(generalContractQueryVo);
        Result<PageData> result = generalContractController.list(generalContractQueryVo);
        Assert.assertEquals(pageInfoExpected, result.getData().get("varList"));
    }


}
