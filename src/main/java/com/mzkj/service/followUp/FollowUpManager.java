package com.mzkj.service.followUp;

import com.mzkj.vo.followUp.FollowUpQueryVo;

import java.util.Map;

/**
 * @Description: 作用
 * @Author: zw
 * @Date: 2019/4/23 15:31
 * @Version: 1.0
 */
public interface FollowUpManager {
    Map<String, Integer> countAllProcessNumber(FollowUpQueryVo followUpQueryVo) throws Exception;

    Map<String, Integer> countAllProcessNumberByDepartmentId(FollowUpQueryVo followUpQueryVo) throws Exception;
}
