package com.bing.ddup.dao;

import com.bing.ddup.model.UserAuth;
import org.apache.ibatis.annotations.Param;

public interface UserAuthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuth record);

    int insertSelective(UserAuth record);

    UserAuth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAuth record);

    int updateByPrimaryKey(UserAuth record);

    UserAuth login(@Param("username") String username, @Param("password") String password);

    UserAuth findByUsername(@Param("username") String username);
}