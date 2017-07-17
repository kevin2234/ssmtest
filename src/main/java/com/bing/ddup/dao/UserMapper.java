package com.bing.ddup.dao;

import com.bing.ddup.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public User infoUser(User user);
    public List<User> listUser(User user);
    public int insertUser(User user);
    public int updateUser(User user);
    public void deleteUser(int id);
    public void destroyUser(int id);
    
    public User login(User user);
    public List<Map<String, Object>> list(Map<String, String> param);
    public int count(Map<String, String> param);
}
