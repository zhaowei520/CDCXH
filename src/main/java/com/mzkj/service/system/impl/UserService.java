package com.mzkj.service.system.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.UserBean;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.system.UserQueryVo;
import com.mzkj.vo.system.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;

import com.mzkj.service.system.UserManager;
import com.mzkj.mapper.system.UserMapper;

import java.util.ArrayList;
import java.util.List;

/** 
 * 说明： 用户管理
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@Service("userService")
public class UserService implements UserManager {

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增
     *
     * @param userVo
     * @throws Exception
     */
    @Override
    public UserVo save(UserVo userVo) throws Exception {
        UserBean userBean = ConvertUtil.objectCopyParams(userVo, UserBean.class);
        userMapper.save(userBean);
        userVo = ConvertUtil.objectCopyParams(userBean, UserVo.class);
        return userVo;
    }

    /**
     * 删除
     *
     * @param USER_ID
     * @throws Exception
     */
    @Override
    public void delete(String USER_ID) throws Exception {
        userMapper.delete(USER_ID);
    }

    /**
     * 修改
     *
     * @param userVo
     * @throws Exception
     */
    @Override
    public void edit(UserVo userVo) throws Exception {
        UserBean userBean = ConvertUtil.objectCopyParams(userVo, UserBean.class);
        userMapper.edit(userBean);
    }

    /**
     * 列表
     *
     * @param userQueryVo
     * @throws Exception
     */
    @Override
    public PageInfo<UserQueryVo> list(UserQueryVo userQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        UserBean userBean = PageUtil.startPageAndObjectCopyParams(userQueryVo, UserBean.class);
        List<UserBean> userPageBean = userMapper.list(userBean);
        PageInfo<UserBean> pageInfo = new PageInfo<>(userPageBean);
        //将DO转vo
        PageInfo<UserQueryVo> userPageVo = ConvertUtil.objectCopyParams(pageInfo, PageInfo.class);
        return userPageVo;
    }

    @Override
    public UserVo findByUsername(String username) throws Exception {
        UserBean userBean = userMapper.findByUsername(username);
        UserVo userVo = ConvertUtil.objectCopyParams(userBean, UserVo.class);
        return userVo;
    }

	/**
	 * 获取所有用户,不包含当前登录人
	 * return
	 * Author luosc
	 * param
	 * Date 2019-04-25 11:04
	 */
	@Override
	public List<UserQueryVo> listAllAndFilterSelf(UserQueryVo userQueryVo) throws Exception {
		//将vo转DO
		UserBean userBean = ConvertUtil.objectCopyParams(userQueryVo, UserBean.class);
		userBean.setTenantId(Jurisdiction.getTenant());
        if (null==userBean.getUserType()) {
            userBean.setUserType(0);//员工
        }
		List<UserBean> userBeanList = userMapper.list(userBean);
		//将DO转VO
		List<UserQueryVo> userQueryVoList = new ArrayList<>();
		for (UserBean userBean1:userBeanList
			 ) {
			UserQueryVo queryVo = ConvertUtil.objectCopyParams(userBean1, UserQueryVo.class);
			//过滤当前登录人
            if (!StringUtils.isEmpty(Jurisdiction.getUsername()) && Jurisdiction.getUsername().equals(queryVo.getUsername())) {
            } else {
                userQueryVoList.add(queryVo);
            }
		}
		return userQueryVoList;
	}
    /**
     * 获取所有用户
     * return
     * Author luosc
     * param
     * Date 2019-04-25 11:04
     */
    @Override
    public List<UserQueryVo> listAll(UserQueryVo userQueryVo) throws Exception {
        //将vo转DO
        UserBean userBean = ConvertUtil.objectCopyParams(userQueryVo, UserBean.class);
        userBean.setTenantId(Jurisdiction.getTenant());
        if (null==userBean.getUserType()) {
            userBean.setUserType(0);//员工
        }
        List<UserBean> userBeanList = userMapper.list(userBean);
        //将DO转VO
        List<UserQueryVo> userQueryVoList = new ArrayList<>();
        for (UserBean userBean1:userBeanList
                ) {
            UserQueryVo queryVo = ConvertUtil.objectCopyParams(userBean1, UserQueryVo.class);
                userQueryVoList.add(queryVo);

        }
        return userQueryVoList;
    }
}

