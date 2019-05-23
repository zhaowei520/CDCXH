package com.mzkj.vo.privilege;

import com.mzkj.vo.BaseVo;

import io.swagger.annotations.ApiModel;

/**
 * @Description: 查询vo
 * @Author: zw
 * @Date: 2019/3/26 17:21
 * @Version: 1.0
 */
@ApiModel(value = "usergroup查询对象", description = "usergroup查询")
public class PrivilegeQueryVo extends BaseVo {

    private String name;                    //名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
