package com.mzkj.mapper.privilege;

import com.mzkj.bean.PrivilegeBean;
import com.mzkj.bean.PrivilegeUnselected2UsergroupBean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PrivilegeMapper {

    public List<PrivilegeBean> datalistPage(PrivilegeBean privilegeBean);

    public void update(PrivilegeBean privilegeBean);

    public PrivilegeBean findById(@Param("privilegeId") String privilegeId);

    public void insert(PrivilegeBean privilegeBean);

    List<PrivilegeBean> findPrivilegesUnselected(PrivilegeUnselected2UsergroupBean privilegesUnselected2UsergroupBean);
}
