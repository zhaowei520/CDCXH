package com.mzkj.vo.usergroup;

/**
 * @Description: 查询vo
 * @Author: zw
 * @Date: 2019/3/26 17:21
 * @Version: 1.0
 */
public class UsergroupVo {

    private String usergroupId;    //主键
    private String name;                    //名称
    private String parentId;                //父类ID
    private String type;
    private String subtype;
    private String code;
    private String tenantId;
    private String createUser;
    private String createDate;
    private String updateUser;
    private String updateDate;
    private String deleted;

    public String getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(String usergroupId) {
        this.usergroupId = usergroupId;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
