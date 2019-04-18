package com.mzkj.controller.companyOriginal;

import com.github.pagehelper.PageInfo;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.companyOriginal.CompanyInformationQueryVo;
import com.mzkj.vo.companyOriginal.CompanyInformationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.mzkj.service.companyOriginal.CompanyInformationManager;

/**
 * 说明：原件管理客户信息
 * 创建人：CDCXH
 * 创建时间：2019-04-17
 */
@RestController
@RequestMapping(value = "/companyinformation")
@Api(tags = "CompanyInformationController", description = "原件管理客户信息接口")
public class CompanyInformationController {

    private static Logger logger = LogManager.getLogger(CompanyInformationController.class);
	String menuUrl = "companyinformation/list.do"; //菜单地址(权限用)
    @Autowired
	private CompanyInformationManager companyinformationService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存companyinformation", notes = "保存companyinformation")
	public Result<CompanyInformationVo> save(CompanyInformationVo companyinformationVo) {
        logger.info(Jurisdiction.getUsername()+"查询原件管理客户信息");
        Result<CompanyInformationVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
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

	/**删除
	 */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除template", notes = "删除template")
	public Result delete(@PathVariable("id") String companyInformationId) {
        logger.info(Jurisdiction.getUsername()+"删除原件管理客户信息");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
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

	/**修改
	 * @throws Exception
	 */
	@RequestMapping(value="/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改template", notes = "修改template")
	public Result edit(CompanyInformationVo companyinformationVo) {
        logger.info(Jurisdiction.getUsername()+"修改原件管理客户信息");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
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

	/**列表
	 * @throws Exception
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询template", notes = "分页查询template")
	public Result<PageInfo<CompanyInformationQueryVo>> list(CompanyInformationQueryVo companyinformationQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看原件管理客户信息");
        Result<PageInfo<CompanyInformationQueryVo>> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<CompanyInformationQueryVo>	varList = companyinformationService.list(companyinformationQueryVo);
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
