package com.mzkj.vo.usergroup;

import com.mzkj.vo.BaseVo;

import io.swagger.annotations.ApiModel;

/**
 * @Description: 查询vo
 * @Author: zw
 * @Date: 2019/3/26 17:21
 * @Version: 1.0
 */
@ApiModel(value = "usergroup查询对象", description = "usergroup查询")
public class UsergroupQueryVo extends BaseVo {

    private String name;                    //名称
    private String parentId;                //父类ID


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
