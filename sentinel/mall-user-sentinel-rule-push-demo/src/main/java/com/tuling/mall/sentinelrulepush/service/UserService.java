package com.tuling.mall.sentinelrulepush.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuling.common.utils.PageUtils;
import com.tuling.mall.sentinelrulepush.entity.UserEntity;

import java.util.Map;

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public UserEntity getUser(int id);
}

