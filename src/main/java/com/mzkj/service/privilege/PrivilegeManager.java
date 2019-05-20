package com.mzkj.service.privilege;

import com.mzkj.vo.privilege.PrivilegeQueryVo;

import java.util.List;

public interface PrivilegeManager {

    public List<PrivilegeQueryVo> datalistPage(PrivilegeQueryVo privilegeQueryVo);

    public void update(PrivilegeQueryVo privilegeQueryVo);

    public PrivilegeQueryVo findById(PrivilegeQueryVo privilegeQueryVo);

    public void insert(PrivilegeQueryVo privilegeQueryVo);

}
