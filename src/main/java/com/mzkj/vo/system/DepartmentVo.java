package com.mzkj.vo.system;

/**
* 说明： 部门管理新增/修改vo
* 创建人：CDCXH
* 创建时间：2019-04-18
* @version
*/
public class DepartmentVo{
private String departmentId;
        private String name;
        private String nameEn;
        private String bianma;
        private String parentId;
        private String bz;
        private String headman;
        private String tel;
        private String functions;
        private String address;
        private Integer isStatistics;
        private String tenantId;

    public String getDepartmentId(){
        return departmentId;
    }
    public void setDepartmentId (String departmentId){
        this.departmentId = departmentId;
    }

    public String getName(){
        return name;
    }
    public void setName (String name){
        this.name = name;
    }
    public String getNameEn(){
        return nameEn;
    }
    public void setNameEn (String nameEn){
        this.nameEn = nameEn;
    }
    public String getBianma(){
        return bianma;
    }
    public void setBianma (String bianma){
        this.bianma = bianma;
    }
    public String getParentId(){
        return parentId;
    }
    public void setParentId (String parentId){
        this.parentId = parentId;
    }
    public String getBz(){
        return bz;
    }
    public void setBz (String bz){
        this.bz = bz;
    }
    public String getHeadman(){
        return headman;
    }
    public void setHeadman (String headman){
        this.headman = headman;
    }
    public String getTel(){
        return tel;
    }
    public void setTel (String tel){
        this.tel = tel;
    }
    public String getFunctions(){
        return functions;
    }
    public void setFunctions (String functions){
        this.functions = functions;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress (String address){
        this.address = address;
    }
    public Integer getIsStatistics(){
        return isStatistics;
    }
    public void setIsStatistics (Integer isStatistics){
        this.isStatistics = isStatistics;
    }
    public String getTenantId(){
        return tenantId;
    }
    public void setTenantId (String tenantId){
        this.tenantId = tenantId;
    }

}

