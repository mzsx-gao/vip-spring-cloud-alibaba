package com.tuling.mall.rule;

import com.netflix.loadbalancer.IRule;
import com.tuling.mall.ribbondemo.rule.NacosRandomWithWeightRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new NacosRandomWithWeightRule();
    }
}
