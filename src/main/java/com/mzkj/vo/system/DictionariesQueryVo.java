package com.mzkj.vo.system;

import com.mzkj.vo.BaseVo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 字典查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 * @version
 */
@ApiModel(value="Dictionaries查询对象",description="Dictionaries查询")
public class DictionariesQueryVo extends BaseVo{
private String dictionariesId;
        private String name;
        private String nameEn;
        private String bianma;
        private Integer orderBy;
        private String parentId;
        private String bz;
        private String tbsname;
        private String tbfield;
        private String yndel;
        private String tenantId;
        private List<DictionariesQueryVo> childTreeList;

    public List<DictionariesQueryVo> getChildTreeList() {
        return childTreeList;
    }

    public void setChildTreeList(List<DictionariesQueryVo> childTreeList) {
        this.childTreeList = childTreeList;
    }

    public String getDictionariesId(){
        return dictionariesId;
    }
    public void setDictionariesId (String dictionariesId){
        this.dictionariesId = dictionariesId;
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
        public Integer getOrderBy(){
        return orderBy;
    }
    public void setOrderBy (Integer orderBy){
        this.orderBy = orderBy;
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
        public String getTbsname(){
        return tbsname;
    }
    public void setTbsname (String tbsname){
        this.tbsname = tbsname;
    }
        public String getTbfield(){
        return tbfield;
    }
    public void setTbfield (String tbfield){
        this.tbfield = tbfield;
    }
        public String getYndel(){
        return yndel;
    }
    public void setYndel (String yndel){
        this.yndel = yndel;
    }
        public String getTenantId(){
        return tenantId;
    }
    public void setTenantId (String tenantId){
        this.tenantId = tenantId;
    }


}

