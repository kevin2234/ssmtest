package com.bing.ddup.web;

import com.bing.ddup.model.User;
import com.bing.ddup.service.UserService;
import com.bing.framework.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();
	private UserService userService;

    @RequestMapping("info")
    public String info(){
        return "user/user-list";
    }

    @RequestMapping("list")
    public String list(){
        logger.info("user-list1111111111");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        System.out.println(user.getUname());
        System.out.println(user.getUsername());

        boolean ishasRole = subject.hasRole("admin");
        logger.info("单个角色判断"+ishasRole);

        // 使用check方法进行授权，如果授权不通过会抛出异常
//        subject.checkRole("employee");

        // 基于资源的授权
        // isPermitted传入权限标识符
        boolean isPermitted = subject.isPermitted("user:create:1");
        System.out.println("单个权限判断" + isPermitted);

        boolean isPermittedAll = subject.isPermittedAll("user:create:1", "user:delete");
        System.out.println("多个权限判断" + isPermittedAll);

        // 使用check方法进行授权，如果授权不通过会抛出异常
//        subject.checkPermission("items:create:1");

        return "user/user-list";
    }

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
