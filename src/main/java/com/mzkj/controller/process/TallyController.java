package com.mzkj.controller.process;

import com.github.pagehelper.PageInfo;
import com.mzkj.controller.base.BaseController;
import com.mzkj.domain.MyPageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.process.TallyManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.TallyQueryVo;
import com.mzkj.vo.process.TallyVo;

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
 * 说明：代理记账
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@RestController
@RequestMapping(value = "/tally")
@Api(tags = "TallyController", description = "代理记账接口")
public class TallyController extends BaseController {

    private static Logger logger = LogManager.getLogger(TallyController.class);
    String menuUrl = "tally/list.do"; //菜单地址(权限用)
    @Autowired
    private TallyManager tallyService;

    /**
     * 保存
     *
     * @param
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询tally", notes = "保存tally")
    public Result<TallyVo> save(TallyVo tallyVo) {
        logger.info(Jurisdiction.getUsername() + "查询代理记账");
        Result<TallyVo> result = new Result<>();
        tallyVo.setTallyId(UuidUtil.get32UUID());
        try {
            tallyVo = tallyService.save(tallyVo);
            result.setData(tallyVo);
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
    @ApiOperation(value = "删除tally", notes = "删除tally")
    public Result delete(@PathVariable("id") String tallyId) {
        logger.info(Jurisdiction.getUsername() + "删除代理记账");
        Result result = new Result<>();
        try {
            tallyService.delete(tallyId);
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
    @ApiOperation(value = "修改tally", notes = "修改tally")
    public Result edit(TallyVo tallyVo) {
        logger.info(Jurisdiction.getUsername() + "修改代理记账");
        Result result = new Result<>();
        try {
            tallyService.edit(tallyVo);
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
    @ApiOperation(value = "分页查询tally", notes = "分页查询tally")
    public Result<PageInfo<TallyQueryVo>> list(TallyQueryVo tallyQueryVo) {
        logger.info(Jurisdiction.getUsername() + "查看代理记账");
        Result<PageInfo<TallyQueryVo>> result = new Result<>();
        try {
            PageInfo<TallyQueryVo> varList = tallyService.list(tallyQueryVo);
            result.setData(varList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**根据创建人查询代理记账跟进流程
     * @throws Exception
     */
    @RequestMapping(value="/listProcessByUser", method = RequestMethod.POST)
    @ApiOperation(value = "根据创建人查询代理记账跟进流程", notes = "根据创建人查询代理记账跟进流程")
    public Result<MyPageInfo<String,Integer,FollowUpQueryVo>> listProcessByUser(FollowUpQueryVo followUpQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看代理记账流程跟进信息");
        Result<MyPageInfo<String,Integer,FollowUpQueryVo>> result = new Result<>();
        try {
            MyPageInfo<String,Integer,FollowUpQueryVo>	varList = tallyService.listProcessByUser(followUpQueryVo);
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
