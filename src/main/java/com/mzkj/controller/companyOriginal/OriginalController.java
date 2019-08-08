package com.mzkj.controller.companyOriginal;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.companyOriginal.OriginalManager;
import com.mzkj.service.system.RoleManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.companyOriginal.OriginalQueryVo;
import com.mzkj.vo.companyOriginal.OriginalVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 说明：公司原件详情
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@RestController
@RequestMapping(value = "/original")
@Api(tags = "OriginalController", description = "公司原件详情接口")
public class OriginalController {

    private static Logger logger = LogManager.getLogger(OriginalController.class);
	String menuUrl = "/original"; //菜单地址(权限用)
    @Autowired
	private OriginalManager originalService;
    @Autowired
    private RoleManager roleService;
	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询original", notes = "保存original")
	public Result<List<OriginalVo>> save(List<OriginalVo> originalVoList) {
        logger.info(Jurisdiction.getUsername()+"查询公司原件详情");
        Result<List<OriginalVo>> result = new Result<>();
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
//            result.setMsg("没有操作权限，请联系管理员");
//            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
//            return result;
//        }
        try {
            result.setData(originalService.save(originalVoList));
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
	public Result delete(@PathVariable("id") String originalId) {
        logger.info(Jurisdiction.getUsername()+"删除公司原件详情");
        Result result = new Result<>();
//        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
//            result.setMsg("没有操作权限，请联系管理员");
//            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
//            return result;
//        }
        try {
            originalService.delete(originalId);
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
	public Result edit(OriginalVo originalVo) {
        logger.info(Jurisdiction.getUsername()+"修改公司原件详情");
        Result result = new Result<>();
//        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
//            result.setMsg("没有操作权限，请联系管理员");
//            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
//            return result;
//        }
        try {
            originalService.edit(originalVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
		return result;
	}

	/**
	 * 根据ID查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-25 15:22
	 */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询original", notes = "根据ID查询original")
    public Result<OriginalQueryVo> findById(@PathVariable("id") String originalId) {
        logger.info(Jurisdiction.getUsername()+"根据ID查询original");
        Result<OriginalQueryVo> result = new Result<>();
        OriginalQueryVo originalQueryVo = new OriginalQueryVo();
        originalQueryVo.setOriginalId(originalId);
        try {
            originalQueryVo = originalService.findById(originalQueryVo);
            result.setData(originalQueryVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        return result;
    }
	/**列表
	 * @throws Exception
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询template", notes = "分页查询template")
	public Result<PageData> list(OriginalQueryVo originalQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看公司原件详情");
        Result<PageData> result = new Result<>();
//        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
//            result.setMsg("没有操作权限，请联系管理员");
//            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
//            return result;
//        }
        try {
            PageInfo<OriginalQueryVo>	varList = originalService.list(originalQueryVo);
            PageData resultdata = new PageData();
            resultdata.put("varList", varList);
            PageData QX = roleService.getPowerByRoleIdAndUrlPrefix("companyInformation");
            resultdata.put("QX", QX);
            result.setData(resultdata);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
		return result;
	}

	/**
	 * 主动借出原件
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-24 15:32
	 */
    @RequestMapping(value="/loanOut", method = RequestMethod.POST)
    @ApiOperation(value = "借出原件", notes = "借出原件")
    public Result loanOut(OriginalVo originalVo) {
        logger.info(Jurisdiction.getUsername()+"借出原件");
        Result result = new Result();
        try {
            originalService.loanOut(originalVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
    /**
     * 借出、借入原件确认
     * return
     * Author luosc
     * param
     * Date 2019-04-24 15:32
     */
    @RequestMapping(value="/loanOutConfirmed", method = RequestMethod.POST)
    @ApiOperation(value = "借出原件确认", notes = "借出原件确认")
    public Result loanOutConfirmed(OriginalVo originalVo) {
        logger.info(Jurisdiction.getUsername()+"借出原件");
        Result result = new Result();
        try {
            originalService.loanOutConfirmed(originalVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 驳回
     * return
     * Author luosc
     * param
     * Date 2019-04-24 16:58
     */
    @RequestMapping(value="/reject", method = RequestMethod.POST)
    @ApiOperation(value = "驳回", notes = "驳回")
    public Result reject(OriginalVo originalVo) {
        logger.info(Jurisdiction.getUsername()+"原件驳回");
        Result result = new Result();
        try {
            originalService.reject(originalVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 主动借入原件
     * return
     * Author luosc
     * param
     * Date 2019-04-24 15:32
     */
    @RequestMapping(value="/loanIn", method = RequestMethod.POST)
    @ApiOperation(value = "借入原件", notes = "借入原件")
    public Result loanIn(OriginalVo originalVo) {
        logger.info(Jurisdiction.getUsername()+"借入原件");
        Result result = new Result();
        try {
            originalService.loanIn(originalVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

}
