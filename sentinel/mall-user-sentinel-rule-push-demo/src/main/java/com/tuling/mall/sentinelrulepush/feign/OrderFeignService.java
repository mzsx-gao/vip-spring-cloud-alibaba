package com.tuling.mall.sentinelrulepush.feign;

import com.tuling.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author gsd
 */

@FeignClient(value = "mall-order",path = "/order",fallback = FallbackOrderFeignService.class)
public interface OrderFeignService {

    @RequestMapping("/findOrderByUserId/{userId}")
    public R findOrderByUserId(@PathVariable("userId") Integer userId);


}
