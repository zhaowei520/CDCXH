package com.mzkj.vo.system;

import com.mzkj.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 用户管理查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@ApiModel(value="User查询对象",description="User查询")
public class UserQueryVo extends BaseVo{
private String userId;
        private String username;
        private String password;
        private String name;
        private String rights;
        private String roleId;
        private String lastLogin;
        private String ip;
        private String status;
        private String bz;
        private String skin;
        private String email;
        private String number;
        private String phone;
        private String roleIds;
        private String tenantId;
        private Integer isTenantAdmin;
        private Integer userType;
        private String openId;

    public String getUserId(){
        return userId;
    }
    public void setUserId (String userId){
        this.userId = userId;
    }

        public String getUsername(){
        return username;
    }
    public void setUsername (String username){
        this.username = username;
    }
        public String getPassword(){
        return password;
    }
    public void setPassword (String password){
        this.password = password;
    }
        public String getName(){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }
        public String getRights(){
        return rights;
    }
    public void setRights (String rights){
        this.rights = rights;
    }
        public String getRoleId(){
        return roleId;
    }
    public void setRoleId (String roleId){
        this.roleId = roleId;
    }
        public String getLastLogin(){
        return lastLogin;
    }
    public void setLastLogin (String lastLogin){
        this.lastLogin = lastLogin;
    }
        public String getIp(){
        return ip;
    }
    public void setIp (String ip){
        this.ip = ip;
    }
        public String getStatus(){
        return status;
    }
    public void setStatus (String status){
        this.status = status;
    }
        public String getBz(){
        return bz;
    }
    public void setBz (String bz){
        this.bz = bz;
    }
        public String getSkin(){
        return skin;
    }
    public void setSkin (String skin){
        this.skin = skin;
    }
        public String getEmail(){
        return email;
    }
    public void setEmail (String email){
        this.email = email;
    }
        public String getNumber(){
        return number;
    }
    public void setNumber (String number){
        this.number = number;
    }
        public String getPhone(){
        return phone;
    }
    public void setPhone (String phone){
        this.phone = phone;
    }
        public String getRoleIds(){
        return roleIds;
    }
    public void setRoleIds (String roleIds){
        this.roleIds = roleIds;
    }
        public String getTenantId(){
        return tenantId;
    }
    public void setTenantId (String tenantId){
        this.tenantId = tenantId;
    }
        public Integer getIsTenantAdmin(){
        return isTenantAdmin;
    }
    public void setIsTenantAdmin (Integer isTenantAdmin){
        this.isTenantAdmin = isTenantAdmin;
    }
        public Integer getUserType(){
        return userType;
    }
    public void setUserType (Integer userType){
        this.userType = userType;
    }
        public String getOpenId(){
        return openId;
    }
    public void setOpenId (String openId){
        this.openId = openId;
    }


}

