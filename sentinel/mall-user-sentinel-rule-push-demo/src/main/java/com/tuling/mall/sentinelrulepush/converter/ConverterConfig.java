package com.tuling.mall.sentinelrulepush.converter;

import com.alibaba.cloud.sentinel.datasource.converter.JsonConverter;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author gsd
 */
//@Configuration
public class ConverterConfig {

    @Bean("sentinel-json-param-flow-converter2")
    @Primary
    public JsonConverter jsonParamFlowConverter() {
        return new FlowParamJsonConverter(new ObjectMapper(), ParamFlowRule.class);
    }
}
