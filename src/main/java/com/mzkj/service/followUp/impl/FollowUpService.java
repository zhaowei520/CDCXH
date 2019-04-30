package com.mzkj.service.followUp.impl;

import com.mzkj.service.followUp.FollowUpManager;
import com.mzkj.service.process.CommerceManager;
import com.mzkj.service.process.GShangChangeManager;
import com.mzkj.service.process.TallyManager;
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



    @Override
    public Map<String, Integer> countAllProcessNumber() throws Exception {
        Map<String, Integer> allProcessNumber = new HashMap<>();
        //工商注册流程中的数量统计
        Integer commerceProcessNumber = commerceService.countProcessNumber();
        allProcessNumber.put("commerceNum",commerceProcessNumber);
        Integer tallyProcessNumber = tallyService.countProcessNumber();
        allProcessNumber.put("tallyNum",tallyProcessNumber);
        Integer gShangChangeProcessNumber = gShangChangeService.countProcessNumber();
        allProcessNumber.put("gShangChangeNum",gShangChangeProcessNumber);
        return allProcessNumber;
    }
}
