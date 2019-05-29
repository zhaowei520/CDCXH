package com.mzkj.facade.vo;

/**
 * @Description: 查询vo
 * @Author: zw
 * @Date: 2019/3/26 17:21
 * @Version: 1.0
 */
public class InsertPrivilegeVo {

    private String name;                    //名称
    private String type;
    private String subType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
}
