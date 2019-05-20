package com.mzkj.service.usergroup;

import com.alibaba.fastjson.JSONArray;
import com.mzkj.vo.system.UserVo;
import com.mzkj.vo.usergroup.PrivilegeOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UserOfUsergroupQueryVo;
import com.mzkj.vo.usergroup.UsergroupQueryVo;

import java.util.List;

public interface UsergroupManager {
    public List<UsergroupQueryVo> list(UsergroupQueryVo usergroupQueryVo) throws Exception;

    public void save(UsergroupQueryVo usergroupQueryVo);

    public void update(UsergroupQueryVo usergroupQueryVo);

    public UsergroupQueryVo findById(UsergroupQueryVo usergroupQueryVo);

    public JSONArray findByParentId(String parentID);

    public void delete(List<String> ids);

    public List<UserVo> findUsersByUsergroup(UserVo userVo);

    public List<PrivilegeOfUsergroupQueryVo> findPrivilegesByUsergroup(PrivilegeOfUsergroupQueryVo privilegeByUsergroupQueryVo);

    public void addUser2Usergroup(UserOfUsergroupQueryVo userByUsergroupQueryVo);

    public void addPrivilege2Usergroup(PrivilegeOfUsergroupQueryVo privilegeOfUsergroupQueryVo);
}
