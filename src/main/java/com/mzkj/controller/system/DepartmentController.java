package com.mzkj.controller.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.system.DepartmentManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.system.DepartmentQueryVo;
import com.mzkj.vo.system.DepartmentVo;

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
 * 说明：部门管理
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@RestController
@RequestMapping(value = "/department")
@Api(tags = "DepartmentController", description = "部门管理接口")
public class DepartmentController {

    private static Logger logger = LogManager.getLogger(DepartmentController.class);
	String menuUrl = "department/list.do"; //菜单地址(权限用)
    @Autowired
	private DepartmentManager departmentService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询department", notes = "保存department")
	public Result<DepartmentVo> save(DepartmentVo departmentVo) {
        logger.info(Jurisdiction.getUsername()+"查询部门管理");
        Result<DepartmentVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        departmentVo.setDepartmentId(UuidUtil.get32UUID());
        try {
            departmentVo = departmentService.save(departmentVo);
            result.setData(departmentVo);
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
	public Result delete(@PathVariable("id") String departmentId) {
        logger.info(Jurisdiction.getUsername()+"删除部门管理");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            departmentService.delete(departmentId);
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
	public Result edit(DepartmentVo departmentVo) {
        logger.info(Jurisdiction.getUsername()+"修改部门管理");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            departmentService.edit(departmentVo);
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
	public Result<PageInfo<DepartmentQueryVo>> list(DepartmentQueryVo departmentQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看部门管理");
        Result<PageInfo<DepartmentQueryVo>> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<DepartmentQueryVo>	varList = departmentService.list(departmentQueryVo);
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
