package com.tuling.mall.gateway.predicate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义RoutePredicateFactory
 */
@Component
@Slf4j
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                log.info("调用CheckAuthRoutePredicateFactory" + config.getName());
                if(config.getName().equals("gsd")){
                    return true;
                }
                return false;
            }
        };
    }

    /**
     * 快捷配置
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("name");
    }

    /**
     * 需要定义一个内部类，该类用于封装application.yml中的配置
     */
    public static class Config {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
