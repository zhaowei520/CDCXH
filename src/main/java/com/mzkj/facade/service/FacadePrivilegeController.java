package com.mzkj.facade.service;

import com.mzkj.controller.usergroup.UsergroupController;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.AddUsers2PrivilegeVo;
import com.mzkj.facade.vo.InsertPrivilegeVo;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.privilege.PrivilegeManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/facade/privilege")
@Api(tags = "FacadePrivilegeController", description = "权限接口")
public class FacadePrivilegeController {

    private static Logger logger = LogManager.getLogger(UsergroupController.class);

    @Autowired
    private PrivilegeManager privilegeService;

    public PrivilegeManager getPrivilegeService() {
        return privilegeService;
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增privilege", notes = "新增privilege2")
    public Result insert(@RequestBody InsertPrivilegeVo insertPrivilegeVo) {
        Result result = new Result();
        try {
            String privilegeId = getPrivilegeService().insert(insertPrivilegeVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("新增权限成功");
            result.setData(privilegeId);
        } catch (RuntimeException e) {
            logger.error("新增权限失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("新增权限失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/addUsers2Privilege", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户到权限", notes = "添加用户到权限")
    public Result addUsers2Privilege(@RequestBody AddUsers2PrivilegeVo addUsers2PrivilegeVo) {
        Result result = new Result();
        try {
            getPrivilegeService().addUsers2Privilege(addUsers2PrivilegeVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("添加用户到权限成功");
        } catch (RuntimeException e) {
            logger.error("添加用户到权限失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("添加用户到权限失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

}

