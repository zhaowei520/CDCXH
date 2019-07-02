package com.mzkj.convert;

import com.github.pagehelper.PageHelper;
import com.mzkj.bean.CommerceBean;
import com.mzkj.bean.DepartmentBean;
import com.mzkj.bean.GShangChangeBean;
import com.mzkj.bean.TallyBean;
import com.mzkj.bean.UserBean;
import com.mzkj.vo.followUp.FollowUpQueryVo;
import com.mzkj.vo.system.DepartmentListVo;
import com.mzkj.vo.system.UserQueryVo;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 部门转换
 * @Author: zw
 * @Date: 2019/4/23 15:45
 * @Version: 1.0
 */
public class DepartmentConvert {


    public static DepartmentListVo departmentBeanToDepartmentVo(DepartmentBean newDepartmentBean) {
        DepartmentListVo departmentListVo = new DepartmentListVo();
        if(newDepartmentBean != null){
            departmentListVo.setKey(newDepartmentBean.getDepartmentId());
            departmentListVo.setLable(newDepartmentBean.getName());
            if(newDepartmentBean.getDepartmentBeans() != null && newDepartmentBean.getDepartmentBeans().size() > 0){
                for(DepartmentBean oneBean : newDepartmentBean.getDepartmentBeans()){
                    DepartmentListVo oneVo = new DepartmentListVo();
                    oneVo.setKey(oneBean.getDepartmentId());
                    oneVo.setLable(oneBean.getName());
                    departmentListVo.getNodes().add(oneVo);
                }
            }
        }
        return departmentListVo;
    }

    public static DepartmentBean departmentListVoToDepartmentBean(DepartmentListVo departmentListVo) {
        DepartmentBean departmentBean = new DepartmentBean();
        departmentBean.setDepartmentId(departmentListVo.getKey());

        return departmentBean;
    }

    public static List<DepartmentListVo> departmentBeanListToDepartmentVoList(List<DepartmentBean> departmentList) {
        List<DepartmentListVo> voList = new ArrayList();
        if(departmentList != null && departmentList.size() > 0){
            for(DepartmentBean oneBean : departmentList){
                DepartmentListVo oneVo = new DepartmentListVo();
                oneVo.setKey(oneBean.getDepartmentId());
                oneVo.setLable(oneBean.getName());
                voList.add(oneVo);
            }
        }
        return voList;
    }
}
