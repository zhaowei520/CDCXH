package com.mzkj.salary.mapper;

import com.github.pagehelper.Page;
import com.mzkj.salary.domain.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2019/3/18.
 */
@Mapper
public interface  CarMapper {

    public List<Car> findAll();

    public Page<Car> findByPage();
}
