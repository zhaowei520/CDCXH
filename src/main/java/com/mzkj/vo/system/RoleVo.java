package com.mzkj.vo.system;

/**
* 说明： 系统角色新增/修改vo
* 创建人：CDCXH
* 创建时间：2019-04-24
* @version
*/
public class RoleVo{
private String roleId;
        private String roleName;
        private String rights;
        private String parentId;
        private String addQx;
        private String delQx;
        private String editQx;
        private String chaQx;
        private String rnumber;
        private String tenantId;

    public String getRoleId(){
        return roleId;
    }
    public void setRoleId (String roleId){
        this.roleId = roleId;
    }

    public String getRoleName(){
        return roleName;
    }
    public void setRoleName (String roleName){
        this.roleName = roleName;
    }
    public String getRights(){
        return rights;
    }
    public void setRights (String rights){
        this.rights = rights;
    }
    public String getParentId(){
        return parentId;
    }
    public void setParentId (String parentId){
        this.parentId = parentId;
    }
    public String getAddQx(){
        return addQx;
    }
    public void setAddQx (String addQx){
        this.addQx = addQx;
    }
    public String getDelQx(){
        return delQx;
    }
    public void setDelQx (String delQx){
        this.delQx = delQx;
    }
    public String getEditQx(){
        return editQx;
    }
    public void setEditQx (String editQx){
        this.editQx = editQx;
    }
    public String getChaQx(){
        return chaQx;
    }
    public void setChaQx (String chaQx){
        this.chaQx = chaQx;
    }
    public String getRnumber(){
        return rnumber;
    }
    public void setRnumber (String rnumber){
        this.rnumber = rnumber;
    }
    public String getTenantId(){
        return tenantId;
    }
    public void setTenantId (String tenantId){
        this.tenantId = tenantId;
    }

}

