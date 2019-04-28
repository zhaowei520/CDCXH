package com.mzkj.service.system;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.UserBean;
import com.mzkj.vo.system.UserQueryVo;
import com.mzkj.vo.system.UserVo;

import java.util.List;

/**
 * 说明： 用户管理接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
public interface UserManager {

    /**
     * 新增
     *
     * @param userVo
     * @throws Exception
     */
    public UserVo save(UserVo userVo) throws Exception;

    /**
     * 删除
     *
     * @param USER_ID
     * @throws Exception
     */
    public void delete(String USER_ID) throws Exception;

    /**
     * 修改
     *
     * @param userVo
     * @throws Exception
     */
    public void edit(UserVo userVo) throws Exception;

    /**
     * 列表
     *
     * @param userQueryVo
     * @throws Exception
     */
    public PageInfo<UserQueryVo> list(UserQueryVo userQueryVo) throws Exception;

    /**
     * 根据登录名查询用户数据
     *
     * @param username
     * @throws Exception
     */
    public UserVo findByUsername(String username) throws Exception;

    /**
     * 查询所有用户
     *luosc
     * @param userQueryVo
     * @throws Exception
     */
    public List<UserQueryVo> listAll(UserQueryVo userQueryVo) throws Exception;
}

