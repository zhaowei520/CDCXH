package com.mzkj.controller.activiti.ruprocdef;

import com.github.pagehelper.PageInfo;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.activiti.ruprocdef.RuprocdefQueryVo;
import com.mzkj.vo.activiti.ruprocdef.RuprocdefVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.mzkj.service.activiti.ruprocdef.RuprocdefManager;

import java.util.List;

/**
 * 说明：工作流历史信息
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 */
@RestController
@RequestMapping(value = "/ruprocdef")
@Api(tags = "RuprocdefController", description = "工作流历史信息接口")
public class RuprocdefController {

    private static Logger logger = LogManager.getLogger(RuprocdefController.class);
	String menuUrl = "ruprocdef/list.do"; //菜单地址(权限用)
    @Autowired
	private RuprocdefManager ruprocdefService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询ruprocdef", notes = "保存ruprocdef")
	public Result<RuprocdefVo> save(RuprocdefVo ruprocdefVo) {
        logger.info(Jurisdiction.getUsername()+"查询工作流历史信息");
        Result<RuprocdefVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        ruprocdefVo.setId(UuidUtil.get32UUID());
        try {
            ruprocdefVo = ruprocdefService.save(ruprocdefVo);
            result.setData(ruprocdefVo);
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
    @ApiOperation(value = "删除ruprocdef", notes = "删除ruprocdef")
	public Result delete(@PathVariable("id") String ruprocdefId) {
        logger.info(Jurisdiction.getUsername()+"删除工作流历史信息");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            ruprocdefService.delete(ruprocdefId);
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
    @ApiOperation(value = "修改ruprocdef", notes = "修改ruprocdef")
	public Result edit(RuprocdefVo ruprocdefVo) {
        logger.info(Jurisdiction.getUsername()+"修改工作流历史信息");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            ruprocdefService.edit(ruprocdefVo);
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
    @ApiOperation(value = "分页查询ruprocdef", notes = "分页查询ruprocdef")
	public Result<PageInfo<RuprocdefQueryVo>> list(RuprocdefQueryVo ruprocdefQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看工作流历史信息");
        Result<PageInfo<RuprocdefQueryVo>> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<RuprocdefQueryVo>	varList = ruprocdefService.list(ruprocdefQueryVo);
            result.setData(varList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
		return result;
	}

    /**查看流程记录信息
     * @throws Exception
     */
    @RequestMapping(value="/viewProcess", method = RequestMethod.GET)
    @ApiOperation(value = "查看流程记录信息", notes = "查看流程记录信息")
    public Result<List<RuprocdefQueryVo>> viewProcess(RuprocdefQueryVo ruprocdefQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看流程记录信息");
        Result<List<RuprocdefQueryVo>> result = new Result<>();
        try {
            List<RuprocdefQueryVo> ruprocdefQueryVoList = ruprocdefService.viewProcess(ruprocdefQueryVo);
            result.setData(ruprocdefQueryVoList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

}
