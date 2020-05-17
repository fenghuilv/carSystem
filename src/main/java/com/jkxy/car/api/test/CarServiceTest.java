package com.jkxy.car.api.test;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    private  CarService carService;

    @Test
    public void InsertTest(){
         Car  car = new Car();
         car.setCarName("testsdss");
         car.setCarType("轿车");
         car.setCarSeries("B级");
         car.setPrice("12.5W--23.3W");
         carService.insertCar(car);
    }

    @Test
    public void deleteTest(){
        carService.deleteById(12);
    }

    @Test
    public void findAllTest(){
        List<Car> listcar = new ArrayList<>();
        for(Car c:listcar){
            System.out.println(c.toString());
        }

    }


}