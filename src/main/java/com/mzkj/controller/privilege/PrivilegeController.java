package com.mzkj.controller.privilege;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.PrivilegeBean;
import com.mzkj.controller.usergroup.UsergroupController;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.enums.PrivilegeSubType;
import com.mzkj.facade.enums.PrivilegeType;
import com.mzkj.facade.vo.AddUsers2PrivilegeVo;
import com.mzkj.facade.vo.InsertPrivilegeVo;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.privilege.PrivilegeManager;
import com.mzkj.vo.privilege.PrivilegeQueryVo;
import com.mzkj.vo.privilege.PrivilegeVo;
import com.mzkj.vo.privilege.QueryPrivilegesByUserVo;
import com.mzkj.vo.privilege.UserOfPrivilegeQueryVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result insert(InsertPrivilegeVo insertPrivilegeVo) {
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

    @RequestMapping(value = "/addUsers2Privilege", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户到权限", notes = "添加用户到权限")
    public Result addUsers2Privilege(AddUsers2PrivilegeVo addUsers2PrivilegeVo) {
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

    @RequestMapping(value = "/deleteUsersOfPrivilege", method = RequestMethod.POST)
    @ApiOperation(value = "删除权限下的用户", notes = "删除权限下的用户")
    public Result deleteUsersOfPrivilege(String[] mappingIds) {
        Result result = new Result();
        try {
            getPrivilegeService().deleteUsersOfPrivilege(mappingIds);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("删除权限下的用户成功");
        } catch (RuntimeException e) {
            logger.error("删除权限下的用户失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("删除权限下的用户失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/findPrivilegeTypes", method = RequestMethod.POST)
    @ApiOperation(value = "查询权限类型", notes = "查询权限类型")
    public List<Map<String, String>> findPrivilegeTypes() {
        List<Map<String, String>> privilegeTypes = new ArrayList<>();
        for (PrivilegeType privilegeType : PrivilegeType.values()) {
            Map<String, String> entry = new HashMap();
            entry.put("name", privilegeType.name());
            entry.put("code", privilegeType.getCode());
            privilegeTypes.add(entry);
        }
        return privilegeTypes;
    }

    @RequestMapping(value = "/findPrivilegeSubTypes", method = RequestMethod.POST)
    @ApiOperation(value = "查询子权限类型", notes = "查询子权限类型")
    public List<Map<String, String>> findPrivilegeSubTypes() {
        List<Map<String, String>> privilegeSubTypes = new ArrayList<>();
        for (PrivilegeSubType privilegeSubType : PrivilegeSubType.values()) {
            Map<String, String> entry = new HashMap();
            entry.put("name", privilegeSubType.name());
            entry.put("code", privilegeSubType.getCode());
            privilegeSubTypes.add(entry);
        }
        return privilegeSubTypes;
    }

    @RequestMapping(value = "/findPrivilegesByUserInPage", method = RequestMethod.POST)
    @ApiOperation(value = "按用户分页查询权限", notes = "按用户分页查询权限")
    public Result findPrivilegesByUser(QueryPrivilegesByUserVo queryPrivilegesByUserVo) {
        Result<PageInfo> result = new Result();
        try {
            PageInfo pageInfo = getPrivilegeService().findPrivilegesByUser(queryPrivilegesByUserVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("按用户分页查询权限成功");
            result.setData(pageInfo);
        } catch (RuntimeException e) {
            logger.error("按用户分页查询权限失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("按用户分页查询权限失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/findPrivilegesUnselectedByUser", method = RequestMethod.POST)
    @ApiOperation(value = "按用户分页查询权限", notes = "按用户分页查询权限")
    public Result<PageInfo<List<PrivilegeBean>>> findPrivilegesUnselectedByUser(QueryPrivilegesByUserVo queryPrivilegesByUserVo) {
        Result<PageInfo<List<PrivilegeBean>>> result = new Result();
        try {
            PageInfo pageInfo = getPrivilegeService().findPrivilegesUnselectedByUser(queryPrivilegesByUserVo);
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setMsg("按用户分页查询权限成功");
            result.setData(pageInfo);
        } catch (RuntimeException e) {
            logger.error("获取未被用户选中的权限失败", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("获取未被用户选中的权限失败");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除权限", notes = "删除权限")
    public void delete(String[] privilegeIds) {

    }


    public void addPrivileges2User(String[] privilegeIds, String userId, String[] operations) {
        getPrivilegeService().addPrivileges2User(privilegeIds, userId, operations);
    }
}
