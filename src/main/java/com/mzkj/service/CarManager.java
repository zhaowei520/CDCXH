package com.mzkj.service;

import com.github.pagehelper.Page;
import com.mzkj.domain.Car;

import java.util.List;

/**
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */
public interface CarManager {

    public List<Car> findAll();

    public Page<Car> findByPage(int pageNo, int pageSize);
}
