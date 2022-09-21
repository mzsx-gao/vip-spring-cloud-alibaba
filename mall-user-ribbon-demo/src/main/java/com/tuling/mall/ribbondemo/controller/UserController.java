package com.tuling.mall.ribbondemo.controller;

import com.tuling.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R  findOrderByUserId(@PathVariable("id") Integer id) {
        // RestTemplate调用
        //String url = "http://localhost:8020/order/findOrderByUserId/"+id;
        //模拟ribbon实现  从注册中心获取service list  ----> service
//        String url = getUri("mall-order")+"/order/findOrderByUserId/"+id;

        // 添加@LoadBalanced
        String url = "http://mall-order/order/findOrderByUserId/"+id;
        R result = restTemplate.getForObject(url,R.class);

        return result;
    }
    
    @RequestMapping(value = "/findAccountByUserId/{id}")
    public R  findAccountByUserId(@PathVariable("id") Integer id) {
        
        String url = "http://mall-account/account/infoByUserId/"+id;
        R result = restTemplate.getForObject(url,R.class);
        
        return result;
    }


    @Autowired
    private DiscoveryClient discoveryClient;
    public String getUri(String serviceName) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        if (serviceInstances == null || serviceInstances.isEmpty()) {
            return null;
        }
        int serviceSize = serviceInstances.size();
        //轮询
        int indexServer = incrementAndGetModulo(serviceSize);
        return serviceInstances.get(indexServer).getUri().toString();
    }
    private AtomicInteger nextIndex = new AtomicInteger(0);
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextIndex.get();
            int next = (current + 1) % modulo;
            if (nextIndex.compareAndSet(current, next) && current < modulo){
                return current;
            }
        }
    }




}
