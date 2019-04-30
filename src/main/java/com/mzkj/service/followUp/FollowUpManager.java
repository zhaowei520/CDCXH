package com.mzkj.service.followUp;

import java.util.Map;

/**
 * @Description: 作用
 * @Author: zw
 * @Date: 2019/4/23 15:31
 * @Version: 1.0
 */
public interface FollowUpManager {
    Map<String, Integer> countAllProcessNumber() throws Exception;
}
