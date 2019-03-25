package com.mzkj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mzkj.domain.Car;
import com.mzkj.mapper.CarMapper;
import com.mzkj.service.CarManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */
@Service
public class CarService implements CarManager {

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> findAll() {
        return carMapper.findAll();
    }

    @Override
    public Page<Car> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return carMapper.findByPage();
    }
}
