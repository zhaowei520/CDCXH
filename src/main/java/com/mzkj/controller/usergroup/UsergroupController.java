package com.mzkj.controller.usergroup;

import com.mzkj.service.usergroup.UsergroupManager;
import com.mzkj.util.UuidUtil;
import com.mzkj.util.enums.HttpCode;
import com.mzkj.vo.Result;
import com.mzkj.vo.usergroup.UsergroupQueryVo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 说明：用户组管理 创建人：FH Q313596790 创建时间：2019-05-08
 */
@RestController
@RequestMapping(value = "/usergroup")
@Api(tags = "UsergroupController", description = "用户组接口")
public class UsergroupController {

    private static Logger logger = LogManager.getLogger(UsergroupController.class);

    @Autowired
    private UsergroupManager usergroupService;

    public UsergroupManager getUsergroupService() {
        return usergroupService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询usergroup", notes = "分页查询usergroup")
    public Result list(final UsergroupQueryVo usergroupQueryVo) {
        Result<List<UsergroupQueryVo>> result = new Result<>();
        try {
            List<UsergroupQueryVo> usergroupQueryVos = getUsergroupService().list(usergroupQueryVo);
            result.setData(usergroupQueryVos);
        } catch (Exception e) {
            logger.error(e);
            result.setSuccess(false);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setMsg("后台报错");
            return result;
        }
        return result;
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存usergroup", notes = "保存usergroup")
    public Result<UsergroupQueryVo> save(UsergroupQueryVo usergroupQueryVo) {
        Result<UsergroupQueryVo> result = new Result<>();
        try {
            usergroupQueryVo.setUsergroupId(UuidUtil.get32UUID());
            getUsergroupService().save(usergroupQueryVo);
            result.setSuccess(Boolean.TRUE);
            result.setData(usergroupQueryVo);
        } catch (Exception e) {
            logger.error("保存用户组报错", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("保存用户组报错");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新usergroup", notes = "更新usergroup")
    public Result update(UsergroupQueryVo usergroupQueryVo) {
        Result result = new Result();
        try {
            getUsergroupService().update(usergroupQueryVo);
            result.setMsg("更新用户组成功");
            result.setSuccess(Boolean.TRUE);
            result.setStatus(HttpCode.OK.getCode());
        } catch (Exception e) {
            logger.error("更新用户组报错", e);
            result.setSuccess(Boolean.FALSE);
            result.setMsg("更新用户组报错");
            result.setStatus(HttpCode.ERROR.getCode());
        }
        return result;
    }

    /**
     * 显示列表ztree
     * @param model
     * @return
     */
//	@RequestMapping(value="/listTree")
//	public ModelAndView listTree(Model model,String PRIVILEGE_ID)throws Exception{
//		ModelAndView mv = this.getModelAndView();
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		try{
//			JSONArray arr = JSONArray.fromObject(privilegeService.listTree("0"));
//			String json = arr.toString();
//			json = json.replaceAll("PRIVILEGE_ID", "id").replaceAll("PARENT_ID", "pId").replaceAll("NAME", "name").replaceAll("subPrivilege", "nodes").replaceAll("hasPrivilege", "checked").replaceAll("treeurl", "url");
//			model.addAttribute("zTreeNodes", json);
//			mv.addObject("PRIVILEGE_ID",PRIVILEGE_ID);
//			mv.addObject("pd", pd);
//			mv.setViewName("privilege/privilege/privilege_tree");
//		} catch(Exception e){
//			logger.error(e.toString(), e);
//		}
//		return mv;
//	}


}
