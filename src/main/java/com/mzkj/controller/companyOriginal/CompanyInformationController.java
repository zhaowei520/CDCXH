package com.mzkj.controller.companyOriginal;

import com.github.pagehelper.PageInfo;
import com.mzkj.domain.Original;
import com.mzkj.util.Const;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.companyOriginal.CompanyInformationQueryVo;
import com.mzkj.vo.companyOriginal.CompanyInformationVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mzkj.service.companyOriginal.CompanyInformationManager;
import com.mzkj.vo.companyOriginal.OriginalQueryVo;

import java.util.List;

/**
 * 说明：原件管理客户信息
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 */
@RestController
@RequestMapping(value = "/companyInformation")
@Api(tags = "CompanyInformationController", description = "原件管理客户信息接口")
public class CompanyInformationController {

    private static Logger logger = LogManager.getLogger(CompanyInformationController.class);
    String menuUrl = "companyInformation/list.do"; //菜单地址(权限用)
    @Autowired
    private CompanyInformationManager companyinformationService;

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存companyinformation", notes = "保存companyinformation")
    public Result<CompanyInformationVo> save(CompanyInformationVo companyinformationVo) {
        logger.info(Jurisdiction.getUsername() + "查询原件管理客户信息");
        Result<CompanyInformationVo> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        companyinformationVo.setCompanyInformationId(UuidUtil.get32UUID());
        try {
            companyinformationVo = companyinformationService.save(companyinformationVo);
            result.setData(companyinformationVo);
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
    public Result delete(@PathVariable("id") String companyInformationId) {
        logger.info(Jurisdiction.getUsername() + "删除原件管理客户信息");
        Result result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            companyinformationService.delete(companyInformationId);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改template", notes = "修改template")
    public Result edit(CompanyInformationVo companyinformationVo) {
        logger.info(Jurisdiction.getUsername() + "修改原件管理客户信息");
        Result result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            companyinformationService.edit(companyinformationVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询CompanyInformation", notes = "分页查询CompanyInformation")
    public Result<PageInfo<CompanyInformationQueryVo>> list(CompanyInformationQueryVo companyinformationQueryVo) {
        logger.info(Jurisdiction.getUsername() + "查看原件管理客户信息");
        Result<PageInfo<CompanyInformationQueryVo>> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<CompanyInformationQueryVo> varList = companyinformationService.list(companyinformationQueryVo);
            //设置原件信息
            constOriginalListToString(varList);

            result.setData(varList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 将originals转成string 原件名：人名，原件2，人名2
     * 设置出库信息
     * return
     * Author luosc
     * param
     * Date 2019-04-22 9:02
     */
    private void constOriginalListToString(PageInfo<CompanyInformationQueryVo> varList) {
        if (varList != null && varList.getList() != null && varList.getList().size() > 0) {
            for (CompanyInformationQueryVo companyInformationQueryVo : varList.getList()) {
                List<OriginalQueryVo> originalQueryVos = companyInformationQueryVo.getOriginalList();
                if (originalQueryVos != null && originalQueryVos.size() > 0) {
                    String result = "";
                    String originalOutStatusInformation = "";
                    for (OriginalQueryVo original : originalQueryVos) {
                        String originalName = original.getOriginalName();//原件名
                        String originalHolder = original.getOriginalHolder();//原件持有人
                        if (!StringUtils.isEmpty(original.getOriginalOutStatus()) && original.getOriginalOutStatus().equals(Const.ORIGINAL_OUT_STATUS_2)) {
                            result += originalName + ":" + originalHolder + ",";
                        } else if (!StringUtils.isEmpty(original.getOriginalHoldStatus()) && original.getOriginalHoldStatus().equals(Const.ORIGINAL_HOLD_STATUS_0)) {
                            //无原件
                        }else if (!StringUtils.isEmpty(original.getOriginalHoldStatus()) && original.getOriginalHoldStatus().equals(Const.ORIGINAL_HOLD_STATUS_1)) {
                            //在客户处
                            result+= originalName + ":客户处,";
                        }

                        //流转状态为 出库中
                        if (!StringUtils.isEmpty(original.getOriginalOutStatus()) && original.getOriginalOutStatus().equals(Const.ORIGINAL_OUT_STATUS_1)) {
                            //如果原件持有人和当前登录人相同
                            if (!StringUtils.isEmpty(original.getOriginalHolder()) && original.getOriginalHolder().equals(Jurisdiction.getUsername())) {
                                originalOutStatusInformation += original.getOriginalName() + ":出库中,";
                            }
                            //出库对象和当前登录人相同
                            else if (!StringUtils.isEmpty(original.getOriginalOutTo()) && original.getOriginalOutTo().equals(Jurisdiction.getUsername())) {
                                originalOutStatusInformation += original.getOriginalName() + ":借入待确认,";
                            }
                        }
                        //待借入
                        if (!StringUtils.isEmpty(original.getOriginalOutStatus()) && original.getOriginalOutStatus().equals(Const.ORIGINAL_OUT_STATUS_3)) {
                            //如果原件持有人和当前登录人相同
                            if (!StringUtils.isEmpty(original.getOriginalHolder()) && original.getOriginalHolder().equals(Jurisdiction.getUsername())) {
                                originalOutStatusInformation += original.getOriginalName() + ":借出待确认,";
                            }
                            //出库对象和当前登录人相同
                            else if (!StringUtils.isEmpty(original.getOriginalOutTo()) && original.getOriginalOutTo().equals(Jurisdiction.getUsername())) {
                                originalOutStatusInformation += original.getOriginalName() + ":待借入,";
                            }
                        }

                    }
                    companyInformationQueryVo.setOriginalListString(result);
                    companyInformationQueryVo.setOriginalInformation(originalOutStatusInformation);
                }
            }
        }
    }

    /**
     * 根据ID查询
     * return
     * Author luosc
     * param
     * Date 2019-04-23 8:46
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询CompanyInformation", notes = "根据ID查询CompanyInformation")
    public Result findCompanyInformationById(@PathVariable("id") String companyInformationId) {
        logger.info(Jurisdiction.getUsername() + "findById查看原件管理客户信息");
        Result result = new Result();
        try {
            CompanyInformationVo companyInformationVo = companyinformationService.findById(companyInformationId);
            result.setData(companyInformationVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}