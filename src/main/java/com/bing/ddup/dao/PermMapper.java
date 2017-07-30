package com.bing.ddup.dao;

import com.bing.ddup.model.Perm;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface PermMapper {
    int deleteByPrimaryKey(Integer permId);

    int insert(Perm record);

    int insertSelective(Perm record);

    Perm selectByPrimaryKey(Integer permId);

    int updateByPrimaryKeySelective(Perm record);

    int updateByPrimaryKey(Perm record);

    public Set<Perm> findPermsByUid(@Param("uid") Integer uid);
}