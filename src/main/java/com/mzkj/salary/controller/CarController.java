package com.mzkj.salary.controller;

import com.mzkj.salary.service.CarManager;
import com.mzkj.salary.util.Jurisdiction;
import com.mzkj.salary.util.enums.HttpCode;
import com.mzkj.salary.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mzkj.salary.domain.Car;

import java.util.List;

/**
 * 汽车控制层
 *
 * @Author: zw
 * @Date: 2019/3/18 13:08
 * @Version: 1.0
 */

@RestController
@RequestMapping(value = "/car")
@Api(tags = "CarController", description = "汽车接口")
public class CarController {

    private static Logger logger = LogManager.getLogger(CarController.class);
    String menuUrl = "car/list.do"; //菜单地址(权限用 )

    @Autowired
    private CarManager carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    @ApiOperation(value = "查询汽车", notes = "查询汽车")
    public Result<List<Car>> getCars() {
        logger.info("------汽车查询");
        Result<List<Car>> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        try {
            List<Car> listCars = carService.findAll();
            result.setData(listCars);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            result.setStatus(HttpCode.ERROR.getCode());
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/pageCars", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询汽车", notes = "分页查询汽车")
    //@ApiImplicitParams({@ApiImplicitParam(name = "pageNo", value = "当前页", required = true, dataType = "Long"), @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, dataType = "Long")})
    public Result<List<Car>> pageCars(@RequestParam(name = "pageNo") int pageNo, @RequestParam(name = "pageSize") int pageSize) {
        logger.info("------汽车分页查询");
        Result<List<Car>> result = new Result<>();
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            result.setMsg("没有操作权限，请联系管理员");
            result.setStatus(HttpCode.UNAUTHORIZED.getCode());
            return result;
        }
        List<Car> listCars = carService.findByPage(pageNo, pageSize);
        result.setData(listCars);
        return result;
    }
}
