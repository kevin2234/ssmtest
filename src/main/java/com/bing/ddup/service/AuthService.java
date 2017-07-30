package com.bing.ddup.service;

import com.bing.ddup.dao.PermMapper;
import com.bing.ddup.dao.RoleMapper;
import com.bing.ddup.dao.UserMapper;
import com.bing.ddup.model.Perm;
import com.bing.ddup.model.Role;
import com.bing.ddup.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Set;

@Component
public class AuthService {
	
	private UserMapper userMapper;
	private RoleMapper roleMapper;
	private PermMapper permMapper;
	
	public User login(String username, String password) throws SQLException{
		User user = userMapper.login(username, password);
		return user;
	}

	public User findByUsername(String username) throws SQLException{
		return userMapper.findByUsername(username);
	}

    public Set<Role> findRolesByUid(Integer uid) throws SQLException{
        return roleMapper.findRolesByUid(uid);
    }

    public Set<Perm> findPermsByUid(Integer uid) throws SQLException{
        return permMapper.findPermsByUid(uid);
    }
	
	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

    @Resource
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Resource
    public void setPermMapper(PermMapper permMapper) {
        this.permMapper = permMapper;
    }
}
