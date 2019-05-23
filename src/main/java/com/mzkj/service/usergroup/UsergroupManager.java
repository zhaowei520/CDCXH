package com.mzkj.service.usergroup;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.mzkj.bean.UsergroupBean;
import com.mzkj.vo.usergroup.PrivilegeOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.PrivilegeUnselected2UsergroupQueryVo;
import com.mzkj.vo.usergroup.UserOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UserUnselected2UsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupVo;

import java.util.List;

public interface UsergroupManager {
    public PageInfo list(UsergroupQueryVo usergroupQueryVo) throws Exception;

    public void save(UsergroupVo usergroupVo);

    public void update(UsergroupVo usergroupVo);

    public UsergroupBean findById(String id);

    public JSONArray findByParentId(String parentID);

    public void delete(List<String> ids);

    public PageInfo findUsersByUsergroup(UserOfUsergroupQueryVo userOfUsergroupQueryVo);

    public PageInfo findPrivilegesByUsergroup(PrivilegeOfUsergroupQueryVo privilegeByUsergroupQueryVo);

    public void addUser2Usergroup(String usergroupId, String[] userIds, String[] operations);

    public void addPrivilege2Usergroup(String usergroupId, String[] privileges, String[] operationParams);

    public void deleteUsersOfUsergroup(String[] mappingIds);

    void deletePrivilegesOfUsergroup(String[] mappingIds);

    PageInfo findUsersUnselected(UserUnselected2UsergroupQueryVo userUnselected2UsergroupVo);

    PageInfo findPrivilegesUnselected(PrivilegeUnselected2UsergroupQueryVo privilegesUnselected2UsergroupQueryVo);
}
