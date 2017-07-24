package com.bing.ddup.service;

import com.bing.ddup.dao.UserAuthMapper;
import com.bing.ddup.model.UserAuth;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;

@Component
public class UserAuthService {
	
	private UserAuthMapper userAuthMapper;
	
	public UserAuth login(String username, String password) throws SQLException{
		UserAuth user = userAuthMapper.login(username, password);
		return user;
	}

	public UserAuth findByUsername(String username) throws SQLException{
		return userAuthMapper.findByUsername(username);
	}
	
	@Resource
	public void setUserAuthMapper(UserAuthMapper userAuthMapper) {
		this.userAuthMapper = userAuthMapper;
	}
}
