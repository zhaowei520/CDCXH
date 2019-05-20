package com.mzkj.mapper.privilege;

import com.mzkj.bean.PrivilegeBean;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrivilegeMapper {

    public List<PrivilegeBean> datalistPage(PrivilegeBean privilegeBean);

    public void update(PrivilegeBean privilegeBean);

    public PrivilegeBean findById(PrivilegeBean privilegeBean);

    public void insert(PrivilegeBean privilegeBean);
}
