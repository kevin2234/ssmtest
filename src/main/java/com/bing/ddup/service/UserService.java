package com.bing.ddup.service;

import com.bing.ddup.dao.UserMapper;
import com.bing.ddup.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;

@Component
public class UserService {
	
	private UserMapper userMapper;

	public int deleteByPrimaryKey(Integer uid) {
		return userMapper.deleteByPrimaryKey(uid);
	}

    public int insert(User record) {
		return userMapper.insert(record);
	}

    public int insertSelective(User record){
		return userMapper.insertSelective(record);
	}

    public User selectByPrimaryKey(Integer uid){
		return userMapper.selectByPrimaryKey(uid);
	}

    public int updateByPrimaryKeySelective(User record){
		return userMapper.updateByPrimaryKeySelective(record);
	}

    public int updateByPrimaryKey(User record){
		return userMapper.updateByPrimaryKey(record);
	}

	
	public User login(String username, String password) throws SQLException{
		User user = userMapper.login(username, password);
		return user;
	}

	public User findByUsername(String username) throws SQLException{
		return userMapper.findByUsername(username);
	}
	
	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
