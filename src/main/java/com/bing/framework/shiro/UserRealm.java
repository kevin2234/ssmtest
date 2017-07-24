package com.bing.framework.shiro;

import com.bing.ddup.model.UserAuth;
import com.bing.ddup.service.UserAuthService;
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
    // 用户对应的角色信息与权限信息都保存在数据库中，通过UserAuthService获取数据
//    private UserAuthService userAuthService = new UserAuthServiceImpl();
    private UserAuthService userAuthService;

    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo");
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        logger.info(username);
        // 根据用户名查询当前用户拥有的角色
//        Set<Role> roles = userAuthService.findRoles(username);
//        Set<String> roleNames = new HashSet<String>();
//        for (Role role : roles) {
//            roleNames.add(role.getRole());
//        }
//        // 将角色名称提供给info
//        authorizationInfo.setRoles(roleNames);

        Set<String> permissionNames = new HashSet<String>();
//        // 根据用户名查询当前用户权限
//        Set<Permission> permissions = userAuthService.findPermissions(username);
//        for (Permission permission : permissions) {
//            permissionNames.add(permission.getPermission());
//        }
        // 将权限名称提供给info
        authorizationInfo.setStringPermissions(permissionNames);

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
        UserAuth user = null;
        try {
            user = userAuthService.findByUsername(username);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new AuthenticationException();
        }
        logger.info(token);
        logger.info(username);
        logger.info(user);
        if (user == null) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException();
        }
//        if (user.getLocked() == 0) {
//            // 用户被管理员锁定抛出异常
//            throw new LockedAccountException();
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
//                user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getIdentifier(),
                user.getCertificate(), ByteSource.Util.bytes(user.getCertificate()), getName());
        return authenticationInfo;
    }

    @Resource
    public void setUserAuthService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }
}