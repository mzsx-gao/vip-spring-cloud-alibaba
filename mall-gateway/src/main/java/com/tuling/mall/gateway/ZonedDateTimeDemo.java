package com.tuling.mall.gateway;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author gsd
 */
public class ZonedDateTimeDemo {

    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();//默认时区
        // 用指定时区获取当前时间
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime2);
    }
}
