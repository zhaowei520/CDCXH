package com.mzkj.controller.privilege;

import com.mzkj.controller.usergroup.UsergroupController;
import com.mzkj.service.privilege.PrivilegeManager;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.privilege.PrivilegeQueryVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/privilege")
@Api(tags = "PrivilegeController", description = "权限接口")
public class PrivilegeController {

    private static Logger logger = LogManager.getLogger(UsergroupController.class);

    @Autowired
    private PrivilegeManager privilegeService;

    public PrivilegeManager getPrivilegeService() {
        return privilegeService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询privilege", notes = "分页查询privilege")
    public Result<List<PrivilegeQueryVo>> list(PrivilegeQueryVo privilegeQueryVo) {
        Result<List<PrivilegeQueryVo>> result = new Result();
        try {
            List<PrivilegeQueryVo> privilegeQueryVos = getPrivilegeService().datalistPage(privilegeQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("查询权限成功");
            result.setData(privilegeQueryVos);
        } catch (Exception e) {
            logger.error("查询权限失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("查询权限失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新privilege", notes = "更新privilege")
    public Result update(PrivilegeQueryVo privilegeQueryVo) {
        Result result = new Result();
        try {
            getPrivilegeService().update(privilegeQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("修改权限成功");
        } catch (Exception e) {
            logger.error("修改权限失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("修改权限失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ApiOperation(value = "查询指定privilege", notes = "查询指定privilege")
    public Result findById(PrivilegeQueryVo privilegeQueryVo) {
        Result result = new Result();
        try {
            PrivilegeQueryVo privilegeQueryVoResult = getPrivilegeService().findById(privilegeQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("获取指定权限成功");
            result.setData(privilegeQueryVoResult);
        } catch (RuntimeException e) {
            logger.error("获取指定权限失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("获取指定权限失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增privilege", notes = "新增privilege")
    public Result insert(PrivilegeQueryVo privilegeQueryVo) {
        Result result = new Result();
        try {
            getPrivilegeService().insert(privilegeQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("新增权限成功");
        } catch (RuntimeException e) {
            logger.error("新增权限失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("新增权限失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

}
