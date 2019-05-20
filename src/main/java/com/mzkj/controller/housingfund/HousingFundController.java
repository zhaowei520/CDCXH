package com.mzkj.controller.housingfund;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.service.insurance.SocialSecurityManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.housingfund.HousingFundQueryVo;
import com.mzkj.vo.housingfund.HousingFundVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mzkj.service.housingfund.HousingFundManager;

import java.util.Map;

/**
 * 说明：公积金工单
 * 创建人：CDCXH
 * 创建时间：2019-05-13
 */
@RestController
@RequestMapping(value = "/housingFund")
@Api(tags = "HousingFundController", description = "公积金工单接口")
public class HousingFundController {

    private static Logger logger = LogManager.getLogger(HousingFundController.class);
    String menuUrl = "/housingfund"; //菜单地址(权限用)
    String type;
    @Autowired
    private HousingFundManager housingFundService;

    public HousingFundManager getHousingFundService() {
        return housingFundService;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询housingFund", notes = "保存housingFund")
    public Result<HousingFundVo> save(HousingFundVo housingFundVo) {
        type = "add";
        logger.info(getUsernameFromSession() + "查询公积金工单");
        Result<HousingFundVo> result = new Result<>();
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        housingFundVo.setHousingFundId(UuidUtil.get32UUID());
        try {
            housingFundVo = getHousingFundService().save(housingFundVo);
            result.setData(housingFundVo);
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
    @ApiOperation(value = "删除housingFund", notes = "删除housingFund")
    public Result delete(@PathVariable("id") String housingFundId) {
        type = "del";
        logger.info(getUsernameFromSession() + "删除公积金工单");
        Result result = new Result<>();
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            getHousingFundService().delete(housingFundId);
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
    @ApiOperation(value = "修改housingFund", notes = "修改housingFund")
    public Result edit(HousingFundVo housingFundVo) {
        type = "edit";
        logger.info(getUsernameFromSession() + "修改公积金工单");
        Result result = new Result<>();
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            getHousingFundService().edit(housingFundVo);
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
     * Date 2019-05-13 15:58
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询housingFund", notes = "根据ID查询housingFund")
    public Result<HousingFundQueryVo> findByID(@PathVariable("id") String housingFundId) {
        type = "cha";
        logger.info(getUsernameFromSession() + "查看公积金工单");
        Result<HousingFundQueryVo> result = new Result<>();
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            HousingFundQueryVo housingFundQueryVo = getHousingFundService().findById(housingFundId);
            result.setData(housingFundQueryVo);
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
    @ApiOperation(value = "分页查询housingFund", notes = "分页查询housingFund")
    public Result<PageData> list(HousingFundQueryVo housingFundQueryVo) {
        type = "cha";
        logger.info(getUsernameFromSession() + "查看公积金工单");
        Result<PageData> result = new Result<>();
        if (!verifyPermissionFromSession()) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<HousingFundQueryVo> varList = getHousingFundService().list(housingFundQueryVo);
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
