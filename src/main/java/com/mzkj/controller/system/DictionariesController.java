package com.mzkj.controller.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.system.DictionariesManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.system.DictionariesQueryVo;
import com.mzkj.vo.system.DictionariesVo;

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
 * 说明：字典
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@RestController
@RequestMapping(value = "/dictionaries")
@Api(tags = "DictionariesController", description = "字典接口")
public class DictionariesController {

    private static Logger logger = LogManager.getLogger(DictionariesController.class);
	String menuUrl = "dictionaries/list.do"; //菜单地址(权限用)
    @Autowired
	private DictionariesManager dictionariesService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询dictionaries", notes = "保存dictionaries")
	public Result<DictionariesVo> save(DictionariesVo dictionariesVo) {
        logger.info(Jurisdiction.getUsername()+"查询字典");
        Result<DictionariesVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        dictionariesVo.setDictionariesId(UuidUtil.get32UUID());
        try {
            dictionariesVo = dictionariesService.save(dictionariesVo);
            result.setData(dictionariesVo);
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
	public Result delete(@PathVariable("id") String dictionariesId) {
        logger.info(Jurisdiction.getUsername()+"删除字典");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            dictionariesService.delete(dictionariesId);
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
	public Result edit(DictionariesVo dictionariesVo) {
        logger.info(Jurisdiction.getUsername()+"修改字典");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            dictionariesService.edit(dictionariesVo);
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
	public Result<PageInfo<DictionariesQueryVo>> list(DictionariesQueryVo dictionariesQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看字典");
        Result<PageInfo<DictionariesQueryVo>> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<DictionariesQueryVo>	varList = dictionariesService.list(dictionariesQueryVo);
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
	 * 通过编码查询子菜单列表
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-24 11:03
	 */
    @RequestMapping(value="/findChildlListByBianma", method = RequestMethod.GET)
    @ApiOperation(value = "通过编码查询子菜单列表", notes = "通过编码查询子菜单列表")
    public Result<List<DictionariesQueryVo>> findChildlListByBianma(String bianma) {
        logger.info(Jurisdiction.getUsername()+"查看字典");
        Result<List<DictionariesQueryVo>> result = new Result<>();
        try {
            List<DictionariesQueryVo>	varList = dictionariesService.findChildlListByBianma(bianma);
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
     * 通过编码查询字典tree
     * return
     * Author luosc
     * param
     * Date 2019-05-15 14:30
     */
    @RequestMapping(value="/findChildlTreeListByBianma", method = RequestMethod.GET)
    @ApiOperation(value = "通过编码查询子菜单Tree列表", notes = "通过编码查询子菜单Tree列表")
    public Result<DictionariesQueryVo> findTreelListByBianma(String bianma) {
        logger.info(Jurisdiction.getUsername()+"查看字典");
        Result<DictionariesQueryVo> result = new Result<>();
        try {
            DictionariesQueryVo	dictionariesQueryVo = dictionariesService.findDicTreeByBianma(bianma);
            result.setData(dictionariesQueryVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
