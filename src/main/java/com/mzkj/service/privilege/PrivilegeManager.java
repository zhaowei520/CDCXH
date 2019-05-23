package com.mzkj.service.privilege;

import com.github.pagehelper.PageInfo;
import com.mzkj.vo.privilege.PrivilegeQueryVo;
import com.mzkj.vo.privilege.PrivilegeVo;
import com.mzkj.vo.privilege.UserOfPrivilegeQueryVo;

public interface PrivilegeManager {

    public PageInfo datalistPage(PrivilegeQueryVo privilegeQueryVo);

    public void update(PrivilegeVo privilegeQueryVo);

    public PrivilegeVo findById(String id);

    public void insert(PrivilegeVo privilegeQueryVo);

    PageInfo findUsersByPrivilege(UserOfPrivilegeQueryVo privilegeId);

    PageInfo findUsersUnselectedByPrivilege(UserOfPrivilegeQueryVo userOfPrivilegeQueryVo);

    void addUsers2Privileges(String privilegeId, String[] userIds, String[] operations);
}
