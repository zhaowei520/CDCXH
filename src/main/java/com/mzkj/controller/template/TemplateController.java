package com.mzkj.controller.template;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.template.TemplateManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.template.TemplateQueryVo;
import com.mzkj.vo.template.TemplateVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 说明：薪资模板表
 * 创建人：CDCXH
 * 创建时间：2019-03-21
 */
@RestController
@RequestMapping(value = "/template")
@Api(tags = "TemplateController", description = "薪资模板表接口")
public class TemplateController {

    private static Logger logger = LogManager.getLogger(TemplateController.class);
    String menuUrl = "template/list.do"; //菜单地址(权限用)
    @Autowired
    private TemplateManager templateService;

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存template", notes = "保存template")
    public Result<TemplateVo> save(TemplateVo templateVo) {
        logger.info(Jurisdiction.getUsername() + "保存薪资模板表");
        Result<TemplateVo> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        templateVo.setTEMPLATE_ID(UuidUtil.get32UUID());
        try {
            templateVo = templateService.save(templateVo);
            result.setData(templateVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        return result;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除template", notes = "删除template")
    public Result delete(@PathVariable("id") String TEMPLATE_ID) {
        logger.info(Jurisdiction.getUsername() + "删除薪资模板表");
        Result result = new Result();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            templateService.delete(TEMPLATE_ID);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        return result;
    }

    /**
     * findById查看template详情
     * return
     * Author luosc
     * param
     * Date 2019-04-03 9:25
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查看template详情", notes = "查看template详情")
    public Result findTemplateById(@PathVariable("id") String TEMPLATE_ID) {
        logger.info(Jurisdiction.getUsername() + "查看薪资模板详情");
        Result result = new Result();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            TemplateVo templateVo = templateService.findById(TEMPLATE_ID);
            result.setData(templateVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        return result;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改template", notes = "修改template")
    public Result<TemplateVo> edit(TemplateVo templateVo) {
        logger.info(Jurisdiction.getUsername() + "修改薪资模板表");
        Result<TemplateVo> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            templateService.edit(templateVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        return result;
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询template", notes = "分页查询template")
    public Result<PageInfo<TemplateQueryVo>> list(TemplateQueryVo templateVo) {
        logger.info(Jurisdiction.getUsername() + "查看薪资模板表");
        Result<PageInfo<TemplateQueryVo>> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<TemplateQueryVo> varList = templateService.list(templateVo);
            result.setData(varList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        return result;
    }

}
