package com.mzkj.mapper.masterAccessOperation;

import com.mzkj.bean.MasterAccessOperationMappingBean;
import com.mzkj.bean.PrivilegeOfUsergroupBean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterAccessOperationMapper {

    public List<PrivilegeOfUsergroupBean> findPrivilegesByUsergroup(PrivilegeOfUsergroupBean privilegesByUsergroupBean);

    public void addAccess2Master(List<MasterAccessOperationMappingBean> masterAccessOperationMappingBean);

    public void addPrivilege2Usergroup(PrivilegeOfUsergroupBean privilegeOfUsergroupBean);

    public void deleteMasterAccessOperationMapping(@Param("mappingIds") String[] mappingIds
            , @Param("tenantId") String tenantId, @Param("updateUser") String updateUser);
}
