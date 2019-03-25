package com.mzkj.util;

import org.springframework.util.StringUtils;

import java.math.BigInteger;

/**
 * @Description: 权限计算帮助类
 * @Author: zw
 * @Date: 2019/3/21 9:50
 * @Version: 1.0
 */
public class RightsHelper {
    /**
     * 测试是否具有指定编码的权限
     * @param sum
     * @param targetRights
     * @return
     */
    public static boolean testRights(String sum,String targetRights){
        if(StringUtils.isEmpty(sum))
            return false;
        return testRights(new BigInteger(sum),targetRights);
    }

    /**
     * 测试是否具有指定编码的权限
     * @param sum
     * @param targetRights
     * @return
     */
    public static boolean testRights(BigInteger sum,String targetRights){
        return testRights(sum,Integer.parseInt(targetRights));
    }

    /**
     * 测试是否具有指定编码的权限
     * @param sum
     * @param targetRights
     * @return
     */
    public static boolean testRights(BigInteger sum,int targetRights){
        return sum.testBit(targetRights);
    }
}
