package com.mzkj.vo.system;

import com.mzkj.vo.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 用户管理查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@ApiModel(value = "User查询对象", description = "User查询")
public class UserQueryVo extends BaseVo {
    private String userId;
    private String username;
    private String name;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

