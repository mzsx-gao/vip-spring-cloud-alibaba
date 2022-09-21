package com.tuling.mall.sentinelrulepush.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tuling.common.utils.PageUtils;
import com.tuling.common.utils.R;
import com.tuling.mall.sentinelrulepush.common.CommonBlockHandler;
import com.tuling.mall.sentinelrulepush.common.CommonFallback;
import com.tuling.mall.sentinelrulepush.common.ExceptionUtil;
import com.tuling.mall.sentinelrulepush.entity.UserEntity;
import com.tuling.mall.sentinelrulepush.feign.OrderFeignService;
import com.tuling.mall.sentinelrulepush.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    OrderFeignService orderFeignService;

    AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * sentinel异常处理配置:
     * 如果配置了@SentinelResource，规范做法是一定要配置blockHandler和fallback，否则sentinel就无法处理异常
     */
//    @SentinelResource(value = "userinfo")
    //这种情况下BlockException也会被fallback处理
//    @SentinelResource(value = "userinfo",fallback = "fallback")
//    @SentinelResource(value = "userinfo",
//        blockHandler = "handleException2",
//        fallback = "fallback"
//    )
    @SentinelResource(value = "userinfo",
        blockHandlerClass = CommonBlockHandler.class, blockHandler = "handleException2",//处理BlockException异常
        fallbackClass = CommonFallback.class, fallback = "fallback" //处理业务异常
    )
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        UserEntity user = userService.getById(id);
        if (id == 4) {
            throw new IllegalArgumentException("异常参数");
        }
        return R.ok().put("user", user);
    }


    @RequestMapping(value = "/findOrderByUserId/{id}")
    @SentinelResource(value = "findOrderByUserId",
        blockHandlerClass = CommonBlockHandler.class, blockHandler = "handleException2")
    public R findOrderByUserId(@PathVariable("id") Integer id) {
//        try {
//            // 模拟测试并发线程数限流
//            Thread.sleep(900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        R result = orderFeignService.findOrderByUserId(id);
        return result;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @SentinelResource(value = "userlist",
        blockHandlerClass = CommonBlockHandler.class, blockHandler = "handleException",
        fallback = "fallback")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);
        // int i=1/0;
        return R.ok().put("page", page);
    }

    @RequestMapping("/test2")
    @SentinelResource(value = "test2",
        fallbackClass = ExceptionUtil.class, fallback = "fallback")//处理业务异常
    public String test2() {
        atomicInteger.getAndIncrement();
        if (atomicInteger.get() % 2 == 0) {
            //模拟异常和异常比率
            int i = 1 / 0;
        }
        return "========test2()";
    }

    public R handleException(@RequestParam Map<String, Object> params, BlockException exception) {
        return R.error(-1, "===被限流降级啦===");
    }

    public R handleException2(@PathVariable("id") Integer id, BlockException exception) {
        return R.error(-1, "===被限流降级啦===");
    }

    public R fallback(@PathVariable("id") Integer id, Throwable e) {
        return R.error(-1, "===被熔断降级啦===" + e.getMessage());
    }
}