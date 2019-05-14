package com.mzkj.mapper.usergroup;


import com.mzkj.bean.UsergroupBean;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsergroupMapper {

    public List<UsergroupBean> datalistPage(UsergroupBean usergroupBean) throws Exception;

    public void save(UsergroupBean usergroupBean);

    public void update(UsergroupBean usergroupBean);

}
