package com.mzkj.controller.process;

import com.github.pagehelper.PageInfo;
import com.mzkj.controller.base.BaseController;
import com.mzkj.domain.MyPageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.process.GShangChangeManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.GShangChangeQueryVo;
import com.mzkj.vo.process.GShangChangeVo;

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
 * 说明：工商变更
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@RestController
@RequestMapping(value = "/gShangChange")
@Api(tags = "GShangChangeController", description = "工商变更接口")
public class GShangChangeController extends BaseController{

    private static Logger logger = LogManager.getLogger(GShangChangeController.class);
    String menuUrl = "gshangchange/list.do"; //菜单地址(权限用)
    @Autowired
    private GShangChangeManager gShangChangeService;

    /**
     * 保存
     *
     * @param
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询gShangChange", notes = "保存gShangChange")
    public Result<GShangChangeVo> save(GShangChangeVo gShangChangeVo) {
        logger.info(Jurisdiction.getUsername() + "查询工商变更");
        Result<GShangChangeVo> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        gShangChangeVo.setGShangChangeId(UuidUtil.get32UUID());
        try {
            gShangChangeVo = gShangChangeService.save(gShangChangeVo);
            result.setData(gShangChangeVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.toString());
        }
        return result;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除gShangChange", notes = "删除gShangChange")
    public Result delete(@PathVariable("id") String gShangChangeId) {
        logger.info(Jurisdiction.getUsername() + "删除工商变更");
        Result result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            gShangChangeService.delete(gShangChangeId);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 修改
     *
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改gShangChange", notes = "修改gShangChange")
    public Result edit(GShangChangeVo gShangChangeVo) {
        logger.info(Jurisdiction.getUsername() + "修改工商变更");
        Result result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            gShangChangeService.edit(gShangChangeVo);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 列表
     *
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询gShangChange", notes = "分页查询gShangChange")
    public Result<PageInfo<GShangChangeQueryVo>> list(GShangChangeQueryVo gShangChangeQueryVo) {
        logger.info(Jurisdiction.getUsername() + "查看工商变更");
        Result<PageInfo<GShangChangeQueryVo>> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<GShangChangeQueryVo> varList = gShangChangeService.list(gShangChangeQueryVo);
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
     * 根据部门ID查询流程
     * return
     * Author luosc
     * param
     * Date 2019-07-15 9:36
     */
    @RequestMapping(value ="/listProcessByDepartmentId",method = RequestMethod.POST)
    @ApiOperation(value = "根据部门查询当前部门下所有工商变更跟进流程",notes = "根据部门ID查询工商变更跟进流程")
    public Result<MyPageInfo<String, Integer, FollowUpQueryVo>> listProcessByDepartmentId(FollowUpQueryVo followUpQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看工商变更跟进信息");
        Result<MyPageInfo<String,Integer,FollowUpQueryVo>> result = new Result<>();
        try {
            MyPageInfo<String,Integer,FollowUpQueryVo>	varList = gShangChangeService.listProcessByDepartmentId(followUpQueryVo);
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
     * 根据创建人查询工商变更跟进流程
     *
     * @throws Exception
     */
    @RequestMapping(value = "/listProcessByUser", method = RequestMethod.POST)
    @ApiOperation(value = "根据创建人查询工商变更跟进流程", notes = "根据创建人查询工商变更跟进流程")
    public Result<MyPageInfo<String,Integer,FollowUpQueryVo>> listProcessByUser(FollowUpQueryVo followUpQueryVo) {
        logger.info(Jurisdiction.getUsername() + "查看工商变更跟进信息");
        Result<MyPageInfo<String,Integer,FollowUpQueryVo>> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            MyPageInfo<String,Integer,FollowUpQueryVo> varList =
                gShangChangeService.listProcessByUser(followUpQueryVo);
            varList.setList(addCHNName(varList.getList()));
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
