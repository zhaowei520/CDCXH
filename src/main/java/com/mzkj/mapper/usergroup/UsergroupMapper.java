package com.mzkj.mapper.usergroup;


import com.mzkj.bean.UsergroupBean;
import com.mzkj.bean.UsergroupDeleteBean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsergroupMapper {

    public List<UsergroupBean> datalistPage(UsergroupBean usergroupBean) throws Exception;

    public void save(UsergroupBean usergroupBean);

    public void update(UsergroupBean usergroupBean);

    public UsergroupBean findById(@Param("usergroupId") String usergroupId);

    public List<UsergroupBean> findByParentId(String parentId);

    public void delete(UsergroupDeleteBean usergroupDeleteBean);

}
