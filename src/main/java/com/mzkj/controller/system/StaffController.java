package com.mzkj.controller.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.system.StaffManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.mzkj.vo.system.StaffQueryVo;
import com.mzkj.vo.system.StaffVo;

import java.util.List;

/**
 * 说明：员工
 * 创建人：CDCXH
 * 创建时间：2019-07-01
 */
@RestController
@RequestMapping(value = "/staff")
@Api(tags = "StaffController", description = "员工接口")
public class StaffController {

    private static Logger logger = LogManager.getLogger(StaffController.class);
	String menuUrl = "staff/list.do"; //菜单地址(权限用)
    @Autowired
	private StaffManager staffService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询staff", notes = "保存staff")
	public Result<StaffVo> save(StaffVo staffVo) {
        logger.info(Jurisdiction.getUsername()+"查询员工");
        Result<StaffVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        staffVo.setStaffId(UuidUtil.get32UUID());
        try {
            staffVo = staffService.save(staffVo);
            result.setData(staffVo);
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
    @ApiOperation(value = "删除staff", notes = "删除staff")
	public Result delete(@PathVariable("id") String staffId) {
        logger.info(Jurisdiction.getUsername()+"删除员工");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            staffService.delete(staffId);
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
    @ApiOperation(value = "修改staff", notes = "修改staff")
	public Result edit(StaffVo staffVo) {
        logger.info(Jurisdiction.getUsername()+"修改员工");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            staffService.edit(staffVo);
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
    @ApiOperation(value = "分页查询staff", notes = "分页查询staff")
	public Result<PageInfo<StaffQueryVo>> list(StaffQueryVo staffQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看员工");
        Result<PageInfo<StaffQueryVo>> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<StaffQueryVo>	varList = staffService.list(staffQueryVo);
            result.setData(varList);
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
    @RequestMapping(value="/listAllByDepartId", method = RequestMethod.POST)
    @ApiOperation(value = "根据部门id查询员工", notes = "根据部门id查询员工")
    public Result<List<StaffQueryVo>> listAllByDepartId(StaffQueryVo staffQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看员工");
        Result<List<StaffQueryVo>> result = new Result<>();
        try {
            List<StaffQueryVo> varList = staffService.listAllByDepartId(staffQueryVo);
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
