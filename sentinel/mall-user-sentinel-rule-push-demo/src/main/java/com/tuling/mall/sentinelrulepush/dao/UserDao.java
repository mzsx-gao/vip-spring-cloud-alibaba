package com.tuling.mall.sentinelrulepush.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuling.mall.sentinelrulepush.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
