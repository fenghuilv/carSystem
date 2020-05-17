package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    /**
     * 通过id购买车辆
     * 购买成功，Service返回1，否则0；
     * @return
     */
    @PostMapping("buyById")
    public JSONResult buyById(@RequestParam(value = "id", required = true, defaultValue = "") String id,
                              @RequestParam(value = "count", required = true, defaultValue = "") String count) {
        int vid=0;
        int vcount=0;
        try {
             vid=Integer.valueOf(id);
             vcount=Integer.valueOf(count);
            if(vid<0 || vcount<1){
                return JSONResult.errorMsg("id或数量不合法");
            }
        }catch (Exception e){
            return JSONResult.errorMsg("车辆ID不合法或数量不合法");
        }
        int result = carService.buyById(vid,vcount);
        if (result>0){
            return JSONResult.ok();
        }else{
            return JSONResult.errorMsg("车辆库存数量不足");
        }
    }

    /**
     * 通过车辆名称进行车辆模糊查找（CarName）
     * 变量 start为查询结果的开始行数
     * 变量 end为查询结果的结束行数
     * 最终返回开始到结束的行数（包含开始与结束2行的数据）
     * @return
     */
    @GetMapping("FilterCarName")
    public JSONResult filterByCarName(@RequestParam(value = "carname", required = false, defaultValue = "") String carname,
                                      @RequestParam(value = "start", required = false, defaultValue = "0") String  start,
                                      @RequestParam(value = "end", required = false, defaultValue = "10") String end) {
       int s=0;
       int e=1;
      try {
            s =  Integer.valueOf(start);
            e =  Integer.valueOf(end);
            if(s<0 || e<s){
                return JSONResult.errorMsg("显示的开始及结束数量不合法");
            }
        }catch (Exception exc){
            return JSONResult.errorMsg("车辆ID不合法或数量不合法!  "+exc.getMessage().toString());
        }
        List<Car> cars = carService.filterByCarName(carname,s,e-s+1);
        return JSONResult.ok(cars);
    }

}
