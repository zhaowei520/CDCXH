package com.mzkj.controller.insurance;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.insurance.SocialSecurityQueryVo;
import com.mzkj.vo.insurance.SocialSecurityVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mzkj.service.insurance.SocialSecurityManager;

import java.util.Map;

/**
 * 说明：社保工单
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 */
@RestController
@RequestMapping(value = "/socialSecurity")
@Api(tags = "SocialSecurityController", description = "社保工单接口")
public class SocialSecurityController {

    private static Logger logger = LogManager.getLogger(SocialSecurityController.class);
    String menuUrl = "/socialsecurity"; //菜单地址(权限用)
    String type;
    @Autowired
    private SocialSecurityManager socialSecurityService;

    public SocialSecurityManager getSocialSecurityService() {
        return socialSecurityService;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询socialSecurity", notes = "保存socialSecurity")
    public Result<SocialSecurityVo> save(SocialSecurityVo socialSecurityVo) {
        this.type = "add";
        Result<SocialSecurityVo> result = new Result<>();
        logger.info(getUsernameFromSession() + "查询社保工单");
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        socialSecurityVo.setSocialSecurityId(UuidUtil.get32UUID());
        socialSecurityVo.setBusinessId(UuidUtil.get32UUID());
        try {
            socialSecurityVo = getSocialSecurityService().save(socialSecurityVo);
            result.setData(socialSecurityVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除socialSecurity", notes = "删除socialSecurity")
    public Result delete(@PathVariable("id") String socialSecurityId) {
        this.type = "del";
        logger.info(getUsernameFromSession() + "删除社保工单");
        Result result = new Result<>();
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            getSocialSecurityService().delete(socialSecurityId);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改socialSecurity", notes = "修改socialSecurity")
    public Result edit(SocialSecurityVo socialSecurityVo) {
        this.type = "edit";
        logger.info(getUsernameFromSession() + "修改社保工单");
        Result result = new Result<>();
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            getSocialSecurityService().edit(socialSecurityVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 根据ID查询数据
     * return
     * Author luosc
     * param
     * Date 2019-05-13 15:46
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询socialSecurity", notes = "根据ID查询socialSecurity")
    public Result<SocialSecurityQueryVo> findById(@PathVariable("id") String socialSecurityId) {
        this.type = "cha";
        logger.info(getUsernameFromSession() + "查看社保工单");
        Result<SocialSecurityQueryVo> result = new Result<>();
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            SocialSecurityQueryVo socialSecurityQueryVo = getSocialSecurityService().findById(socialSecurityId);
            result.setData(socialSecurityQueryVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        result.setSuccess(true);
        return result;
    }
    /**
     * 根据businessId查询数据
     * return
     * Author luosc
     * param
     * Date 2019-05-13 15:46
     */
    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "根据businessId查询socialSecurity", notes = "根据businessId查询socialSecurity")
    public Result<SocialSecurityQueryVo> findByCode( String businessId) {
        logger.info(getUsernameFromSession() + "查询findByCode社保工单");
        Result<SocialSecurityQueryVo> result = new Result<>();
        try {
            SocialSecurityQueryVo socialSecurityQueryVo = getSocialSecurityService().findByCode(businessId);
            result.setData(socialSecurityQueryVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        result.setSuccess(true);
        return result;
    }
    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询socialSecurity", notes = "分页查询socialSecurity")
    public Result<PageData> list(SocialSecurityQueryVo socialSecurityQueryVo) {
        this.type = "cha";
        logger.info(getUsernameFromSession() + "查看社保工单");
        Result<PageData> result = new Result<>();
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<SocialSecurityQueryVo> varList = getSocialSecurityService().list(socialSecurityQueryVo);
            PageData resultdata = new PageData();
            resultdata.put("varList", varList);
            resultdata.put("QX",getButtonPermissionFromSession() );
            result.setData(resultdata);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 获取当前登录人username
     * return
     * Author luosc
     * param
     * Date 2019-05-13 17:10
     */
    public String getUsernameFromSession() {
        return Jurisdiction.getUsername();
    }

    /**
     * 验证当前登录人是否有按钮权限
     * return
     * Author luosc
     * param type 按钮类型
     * Date 2019-05-13 17:10
     */
    public boolean verifyPermissionFromSession() {
        return Jurisdiction.buttonJurisdiction(menuUrl, type);
    }

    /**
     * 获取所有按钮权限
     * return
     * Author luosc
     * param
     * Date 2019-05-15 9:03
     */
    public Map<String, String> getButtonPermissionFromSession() {
        return Jurisdiction.getHC();
    }
}
