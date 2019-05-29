package com.mzkj.controller.customer;

import com.github.pagehelper.PageInfo;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.customer.CustomerManager;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.UuidUtil;
import com.mzkj.vo.customer.CustomerQueryVo;
import com.mzkj.vo.customer.CustomerVo;

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
 * 说明：客户管理
 * 创建人：CDCXH
 * 创建时间：2019-05-15
 */
@RestController
@RequestMapping(value = "/customer")
@Api(tags = "CustomerController", description = "客户管理接口")
public class CustomerController {

    private static Logger logger = LogManager.getLogger(CustomerController.class);
	String menuUrl = "customer/list.do"; //菜单地址(权限用)
    @Autowired
	private CustomerManager customerService;

	/**保存
	 * @param
	 */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "查询customer", notes = "保存customer")
	public Result<CustomerVo> save(CustomerVo customerVo) {
        logger.info(Jurisdiction.getUsername()+"查询客户管理");
        Result<CustomerVo> result = new Result<>();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        customerVo.setCustomerId(UuidUtil.get32UUID());
        try {
            customerVo = customerService.save(customerVo);
            result.setData(customerVo);
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
    @ApiOperation(value = "删除customer", notes = "删除customer")
	public Result delete(@PathVariable("id") String customerId) {
        logger.info(Jurisdiction.getUsername()+"删除客户管理");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            customerService.delete(customerId);
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
    @ApiOperation(value = "修改customer", notes = "修改customer")
	public Result edit(CustomerVo customerVo) {
        logger.info(Jurisdiction.getUsername()+"修改客户管理");
        Result result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            customerService.edit(customerVo);
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
    @ApiOperation(value = "分页查询customer", notes = "分页查询customer")
	public Result<PageInfo<CustomerQueryVo>> list(CustomerQueryVo customerQueryVo) {
        logger.info(Jurisdiction.getUsername()+"查看客户管理");
        Result<PageInfo<CustomerQueryVo>> result = new Result<>();
        if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            PageInfo<CustomerQueryVo>	varList = customerService.list(customerQueryVo);
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
     * 查询所有客户列表
     * return
     * Author luosc
     * param
     * Date 2019-05-15 9:57
     */
    @RequestMapping(value="/listAll", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有customer", notes = "查询所有customer")
    public Result<List<CustomerQueryVo>> listAll(CustomerQueryVo customerQueryVo) {
        Result<List<CustomerQueryVo>> result = new Result<>();
        try {
            List<CustomerQueryVo>	varList = customerService.listAll(customerQueryVo);
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
