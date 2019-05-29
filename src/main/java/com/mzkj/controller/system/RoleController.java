package com.mzkj.controller.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.system.RoleManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.system.RoleQueryVo;
import com.mzkj.vo.system.RoleVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 说明：系统角色
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 */
@RestController
@RequestMapping(value = "/role")
@Api(tags = "RoleController", description = "系统角色接口")
public class RoleController {

    private static Logger logger = LogManager.getLogger(RoleController.class);
	String menuUrl = "role/list.do"; //菜单地址(权限用)
    @Autowired
	private RoleManager roleService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询role", notes = "保存role")
	public Result<RoleVo> save(RoleVo roleVo) {
        logger.info(Jurisdiction.getUsername()+"查询系统角色");
        Result<RoleVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        roleVo.setRoleId(UuidUtil.get32UUID());
        try {
            roleVo = roleService.save(roleVo);
            result.setData(roleVo);
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
    @ApiOperation(value = "删除role", notes = "删除role")
	public Result delete(@PathVariable("id") String roleId) {
        logger.info(Jurisdiction.getUsername()+"删除系统角色");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            roleService.delete(roleId);
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
	@RequestMapping(value="/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改role", notes = "修改role")
	public Result edit(RoleVo roleVo) {
        logger.info(Jurisdiction.getUsername()+"修改系统角色");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            roleService.edit(roleVo);
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
	@RequestMapping(value="/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询role", notes = "分页查询role")
	public Result<PageInfo<RoleQueryVo>> list(RoleQueryVo roleQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看系统角色");
        Result<PageInfo<RoleQueryVo>> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<RoleQueryVo>	varList = roleService.list(roleQueryVo);
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
