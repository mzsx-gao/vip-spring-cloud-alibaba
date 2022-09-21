package com.tuling.mall.feigndemo.controller;

import com.tuling.common.utils.R;

import com.tuling.mall.feigndemo.feign.OrderFeignService;
import com.tuling.mall.feigndemo.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    OrderFeignService orderFeignService;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R  findOrderByUserId(@PathVariable("id") Integer id) {
        //feign调用
        R result = orderFeignService.findOrderByUserId(id);
        return result;
    }

//    @RequestMapping(value = "/save")
//    public R save(@RequestBody OrderVo order){
//        return orderFeignService.save(order);
//    }

}