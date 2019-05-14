package com.mzkj.service.usergroup;

import com.mzkj.vo.usergroup.UsergroupQueryVo;

import java.util.List;

public interface UsergroupManager {
    public List<UsergroupQueryVo> list(UsergroupQueryVo usergroupQueryVo) throws Exception;

    public void save(UsergroupQueryVo usergroupQueryVo);

    public void update(UsergroupQueryVo usergroupQueryVo);
}
