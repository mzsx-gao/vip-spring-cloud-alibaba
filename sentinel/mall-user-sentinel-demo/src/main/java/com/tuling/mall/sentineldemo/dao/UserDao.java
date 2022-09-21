package com.tuling.mall.sentineldemo.dao;

import com.tuling.mall.sentineldemo.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author gsd
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
