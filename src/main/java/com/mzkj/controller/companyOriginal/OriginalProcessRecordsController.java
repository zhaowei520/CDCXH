package com.mzkj.controller.companyOriginal;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.companyOriginal.OriginalProcessRecordsManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.companyOriginal.OriginalProcessRecordsQueryVo;
import com.mzkj.vo.companyOriginal.OriginalProcessRecordsVo;

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
 * 说明：原件流转记录
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@RestController
@RequestMapping(value = "/originalprocessrecords")
@Api(tags = "OriginalProcessRecordsController", description = "原件流转记录接口")
public class OriginalProcessRecordsController {

    private static Logger logger = LogManager.getLogger(OriginalProcessRecordsController.class);
	String menuUrl = "originalprocessrecords/list.do"; //菜单地址(权限用)
    @Autowired
	private OriginalProcessRecordsManager originalprocessrecordsService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询originalprocessrecords", notes = "保存originalprocessrecords")
	public Result<OriginalProcessRecordsVo> save(OriginalProcessRecordsVo originalprocessrecordsVo) {
        logger.info(Jurisdiction.getUsername()+"查询原件流转记录");
        Result<OriginalProcessRecordsVo> result = new Result<>();
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
//            result.setMsg("没有操作权限，请联系管理员");
//            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
//            return result;
//        }
        originalprocessrecordsVo.setOriginalProcessRecordsId(UuidUtil.get32UUID());
        try {
            originalprocessrecordsVo = originalprocessrecordsService.save(originalprocessrecordsVo);
            result.setData(originalprocessrecordsVo);
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
	public Result delete(@PathVariable("id") String originalProcessRecordsId) {
        logger.info(Jurisdiction.getUsername()+"删除原件流转记录");
        Result result = new Result<>();
//        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
//            result.setMsg("没有操作权限，请联系管理员");
//            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
//            return result;
//        }
        try {
            originalprocessrecordsService.delete(originalProcessRecordsId);
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
	public Result edit(OriginalProcessRecordsVo originalprocessrecordsVo) {
        logger.info(Jurisdiction.getUsername()+"修改原件流转记录");
        Result result = new Result<>();
//        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
//            result.setMsg("没有操作权限，请联系管理员");
//            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
//            return result;
//        }
        try {
            originalprocessrecordsService.edit(originalprocessrecordsVo);
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
	public Result<PageInfo<OriginalProcessRecordsQueryVo>> list(OriginalProcessRecordsQueryVo originalprocessrecordsQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看原件流转记录");
        Result<PageInfo<OriginalProcessRecordsQueryVo>> result = new Result<>();
//        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
//            result.setMsg("没有操作权限，请联系管理员");
//            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
//            return result;
//        }
        try {
            PageInfo<OriginalProcessRecordsQueryVo>	varList = originalprocessrecordsService.list(originalprocessrecordsQueryVo);
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
