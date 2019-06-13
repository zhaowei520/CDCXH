package com.mzkj.service.privilege;

import com.github.pagehelper.PageInfo;
import com.mzkj.domain.Privilege;
import com.mzkj.facade.vo.AddUsers2PrivilegeVo;
import com.mzkj.facade.vo.InsertPrivilegeVo;
import com.mzkj.vo.privilege.PrivilegeQueryVo;
import com.mzkj.vo.privilege.PrivilegeVo;
import com.mzkj.vo.privilege.QueryPrivilegesByUserVo;
import com.mzkj.vo.privilege.UserOfPrivilegeQueryVo;

import java.util.List;

public interface PrivilegeManager {

    public PageInfo datalistPage(PrivilegeQueryVo privilegeQueryVo);

    public void update(PrivilegeVo privilegeQueryVo);

    public PrivilegeVo findById(String id);

    public String insert(InsertPrivilegeVo insertPrivilegeVo);

    PageInfo findUsersByPrivilege(UserOfPrivilegeQueryVo privilegeId);

    PageInfo findUsersUnselectedByPrivilege(UserOfPrivilegeQueryVo userOfPrivilegeQueryVo);

    void addUsers2Privilege(AddUsers2PrivilegeVo addUsers2PrivilegeVo);

    void deleteUsersOfPrivilege(String[] mappingIds);

    public List<Privilege> findPrivilegesByUser(String userId);

    PageInfo findPrivilegesByUser(QueryPrivilegesByUserVo queryPrivilegesByUserVo);

    PageInfo findPrivilegesUnselectedByUser(QueryPrivilegesByUserVo queryPrivilegesByUserVo);

    void addPrivileges2User(String[] privilegeIds, String userId, String[] operations);
}
