package com.tuling.mall.sentinelrulepush.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuling.common.utils.PageUtils;
import com.tuling.common.utils.Query;
import com.tuling.mall.sentinelrulepush.dao.UserDao;
import com.tuling.mall.sentinelrulepush.entity.UserEntity;
import com.tuling.mall.sentinelrulepush.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @SentinelResource(value = "getUser")  //aop
    public UserEntity getUser(int id){
        UserEntity user = baseMapper.selectById(id);
        return user;
    }
    
    public UserEntity handleException(int id, BlockException ex) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("===被限流降级啦===");
        return userEntity;
    }

}