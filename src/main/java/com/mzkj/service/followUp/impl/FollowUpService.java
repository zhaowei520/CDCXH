package com.mzkj.service.followUp.impl;

import com.mzkj.bean.StaffBean;
import com.mzkj.service.followUp.FollowUpManager;
import com.mzkj.service.process.CommerceManager;
import com.mzkj.service.process.GShangChangeManager;
import com.mzkj.service.process.TallyManager;
import com.mzkj.service.system.StaffManager;
import com.mzkj.vo.followUp.FollowUpQueryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 作用
 * @Author: zw
 * @Date: 2019/4/23 15:32
 * @Version: 1.0
 */
@Service("followUpService")
public class FollowUpService implements FollowUpManager {

    @Autowired
    private CommerceManager commerceService;

    @Autowired
    private TallyManager tallyService;

    @Autowired
    private GShangChangeManager gShangChangeService;

    @Autowired
    private StaffManager staffService;



    @Override
    public Map<String, Integer> countAllProcessNumber(FollowUpQueryVo followUpQueryVo) throws Exception {
        Map<String, Integer> allProcessNumber = new HashMap<>();
        //工商注册流程中的数量统计
        Integer commerceProcessNumber = commerceService.countProcessNumber(followUpQueryVo.getStaffName());
        allProcessNumber.put("commerceNum",commerceProcessNumber);
        Integer tallyProcessNumber = tallyService.countProcessNumber(followUpQueryVo.getStaffName());
        allProcessNumber.put("tallyNum",tallyProcessNumber);
        Integer gShangChangeProcessNumber = gShangChangeService.countProcessNumber(followUpQueryVo.getStaffName());
        allProcessNumber.put("gShangChangeNum",gShangChangeProcessNumber);
        return allProcessNumber;
    }

    @Override
    public Map<String, Integer> countAllProcessNumberByDepartmentId(FollowUpQueryVo followUpQueryVo) throws Exception {
        Map<String, Integer> allProcessNumber = new HashMap<>();
        //工商注册流程中的数量统计
        Integer commerceProcessNumber = commerceService.countProcessNumberByDepartment(followUpQueryVo);
        allProcessNumber.put("commerceNum",commerceProcessNumber);
        Integer tallyProcessNumber = tallyService.countProcessNumberByDepartment(followUpQueryVo);
        allProcessNumber.put("tallyNum",tallyProcessNumber);
        Integer gShangChangeProcessNumber = gShangChangeService.countProcessNumberByDepartment(followUpQueryVo);
        allProcessNumber.put("gShangChangeNum",gShangChangeProcessNumber);
        return allProcessNumber;
    }
}
