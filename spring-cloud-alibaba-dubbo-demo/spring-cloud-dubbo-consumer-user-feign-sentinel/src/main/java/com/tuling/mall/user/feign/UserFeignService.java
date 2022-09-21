package com.tuling.mall.user.feign;

import com.tuling.mall.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author gsd
 */
@FeignClient(value = "spring-cloud-dubbo-provider-user-feign",path = "/user")
public interface UserFeignService {

    @RequestMapping("/list")
    public List<User> list();

    @RequestMapping("/getById/{id}")
    public User getById(@PathVariable("id") Integer id);
}
