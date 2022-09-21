package com.tuling.mall.sentinelrulepush.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.tuling.mall.sentinelrulepush.common.GlobalExceptionUtil;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced    //拦截器
    @SentinelRestTemplate(
        fallbackClass = GlobalExceptionUtil.class,fallback = "fallback",
        blockHandlerClass = GlobalExceptionUtil.class,blockHandler = "handleException"
    )
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
