package com.mzkj.mapper.system;

import com.mzkj.bean.UserBean;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 说明： 用户管理接口
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@Mapper
public interface UserMapper {

    /**
     * 新增
     *
     * @param userBean
     * @throws Exception
     */
    public void save(UserBean userBean) throws Exception;

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
     * @param userBean
     * @throws Exception
     */
    public void edit(UserBean userBean) throws Exception;

    /**
     * 列表
     *
     * @param userBean
     * @throws Exception
     */
    public List<UserBean> list(UserBean userBean) throws Exception;

    /**
     * 根据登录名查询用户数据
     *
     * @param username
     * @throws Exception
     */
    public UserBean findByUsername(String username)throws Exception;

    /**
     *
     */
    public List<UserBean> findUsersByUsergroup(UserBean userBean);
}

