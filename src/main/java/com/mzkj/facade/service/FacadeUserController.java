package com.mzkj.facade.service;

import com.mzkj.domain.Privilege;
import com.mzkj.facade.enums.HttpCode;
import com.mzkj.facade.vo.Result;
import com.mzkj.service.privilege.PrivilegeManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/facade/user")
@Api(tags = "FacadeUserController", description = "根据用户查权限接口")
public class FacadeUserController {

    private static Logger logger = LogManager.getLogger(FacadeUserController.class);

    @Autowired
    private PrivilegeManager privilegeService;

    public PrivilegeManager getPrivilegeService() {
        return privilegeService;
    }

    @RequestMapping(value = "/findAllPrivilegesByUser", method = RequestMethod.POST)
    @ApiOperation(value = "根据用户查权限", notes = "根据用户查权限")
    public Result<List<Privilege>> findAllPrivilegesByUser(@RequestBody String userId) {
        Result<List<Privilege>> result = new Result<>();
        try {
            List<Privilege> privileges = getPrivilegeService().findPrivilegesByUser(userId);
            result.setMsg("根据用户查询权限成功");
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
            result.setData(privileges);
        } catch (Exception e) {
            logger.error(e);
            result.setSuccess(false);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setMsg("根据用户查询权限报错");
            return result;
        }
        return result;
    }


}
