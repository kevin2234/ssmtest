package com.bing.ddup.dao;

import com.bing.ddup.model.UserBase;

public interface UserBaseMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UserBase record);

    int insertSelective(UserBase record);

    UserBase selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserBase record);

    int updateByPrimaryKey(UserBase record);
}