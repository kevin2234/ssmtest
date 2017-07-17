package com.bing.framework.base;

/**
 * DAO层基类(用于一些通用的方法)
 * Created by lian on 17/7/12.
 */
public interface BaseDAO<T> {
    /**
     * 通过ID查询单本图书
     *
     * @param id
     * @return
     */
    T queryById(long id);
}
