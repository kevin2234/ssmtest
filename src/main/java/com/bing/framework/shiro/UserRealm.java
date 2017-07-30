package com.bing.framework.shiro;

import com.bing.ddup.model.Perm;
import com.bing.ddup.model.Role;
import com.bing.ddup.model.User;
import com.bing.ddup.model.dto.CurUser;
import com.bing.ddup.service.AuthService;
import com.bing.ddup.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();
    // 用户对应的角色信息与权限信息都保存在数据库中，通过UserService获取数据
    private UserService userService;
    private AuthService authService;

    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo");
        CurUser curUser = (CurUser) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        logger.info(curUser.getUsername());

        try {
            // 根据用户名查询当前用户拥有的角色
            Set<Role> roles = authService.findRolesByUid(curUser.getUid());

            Set<String> roleNames = new HashSet<String>();
            for (Role role : roles) {
                roleNames.add(role.getRoleItem());
            }
    //         将角色名称提供给info
            authorizationInfo.setRoles(roleNames);

            Set<String> permissionNames = new HashSet<String>();
            // 根据用户名查询当前用户权限
            Set<Perm> permissions = authService.findPermsByUid(curUser.getUid());
            for (Perm permission : permissions) {
                permissionNames.add(permission.getPermission());
            }
            // 将权限名称提供给info
            authorizationInfo.setStringPermissions(permissionNames);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return authorizationInfo;
    }

    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo");
        String username = (String) token.getPrincipal();
        //todo
        User user = null;
        try {
            user = userService.findByUsername(username);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new AuthenticationException();
        }
        logger.info(token);
        logger.info(username);
        if (user == null) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException();
        }
        logger.info(user.getUsername());
        logger.info(user.getPassword());
//        if (user.getLocked() == 0) {
//            // 用户被管理员锁定抛出异常
//            throw new LockedAccountException();
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
//                user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
//                user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());

        CurUser curUser = new CurUser(user.getUid(), user.getUname(), user.getUsername(), user.getUserType(), user.getStatus());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(curUser,
                user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
        return authenticationInfo;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Resource
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}