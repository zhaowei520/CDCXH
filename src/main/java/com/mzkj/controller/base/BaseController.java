package com.mzkj.controller.base;


import com.fh.entity.Page;
import com.fh.util.PageData;
import com.mzkj.bean.RoleBean;
import com.mzkj.bean.UserBean;
import com.mzkj.service.companyOriginal.CompanyInformationManager;
import com.mzkj.service.system.RoleManager;
import com.mzkj.service.system.UserManager;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.system.RoleVo;
import com.mzkj.vo.system.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author FH Q313596790
 *         修改时间：2015、12、11
 */
public class BaseController {

    private static final long serialVersionUID = 6357869213649815390L;

    @Autowired
    private RoleManager roleService;

    @Autowired
    private UserManager userService;

    /**
     * new PageData对象
     *
     * @return
     */
    public PageData getPageData() {
        return new PageData(this.getRequest());
    }

    /**
     * 得到request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 得到分页列表的信息
     *
     * @return
     */
    public Page getPage() {
        return new Page();
    }


    /**
     * 添加中文名
     */
    protected List<FollowUpQueryVo> addCHNName(List<FollowUpQueryVo> varList) throws Exception {
        String name = null;
        for (FollowUpQueryVo followUpQueryVo : varList) {
            UserVo userVo = userService.findByUsername(followUpQueryVo.getActAssignee());

            if (userVo == null) {
                RoleVo roleVo = roleService.getRoleByRnumber(followUpQueryVo.getActAssignee());
                if (roleVo == null) {
                    continue;
                }
                name = roleVo.getRoleName();
            } else {
                name = userVo.getName();
            }
            followUpQueryVo.setActAssignee(name);
        }
        return varList;
    }



}
