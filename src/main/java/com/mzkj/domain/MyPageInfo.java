package com.mzkj.domain;

import com.github.pagehelper.PageInfo;
import com.mzkj.bean.CommerceBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: PageInfo的扩展
 * @Author: zw
 * @Date: 2019/4/30 15:55
 * @Version: 1.0
 */
public class MyPageInfo<K,V,T> extends PageInfo {

    private Map<K,V> map = new HashMap<>();

    public MyPageInfo(List<T> commercePageBean) {
        super(commercePageBean, 8);
    }

    public Map<K, V> getMap() {
        return map;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }
}
