package com.mzkj.mapper.masterAccessOperation;

import com.mzkj.bean.PrivilegeOfUsergroupBean;
import com.mzkj.bean.UserOfUsergroupBean;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MasterAccessOperationMapper {

    public List<PrivilegeOfUsergroupBean> findPrivilegesByUsergroup(PrivilegeOfUsergroupBean privilegesByUsergroupBean);

    public void addUser2Usergroup(UserOfUsergroupBean userOfUsergroupBean);

    public void addPrivilege2Usergroup(PrivilegeOfUsergroupBean privilegeOfUsergroupBean);

}
