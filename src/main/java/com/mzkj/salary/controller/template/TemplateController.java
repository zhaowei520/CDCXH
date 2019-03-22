package com.mzkj.salary.controller.template;

import java.util.List;

import com.mzkj.salary.controller.base.BaseController;
import com.mzkj.salary.util.Jurisdiction;
import com.mzkj.salary.util.UuidUtil;
import com.mzkj.salary.util.enums.HttpCode;
import com.mzkj.salary.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fh.util.PageData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mzkj.salary.service.template.TemplateManager;

/**
 * 说明：薪资模板表
 * 创建人：CDCXH
 * 创建时间：2019-03-21
 */
@RestController
@RequestMapping(value = "/template")
@Api(tags = "TemplateController", description = "薪资模板表接口")
public class TemplateController extends BaseController {

    private static Logger logger = LogManager.getLogger(TemplateController.class);
    String menuUrl = "template/list.do"; //菜单地址(权限用)
    @Autowired
    private TemplateManager templateService;

    /**
     * 保存
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询template", notes = "保存template")
    public Result<PageData> save() throws Exception {
        logger.info(Jurisdiction.getUsername() + "查询薪资模板表");
        Result<PageData> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        PageData pd = this.getPageData();
        pd.put("TEMPLATE_ID", UuidUtil.get32UUID());  //主键
        try {
            templateService.save(pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除
     *
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<PageData> delete() throws Exception {
        logger.info(Jurisdiction.getUsername() + "删除薪资模板表");
        Result<PageData> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        PageData pd = this.getPageData();
        try {
            templateService.delete(pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 修改
     *
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Result<PageData> edit() throws Exception {
        logger.info(Jurisdiction.getUsername() + "修改薪资模板表");
        Result<PageData> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        PageData pd = this.getPageData();
        try {
            templateService.edit(pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 列表
     *
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<PageData>> list() throws Exception {
        logger.info(Jurisdiction.getUsername() + "查看薪资模板表");
        Result<List<PageData>> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        PageData pd = this.getPageData();
        try {
            List<PageData> varList = templateService.list(pd);
            result.setData(varList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
        }
        return result;
    }

}
