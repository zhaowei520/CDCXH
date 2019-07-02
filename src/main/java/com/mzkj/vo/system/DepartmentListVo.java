package com.mzkj.vo.system;

import com.mzkj.vo.BaseVo;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;

/**
 * 说明： 部门管理查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-18
 */
@ApiModel(value = "Department层级结构对象", description = "Department层级结构查询")
public class DepartmentListVo extends BaseVo {
    //id
    private String key;
    //name
    private String lable;
    //子部门
    private List<DepartmentListVo> nodes = new ArrayList();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public List<DepartmentListVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<DepartmentListVo> nodes) {
        this.nodes = nodes;
    }
}

