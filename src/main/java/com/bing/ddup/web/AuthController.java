package com.bing.ddup.web;

import com.bing.ddup.model.User;
import com.bing.ddup.service.UserService;
import com.bing.framework.helper.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;

@Controller
@RequestMapping("auth")
public class AuthController {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();
    private UserService userService;

    @RequestMapping(value = "login")
    public String login(Model model,
                        HttpServletRequest request, HttpServletResponse response) {
        //获取session
        HttpSession session = request.getSession();
        logger.info("sessionid : {}", session.getId());
        //获取session中所有的键值
        Enumeration<?> enumeration = session.getAttributeNames();
        logger.info("session有值？ {}", enumeration.hasMoreElements());
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            Object value = session.getAttribute(name);
            logger.info("{}：{}", name, value);
        }
        return "/auth/login";
    }

    @RequestMapping(value = "register")
    public String register(Model model,
                           HttpServletRequest request, HttpServletResponse response) {
        return "/auth/register";
    }


//    /**
//     * 用户注册
//     * @param entity
//     * @return
//     */
//    public ResponseEntity<Map> createSubmit(User entity) {
//        //加密用户输入的密码，得到密码的摘要和盐，保存到数据库
//        User user = EndecryptUtils.md5Password(entity.getIdentifier(), entity.getCertificate());
//        entity.setPassword(user.getPassword());
//        entity.setSalt(user.getSalt());
//        Map<String, Object> map = Maps.newHashMap();
//        try {
//            boolean createResult = service.modify(entity, OperationType.create);
//            map.put("success", createResult);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<Map>(map, HttpStatus.OK);
//    }

    @RequestMapping("do-register")
    public ModelAndView register(@RequestParam("username") String username, @RequestParam("password") String password) throws SQLException {
        PasswordHelper helper = new PasswordHelper();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSalt("sugar");
        helper.encryptPassword(user);
        logger.info(user.getUsername());
        logger.info(user.getPassword());
        logger.info(user.getSalt());
        logger.info(user.getCredentialsSalt());
        user.setCreateTime(new Date());
        userService.insert(user);
        return new ModelAndView("/auth/login");
    }

    @RequestMapping("do-login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) throws SQLException {
        logger.info("{},{}", username, password);
        PasswordHelper helper = new PasswordHelper();
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setSalt("sugar");
//        helper.encryptPassword(user);

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        logger.info("000");
        Subject subject = SecurityUtils.getSubject();
        logger.info("111");
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            logger.error("捕获密码错误异常");
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", "password error!");
            return mv;
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            logger.error("捕获未知用户名异常");
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", "username error!");
            return mv;
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            logger.error("捕获错误登录过多的异常");
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("message", "times error");
            return mv;
        }
        logger.info("222");
//        User user = userService.findByUsername(username);
//        User user = new User();
        User user = userService.findByUsername(username);
        logger.info("333");
        logger.info(user.getUsername());
        subject.getSession().setAttribute("user", user);
        User user2 = (User) subject.getSession().getAttribute("user");
        logger.info(user2.getUsername());

//        return new ModelAndView("/index");
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping("do-logout")
    public ModelAndView logout() throws SQLException {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("redirect:/auth/login");
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
