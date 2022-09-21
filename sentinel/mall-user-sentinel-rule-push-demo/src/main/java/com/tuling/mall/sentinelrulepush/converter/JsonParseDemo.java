package com.tuling.mall.sentinelrulepush.converter;

import com.alibaba.cloud.sentinel.datasource.converter.JsonConverter;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


import java.util.Iterator;
import java.util.List;

/**
 * @author gsd
 */

public class JsonParseDemo {

    public static void main(String[] args) {
        // 如果配置中心直接存的是ParamFlowRuleEntity类型，会导致解析规则出错
        //
        Class runclass = ParamFlowRule.class;
        String dataId = "mall-user-sentinel-rule-push-demo-param-flow-rules";
//        dataId = "mall-user-sentinel-rule-push-demo-flow-rules";
//        runclass = FlowRule.class;
        try {
            ConfigService configService = ConfigFactory.createConfigService("localhost:8848");
            String conf = configService.getConfig(dataId,"SENTINEL_GROUP",3000);
            System.out.println("配置:"+conf);

            List list = JSON.parseArray(conf, runclass);
            System.err.println("直接转对象列表:"+list.get(0).toString());

          JSONArray jsonArray = JSON.parseArray(conf);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.getJSONObject(i).get("rule");

                System.out.println(i+":"+JSON.toJavaObject(jsonObject, runclass));
            }

        } catch (NacosException e) {
            e.printStackTrace();
    }


}

}
