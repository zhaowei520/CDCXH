package com.mzkj.controller.usergroup;

import com.alibaba.fastjson.JSONArray;
import com.mzkj.service.usergroup.UsergroupManager;
import com.mzkj.util.Const;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.system.UserVo;
import com.mzkj.vo.usergroup.PrivilegeOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UserOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupQueryVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 说明：用户组管理 创建人：FH Q313596790 创建时间：2019-05-08
 */
@RestController
@RequestMapping(value = "/usergroup")
@Api(tags = "UsergroupController", description = "用户组接口")
public class UsergroupController {

    private static Logger logger = LogManager.getLogger(UsergroupController.class);

    @Autowired
    private UsergroupManager usergroupService;

    public UsergroupManager getUsergroupService() {
        return usergroupService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询usergroup", notes = "分页查询usergroup")
    public Result list(final UsergroupQueryVo usergroupQueryVo) {
        Result<List<UsergroupQueryVo>> result = new Result<>();
        try {
            List<UsergroupQueryVo> usergroupQueryVos = getUsergroupService().list(usergroupQueryVo);
            result.setData(usergroupQueryVos);
        } catch (Exception e) {
            logger.error(e);
            result.setSuccess(false);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setMsg("后台报错");
            return result;
        }
        return result;
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存usergroup", notes = "保存usergroup")
    public Result<UsergroupQueryVo> save(UsergroupQueryVo usergroupQueryVo) {
        Result<UsergroupQueryVo> result = new Result<>();
        try {
            usergroupQueryVo.setUsergroupId(UuidUtil.get32UUID());
            getUsergroupService().save(usergroupQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setData(usergroupQueryVo);
        } catch (Exception e) {
            logger.error("保存用户组报错", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("保存用户组报错");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新usergroup", notes = "更新usergroup")
    public Result update(UsergroupQueryVo usergroupQueryVo) {
        Result result = new Result();
        try {
            getUsergroupService().update(usergroupQueryVo);
            result.setMsg("更新用户组成功");
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
        } catch (Exception e) {
            logger.error("更新用户组报错", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("更新用户组报错");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ApiOperation(value = "查找指定usergroup", notes = "查找指定usergroup")
    public Result findById(UsergroupQueryVo usergroupQueryVo) {
        Result result = new Result();
        try {
            UsergroupQueryVo usergroupQueryVoResult = getUsergroupService().findById(usergroupQueryVo);
            result.setMsg("查询指定用户组成功");
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setData(usergroupQueryVoResult);
        } catch (RuntimeException e) {
            logger.error("查询指定用户组报错", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("查询指定用户组报错");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/findByParentId", method = RequestMethod.POST)
    @ApiOperation(value = "按照父节点查找usergroup", notes = "按照父节点查找usergroup")
    public Result<JSONArray> findByParentId() {
        Result<JSONArray> result = new Result();
        try {
            JSONArray jsonArray = getUsergroupService().findByParentId(Const.ROOF_ID_FOR_TREE);
            result.setMsg("查询树状用户组成功");
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setData(jsonArray);
        } catch (RuntimeException e) {
            logger.error("查询树状用户组异常", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("查询树状用户组异常");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "批量删除usergroup", notes = "批量删除usergroup")
    public Result delete(@RequestBody List<String> ids) {
        Result result = new Result();
        try {
            getUsergroupService().delete(ids);
            result.setMsg("删除用户组成功");
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
        } catch (RuntimeException e) {
            logger.error("删除用户组异常", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("删除用户组异常");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/findUsersByUsergroup", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户组查用户", notes = "根据用户组查用户")
    public Result findUsersByUsergroup(UserVo userVo) {
        Result result = new Result();
        try {
            List<UserVo> userVos = getUsergroupService().findUsersByUsergroup(userVo);
            result.setMsg("查询用户组对应用户成功");
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setData(userVos);
        } catch (RuntimeException e) {
            logger.error("查询用户组对应用户异常", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("查询用户组对应用户异常");
            result.setStatus(HttpCode.ERROR.getCode());
        }

        return result;
    }

    @RequestMapping(value = "/findPrivilegesByUsergroup", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户组查权限", notes = "根据用户组查权限")
    public Result findPrivilegesByUsergroup(PrivilegeOfUsergroupQueryVo privilegeByUsergroupQueryVo) {
        Result result = new Result();
        try {
            List<PrivilegeOfUsergroupQueryVo> privilegeByUsergroupQueryVos = getUsergroupService().findPrivilegesByUsergroup(privilegeByUsergroupQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setMsg("查询用户组对应权限成功");
            result.setStatus(HttpCode.OK.getCode());
            result.setData(privilegeByUsergroupQueryVos);
        } catch (Exception e) {
            logger.error("查询用户组对应权限异常", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("查询用户组对应权限异常");
            result.setStatus(HttpCode.ERROR.getCode());
        }

        return result;
    }

    @RequestMapping(value = "/addUser2Usergroup", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户到用户组", notes = "添加用户到用户组")
    public Result addUser2Usergroup(UserOfUsergroupQueryVo userByUsergroupQueryVo) {
        Result result = new Result();
        try {
            getUsergroupService().addUser2Usergroup(userByUsergroupQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setMsg("添加用户到用户组成功");
            result.setStatus(HttpCode.OK.getCode());
        } catch (Exception e) {
            logger.error("添加用户到用户组异常", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("添加用户到用户组异常");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    @RequestMapping(value = "/addPrivilege2Usergroup", method = RequestMethod.POST)
    @ApiOperation(value = "添加权限到用户组", notes = "添加权限到用户组")
    public Result addPrivilege2Usergroup(PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo) {
        Result result = new Result();
        try {
            getUsergroupService().addPrivilege2Usergroup(privilegeOfUsergroupQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setMsg("添加权限到用户组成功");
            result.setStatus(HttpCode.OK.getCode());
        } catch (RuntimeException e) {
            logger.error("添加权限到用户组异常", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("添加权限到用户组异常");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    /**
     * 显示列表ztree
     * @param model
     * @return
     */
//	@RequestMapping(value="/listTree")
//	public ModelAndView listTree(Model model,String PRIVILEGE_ID)throws Exception{
//		ModelAndView mv = this.getModelAndView();
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		try{
//			JSONArray arr = JSONArray.fromObject(privilegeService.listTree("0"));
//			String json = arr.toString();
//			json = json.replaceAll("PRIVILEGE_ID", "id").replaceAll("PARENT_ID", "pId").replaceAll("NAME", "name").replaceAll("subPrivilege", "nodes").replaceAll("hasPrivilege", "checked").replaceAll("treeurl", "url");
//			model.addAttribute("zTreeNodes", json);
//			mv.addObject("PRIVILEGE_ID",PRIVILEGE_ID);
//			mv.addObject("pd", pd);
//			mv.setViewName("privilege/privilege/privilege_tree");
//		} catch(Exception e){
//			logger.error(e.toString(), e);
//		}
//		return mv;
//	}


}
