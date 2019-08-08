package com.mzkj.service.system.impl;

import com.fh.util.PageData;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.RoleBean;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.system.RoleQueryVo;
import com.mzkj.vo.system.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import com.mzkj.service.system.RoleManager;
import com.mzkj.mapper.system.RoleMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明： 系统角色
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 * @version
 */
@Service("roleService")
public class RoleService implements RoleManager{

    @Autowired
    private RoleMapper roleMapper;

	/**新增
	 * @param roleVo
	 * @throws Exception
	 */
    @Override
	public RoleVo save(RoleVo roleVo)throws Exception{
        RoleBean roleBean = ConvertUtil.objectCopyParams(roleVo, RoleBean.class);
        roleMapper.save(roleBean);
        roleVo = ConvertUtil.objectCopyParams(roleBean, RoleVo.class);
        return roleVo;
	}

	/**删除
	 * @param roleId
	 * @throws Exception
	 */
    @Override
	public void delete(String roleId)throws Exception{
        roleMapper.delete(roleId);
	}

	/**修改
	 * @param roleVo
	 * @throws Exception
	 */
    @Override
	public void edit(RoleVo roleVo)throws Exception{
        RoleBean roleBean = ConvertUtil.objectCopyParams(roleVo, RoleBean.class);
        roleMapper.edit(roleBean);
	}

	/**列表
	 * @param roleQueryVo
	 * @throws Exception
	 */
    @Override
	public PageInfo<RoleQueryVo> list(RoleQueryVo roleQueryVo)throws Exception{
        //将vo转DO并将分页信息传到pageHelper
        RoleBean roleBean = PageUtil.startPageAndObjectCopyParams(roleQueryVo, RoleBean.class);
        //设置租户ID
        roleBean.setTenantId(Jurisdiction.getTenant());
        List<RoleBean> rolePageBean = roleMapper.list(roleBean);
        //DO转VO
        List<RoleQueryVo> roleQueryVoList = (List<RoleQueryVo>) ConvertUtil.castListObjectToTargetList(rolePageBean,RoleQueryVo.class);
        PageInfo<RoleQueryVo> pageInfo = new PageInfo<>(roleQueryVoList);
         return pageInfo;
	}

	@Override
	public RoleVo getRoleByRnumber(String rnumber) throws Exception {
		RoleBean roleBean = roleMapper.getRoleByRnumber(rnumber, Jurisdiction.getTenant());
		RoleVo roleVo = ConvertUtil.objectCopyParams(roleBean, RoleVo.class);
		return roleVo;
	}
	/**
	 * 根据 RoleId 及 UrlPrefix 获取按钮权限及页面元素权限
	 * return
	 * Author luosc
	 * param
	 * Date 2019-07-31 15:35
	 */
	@Override
	public PageData getPowerByRoleIdAndUrlPrefix(String urlPrefix) throws Exception {
		//从Session中获取RoleIds
		List<String> roleIds = Jurisdiction.getRoleIds();
		List<PageData> powerList = new ArrayList<>();
		PageData param = new PageData();
		param.put("tenantId", Jurisdiction.getTenant());
		param.put("urlPrefix", urlPrefix);
		if (Jurisdiction.getUsername().equals("admin")) {
			List<PageData> list = roleMapper.getPowerByRoleIdAndUrlPrefix(param);
			powerList.addAll(list);
		} else {
			for (String roleId:roleIds) {
				param.put("roleId", roleId);
				List<PageData> list = roleMapper.getPowerByRoleIdAndUrlPrefix(param);
				powerList.addAll(list);
			}
		}
		PageData QX = new PageData();
		for (PageData qxPageData:powerList) {
			QX.put(qxPageData.getString("OPERATION_CODE"), 1);//按钮权限
			//QX.put(qxPageData.getString("PAGE_ELEMENT_NAME"), 1);//页面元素权限
		}
		return QX;
	}
}

