package com.bing.ddup.service;

import com.bing.ddup.dao.UserMapper;
import com.bing.ddup.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class UserService {
	
	private UserMapper userMapper;
	
	public User login(User user) throws SQLException{
		user = userMapper.login(user);
		return user;
	}
	
	public User infoUser(User user){
		return userMapper.infoUser(user);
	}
	
	public List<User> listUser(User user){
		return userMapper.listUser(user);
	}
	
	public List<Map<String, Object>> list(Map<String, String> param){
		return userMapper.list(param);
	}
	public Integer count(Map<String, String> param){
		return userMapper.count(param);
	}
	
	public int saveUser(User user){
		return userMapper.insertUser(user);
	}
	
	public int updateUser(User user){
		return userMapper.updateUser(user);
	}
	
	public void deleteUser(int id){
		userMapper.deleteUser(id);
	}
	
	public void destroyUser(int id){
		userMapper.destroyUser(id);
	}

    public User findByUsername(String username){
        return new User();
    }
	
	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
}
