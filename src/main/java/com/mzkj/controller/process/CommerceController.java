package com.mzkj.controller.process;

import com.github.pagehelper.PageInfo;
import com.mzkj.controller.base.BaseController;
import com.mzkj.domain.MyPageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.process.CommerceManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.process.CommerceQueryVo;
import com.mzkj.vo.process.CommerceVo;

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
 * 说明：工商注册
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@RestController
@RequestMapping(value = "/commerce")
@Api(tags = "CommerceController", description = "工商注册接口")
public class CommerceController extends BaseController {

    private static Logger logger = LogManager.getLogger(CommerceController.class);
    String menuUrl = "commerce/list.do"; //菜单地址(权限用)
    @Autowired
    private CommerceManager commerceService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询commerce", notes = "保存commerce")
	public Result<CommerceVo> save(CommerceVo commerceVo) {
        logger.info(Jurisdiction.getUsername()+"查询工商注册");
        Result<CommerceVo> result = new Result<>();
        commerceVo.setCommerceId(UuidUtil.get32UUID());
        try {
            commerceVo = commerceService.save(commerceVo);
            result.setData(commerceVo);
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
    @ApiOperation(value = "删除commerce", notes = "删除commerce")
	public Result delete(@PathVariable("id") String commerceId) {
        logger.info(Jurisdiction.getUsername()+"删除工商注册");
        Result result = new Result<>();
        try {
            commerceService.delete(commerceId);
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
    @ApiOperation(value = "修改commerce", notes = "修改commerce")
	public Result edit(CommerceVo commerceVo) {
        logger.info(Jurisdiction.getUsername()+"修改工商注册");
        Result result = new Result<>();
        try {
            commerceService.edit(commerceVo);
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
    @ApiOperation(value = "分页查询commerce", notes = "分页查询commerce")
	public Result<PageInfo<CommerceQueryVo>> list(CommerceQueryVo commerceQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看工商注册");
        Result<PageInfo<CommerceQueryVo>> result = new Result<>();
        try {
            PageInfo<CommerceQueryVo>	varList = commerceService.list(commerceQueryVo);
            result.setData(varList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
		return result;
	}


    /**根据创建人查询工商注册跟进流程
     * @throws Exception
     */
    @RequestMapping(value="/listProcessByUser", method = RequestMethod.POST)
    @ApiOperation(value = "根据创建人查询工商注册跟进流程", notes = "根据创建人查询工商注册跟进流程")
    public Result<MyPageInfo<String,Integer,FollowUpQueryVo>> listProcessByUser(FollowUpQueryVo followUpQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看工商注册跟进信息");
        Result<MyPageInfo<String,Integer,FollowUpQueryVo>> result = new Result<>();
        try {
            MyPageInfo<String,Integer,FollowUpQueryVo>	varList = commerceService.listProcessByUser(followUpQueryVo);
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
