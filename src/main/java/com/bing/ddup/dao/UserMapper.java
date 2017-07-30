package com.bing.ddup.dao;

import com.bing.ddup.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    User login(@Param("username") String username, @Param("password") String password);

    User findByUsername(@Param("username") String username);
}