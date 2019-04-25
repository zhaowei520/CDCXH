package com.mzkj.controller.companyOriginal;

import com.github.pagehelper.PageInfo;
import com.mzkj.util.Const;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.companyOriginal.OriginalQueryVo;
import com.mzkj.vo.companyOriginal.OriginalVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.mzkj.service.companyOriginal.OriginalManager;

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
	String menuUrl = "original/list.do"; //菜单地址(权限用)
    @Autowired
	private OriginalManager originalService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询original", notes = "保存original")
	public Result<OriginalVo> save(OriginalVo originalVo) {
        logger.info(Jurisdiction.getUsername()+"查询公司原件详情");
        Result<OriginalVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        originalVo.setOriginalId(UuidUtil.get32UUID());
        try {
            originalVo = originalService.save(originalVo);
            result.setData(originalVo);
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
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
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
	@RequestMapping(value="/edit", method = RequestMethod.PUT)
    @ApiOperation(value = "修改template", notes = "修改template")
	public Result edit(OriginalVo originalVo) {
        logger.info(Jurisdiction.getUsername()+"修改公司原件详情");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
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

	/**列表
	 * @throws Exception
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询template", notes = "分页查询template")
	public Result<PageInfo<OriginalQueryVo>> list(OriginalQueryVo originalQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看公司原件详情");
        Result<PageInfo<OriginalQueryVo>> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<OriginalQueryVo>	varList = originalService.list(originalQueryVo);
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
