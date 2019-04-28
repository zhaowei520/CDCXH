package com.mzkj.service.activiti.ruprocdef.impl;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.RuprocdefBean;
import com.mzkj.service.system.RoleManager;
import com.mzkj.service.system.UserManager;
import com.mzkj.util.ConvertUtil;
import com.mzkj.util.Jurisdiction;
import com.mzkj.util.PageUtil;
import com.mzkj.vo.activiti.ruprocdef.RuprocdefQueryVo;
import com.mzkj.vo.activiti.ruprocdef.RuprocdefVo;
import com.mzkj.vo.system.RoleVo;
import com.mzkj.vo.system.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mzkj.service.activiti.ruprocdef.RuprocdefManager;
import com.mzkj.mapper.activiti.ruprocdef.RuprocdefMapper;

import java.util.List;

/**
 * 说明： 工作流历史信息
 * 创建人：CDCXH
 * 创建时间：2019-04-24
 */
@Service("ruprocdefService")
public class RuprocdefService implements RuprocdefManager {

    @Autowired
    private RuprocdefMapper ruprocdefMapper;

    @Autowired
    private RoleManager roleService;

    @Autowired
    private UserManager userService;

    /**
     * 新增
     *
     * @param ruprocdefVo
     * @throws Exception
     */
    @Override
    public RuprocdefVo save(RuprocdefVo ruprocdefVo) throws Exception {
        RuprocdefBean ruprocdefBean =
            ConvertUtil.objectCopyParams(ruprocdefVo, RuprocdefBean.class);
        ruprocdefMapper.save(ruprocdefBean);
        ruprocdefVo = ConvertUtil.objectCopyParams(ruprocdefBean, RuprocdefVo.class);
        return ruprocdefVo;
    }

    /**
     * 删除
     *
     * @param ruprocdefId
     * @throws Exception
     */
    @Override
    public void delete(String ruprocdefId) throws Exception {
        ruprocdefMapper.delete(ruprocdefId);
    }

    /**
     * 修改
     *
     * @param ruprocdefVo
     * @throws Exception
     */
    @Override
    public void edit(RuprocdefVo ruprocdefVo) throws Exception {
        RuprocdefBean ruprocdefBean =
            ConvertUtil.objectCopyParams(ruprocdefVo, RuprocdefBean.class);
        ruprocdefMapper.edit(ruprocdefBean);
    }

    /**
     * 列表
     *
     * @param ruprocdefQueryVo
     * @throws Exception
     */
    @Override
    public PageInfo<RuprocdefQueryVo> list(RuprocdefQueryVo ruprocdefQueryVo) throws Exception {
        //将vo转DO并将分页信息传到pageHelper
        RuprocdefBean ruprocdefBean =
            PageUtil.startPageAndObjectCopyParams(ruprocdefQueryVo, RuprocdefBean.class);
        //设置租户ID
        ruprocdefBean.setTenantId(Jurisdiction.getTenant());
        List<RuprocdefBean> ruprocdefPageBean = ruprocdefMapper.list(ruprocdefBean);
        //DO转VO
        List<RuprocdefQueryVo> ruprocdefQueryVoList =
            (List<RuprocdefQueryVo>) ConvertUtil.castListObjectToTargetList(ruprocdefPageBean, RuprocdefQueryVo.class);
        PageInfo<RuprocdefQueryVo> pageInfo = new PageInfo<>(ruprocdefQueryVoList);
        return pageInfo;
    }

    @Override
    public List<RuprocdefQueryVo> viewProcess(RuprocdefQueryVo ruprocdefQueryVo) throws Exception {
        ruprocdefQueryVo.setTenantId(Jurisdiction.getTenant());
        RuprocdefBean ruprocdefBean =
            ConvertUtil.objectCopyParams(ruprocdefQueryVo, RuprocdefBean.class);
        List<RuprocdefBean> ruprocdefBeanList = ruprocdefMapper.viewProcess(ruprocdefBean);
        List<RuprocdefQueryVo> ruprocdefQueryVoList =
            (List<RuprocdefQueryVo>) ConvertUtil.castListObjectToTargetList(ruprocdefBeanList, RuprocdefQueryVo.class);
        //对返回数据二次处理(计算用时，人名替换)
        ruprocdefQueryVoList = countDurationAndNameConvert(ruprocdefQueryVoList);

        return ruprocdefQueryVoList;
    }

    private List<RuprocdefQueryVo> countDurationAndNameConvert(List<RuprocdefQueryVo> ruprocdefQueryVoList) throws Exception {
        if (ruprocdefQueryVoList != null && ruprocdefQueryVoList.size() > 0) {
            for (RuprocdefQueryVo ruprocdefQueryVo : ruprocdefQueryVoList) {
                //计算用时
                if(null != ruprocdefQueryVo.getDuration()){
                    String newduration =  durationConvert(ruprocdefQueryVo.getDuration().toString());
                    ruprocdefQueryVo.setDurationCN(newduration);
                }
                //人名替换
                if(null != ruprocdefQueryVo.getAssignee()){
                    String newAssignee =  nameConvert(ruprocdefQueryVo.getAssignee());
                    ruprocdefQueryVo.setAssignee(newAssignee);
                }
            }
        }
        return ruprocdefQueryVoList;
    }

    private String durationConvert(String duration) {
        Long ztime = Long.parseLong(duration);
        Long tian = ztime / (1000*60*60*24);
        Long shi = (ztime % (1000*60*60*24))/(1000*60*60);
        Long fen = (ztime % (1000*60*60*24))%(1000*60*60)/(1000*60);
        Long miao = (ztime % (1000*60*60*24))%(1000*60*60)%(1000*60)/1000;
        return tian+"天"+shi+"时"+fen+"分"+miao+"秒";
    }

    private String nameConvert(String assignee) throws Exception {
        String name = null;
        UserVo userVo = userService.findByUsername(assignee);
        if (userVo == null) {
            RoleVo roleVo = roleService.getRoleByRnumber(assignee);
            if (roleVo == null) {
                return name;
            }
            name = roleVo.getRoleName();
        } else {
            name = userVo.getName();
        }
        return name;
    }

}

