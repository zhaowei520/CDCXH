package com.mzkj.controller.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.system.UserQueryVo;
import com.mzkj.vo.system.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.mzkj.service.system.UserManager;

import java.util.List;

/**
 * 说明：用户管理
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = "UserController", description = "用户管理接口")
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class);
	String menuUrl = "user/list.do"; //菜单地址(权限用)
    @Autowired
	private UserManager userService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询user", notes = "保存user")
	public Result<UserVo> save(UserVo userVo) {
        logger.info(Jurisdiction.getUsername()+"查询用户管理");
        Result<UserVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        userVo.setUserId(UuidUtil.get32UUID());
        try {
            userVo = userService.save(userVo);
            result.setData(userVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
         }
		return result;
	}

	/**删除
	 */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除template", notes = "删除template")
	public Result delete(@PathVariable("id") String userId) {
        logger.info(Jurisdiction.getUsername()+"删除用户管理");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            userService.delete(userId);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
         return result;
	}

	/**修改
	 * @throws Exception
	 */
	@RequestMapping(value="/edit", method = RequestMethod.PUT)
    @ApiOperation(value = "修改template", notes = "修改template")
	public Result edit(UserVo userVo) {
        logger.info(Jurisdiction.getUsername()+"修改用户管理");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            userService.edit(userVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
		return result;
	}

	/**列表
	 * @throws Exception
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询template", notes = "分页查询template")
	public Result<PageInfo<UserQueryVo>> list(UserQueryVo userQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看用户管理");
        Result<PageInfo<UserQueryVo>> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<UserQueryVo>	varList = userService.list(userQueryVo);
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
     * 获取所有user，不包含当前登录人
     * return
     * Author luosc
     * param
     * Date 2019-04-25 10:54
     */
    @RequestMapping(value="/listAll", method = RequestMethod.POST)
    @ApiOperation(value = "userList不分页", notes = "userList不分页")
    public Result<List<UserQueryVo>> listAll(UserQueryVo userQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看所有用户列表");
        Result<List<UserQueryVo>> result = new Result<>();
        try {
            List<UserQueryVo>	varList = userService.listAllAndFilterSelf(userQueryVo);
            result.setData(varList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
