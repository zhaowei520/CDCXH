package com.mzkj.facade;

import com.mzkj.facade.vo.AddUsers2PrivilegeVo;
import com.mzkj.facade.vo.InsertPrivilegeVo;
import com.mzkj.facade.vo.Result;

public interface PrivilegeFacade {

    public Result insert(InsertPrivilegeVo insertPrivilegeVo) throws Exception;

    public Result addUsers2Privilege(AddUsers2PrivilegeVo addUsers2PrivilegeVo) throws Exception;

}
