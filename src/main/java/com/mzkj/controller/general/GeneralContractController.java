package com.mzkj.controller.general;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.general.GeneralContractQueryVo;
import com.mzkj.vo.general.GeneralContractVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.mzkj.service.general.GeneralContractManager;

import java.util.Map;

/**
 * 说明：其它合同管理
 * 创建人：CDCXH
 * 创建时间：2019-06-03
 */
@RestController
@RequestMapping(value = "/generalContract")
@Api(tags = "GeneralContractController", description = "其它合同管理接口")
public class GeneralContractController {

    private static Logger logger = LogManager.getLogger(GeneralContractController.class);
	String menuUrl = "generalcontract/list.do"; //菜单地址(权限用)
    @Autowired
	private GeneralContractManager generalContractService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询generalContract", notes = "保存generalContract")
	public Result<GeneralContractVo> save(@RequestBody GeneralContractVo generalContractVo) {
        logger.info(Jurisdiction.getUsername()+"保存其它合同管理");
        Result<GeneralContractVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        generalContractVo.setGeneralContractId(UuidUtil.get32UUID());
        try {
            generalContractVo = generalContractService.save(generalContractVo);
            result.setData(generalContractVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
         }
		return result;
	}
	/**
	 * 从前端页面保存数据
	 * return
	 * Author luosc
	 * param
	 * Date 2019-06-05 8:59
	 */
    @RequestMapping(value = "/saveFromPage", method = RequestMethod.POST)
    @ApiOperation(value = "查询generalContract", notes = "保存generalContract")
    public Result<GeneralContractVo> saveFromPage(GeneralContractVo generalContractVo) {
        logger.info(Jurisdiction.getUsername()+"保存其它合同管理");
        Result<GeneralContractVo> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        generalContractVo.setGeneralContractId(UuidUtil.get32UUID());
        try {
            generalContractVo = generalContractService.save(generalContractVo);
            result.setData(generalContractVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        return result;
    }
	/**
	 * 根据ID查询
	 * return
	 * Author luosc
	 * param
	 * Date 2019-06-03 15:01
	 */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询generalContract", notes = "查询generalContract")
    public Result<GeneralContractQueryVo> findById(@PathVariable("id") String generalContractId) {
        logger.info(Jurisdiction.getUsername()+"查询其它合同管理");
        Result<GeneralContractQueryVo> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            GeneralContractQueryVo generalContractQueryVo = generalContractService.findById(generalContractId);
            result.setData(generalContractQueryVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        return result;
    }

    /**
     * 根据businessId查询
     * return
     * Author luosc
     * param
     * Date 2019-06-03 15:01
     */
    @RequestMapping(value = "/findByBusinessId", method = RequestMethod.GET)
    @ApiOperation(value = "查询generalContract", notes = "查询generalContract")
    public Result<GeneralContractQueryVo> findByBusinessId(String businessId) {
        Result<GeneralContractQueryVo> result = new Result<>();
        try {
            GeneralContractQueryVo generalContractQueryVo = generalContractService.findByBusinessId(businessId);
            result.setData(generalContractQueryVo);
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
    @ApiOperation(value = "删除generalContract", notes = "删除generalContract")
	public Result delete(@PathVariable("id") String generalContractId) {
        logger.info(Jurisdiction.getUsername()+"删除其它合同管理");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            generalContractService.delete(generalContractId);
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
    @ApiOperation(value = "修改generalContract", notes = "修改generalContract")
	public Result edit(@RequestBody GeneralContractVo generalContractVo) {
        logger.info(Jurisdiction.getUsername()+"修改其它合同管理");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            generalContractService.edit(generalContractVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
		return result;
	}
	/**
	 * 前端页面修改
	 * return
	 * Author luosc
	 * param
	 * Date 2019-06-05 9:04
	 */
    @RequestMapping(value="/editFromPage", method = RequestMethod.POST)
    @ApiOperation(value = "修改generalContract", notes = "修改generalContract")
    public Result editFormPage( GeneralContractVo generalContractVo) {
        logger.info(Jurisdiction.getUsername()+"修改其它合同管理");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            generalContractService.edit(generalContractVo);
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
    @ApiOperation(value = "分页查询generalContract", notes = "分页查询generalContract")
	public Result<PageData> list(GeneralContractQueryVo generalContractQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看其它合同管理");
        Result<PageData> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<GeneralContractQueryVo>	varList = generalContractService.list(generalContractQueryVo);

            PageData resultdata = new PageData();
            resultdata.put("varList", varList);
            resultdata.put("QX",getButtonPermissionFromSession() );
            result.setData(resultdata);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
		return result;
	}
    public Map<String, String> getButtonPermissionFromSession() {
        return Jurisdiction.getHC();
    }
}
