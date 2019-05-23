package com.mzkj.controller.privilege;

import com.github.pagehelper.PageInfo;
import com.mzkj.controller.usergroup.UsergroupController;
import com.mzkj.service.privilege.PrivilegeManager;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.privilege.PrivilegeQueryVo;
import com.mzkj.vo.privilege.PrivilegeVo;
import com.mzkj.vo.privilege.UserOfPrivilegeQueryVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<PageInfo> list(PrivilegeQueryVo privilegeQueryVo) {
        Result<PageInfo> result = new Result();
        try {
            PageInfo pageInfo = getPrivilegeService().datalistPage(privilegeQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("查询权限成功");
            result.setData(pageInfo);
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
    public Result update(PrivilegeVo privilegeVo) {
        Result result = new Result();
        try {
            getPrivilegeService().update(privilegeVo);
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
    public Result findById(String id) {
        Result result = new Result();
        try {
            PrivilegeVo privilegeVo = getPrivilegeService().findById(id);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("获取指定权限成功");
            result.setData(privilegeVo);
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
    public Result insert(PrivilegeVo privilegeVo) {
        Result result = new Result();
        try {
            getPrivilegeService().insert(privilegeVo);
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

    @RequestMapping(value = "/findUsersByPrivilege", method = RequestMethod.POST)
    @ApiOperation(value = "查询权限下的用户", notes = "查询权限下的用户")
    public Result findUsersByPrivilege(UserOfPrivilegeQueryVo userOfPrivilegeQueryVo) {
        Result result = new Result();
        try {
            PageInfo pageInfo = getPrivilegeService().findUsersByPrivilege(userOfPrivilegeQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("查询权限下的用户成功");
            result.setData(pageInfo);
        } catch (RuntimeException e) {
            logger.error("查询权限下的用户失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("查询权限下的用户失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/findUsersUnselectedByPrivilege", method = RequestMethod.POST)
    @ApiOperation(value = "查询未被选中的用户", notes = "查询未被选中的用户")
    public Result findUsersUnselectedByPrivilege(UserOfPrivilegeQueryVo userOfPrivilegeQueryVo) {
        Result result = new Result();
        try {
            PageInfo PageInfo = getPrivilegeService().findUsersUnselectedByPrivilege(userOfPrivilegeQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("查询未被选中的用户成功");
            result.setData(PageInfo);
        } catch (RuntimeException e) {
            logger.error("查询未被选中的用户失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("查询未被选中的用户失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/addUsers2Privileges", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户到权限", notes = "添加用户到权限")
    public Result addUsers2Privileges(String privilegeId, String[] userIds, String[] operations) {
        Result result = new Result();
        try {
            getPrivilegeService().addUsers2Privileges(privilegeId, userIds, operations);
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
