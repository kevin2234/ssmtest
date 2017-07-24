package com.bing.ddup.web;

import com.bing.ddup.model.User;
import com.bing.ddup.model.UserAuth;
import com.bing.ddup.service.UserAuthService;
import com.bing.framework.helper.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.sql.SQLException;

@Controller
@RequestMapping("auth")
public class LoginController {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();
    private UserAuthService userAuthService;


//    /**
//     * 用户注册
//     * @param entity
//     * @return
//     */
//    public ResponseEntity<Map> createSubmit(UserAuth entity) {
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

    @RequestMapping("register")
    public ModelAndView register(@RequestParam("username") String username, @RequestParam("password") String password) throws SQLException {
        PasswordHelper helper = new PasswordHelper();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSalt("sugar");
        helper.encryptPassword(user);
        logger.info(user.getUsername());
        logger.info(user.getPassword());
        return new ModelAndView("login");
    }

    @RequestMapping("login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) throws SQLException {
        logger.info("{},{}", username, password);
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
//        User user = userAuthService.findByUsername(username);
//        User user = new User();
        UserAuth userAuth = userAuthService.login(username,password);
        logger.info("333");
        logger.info(userAuth);
        subject.getSession().setAttribute("user", userAuth);
        return new ModelAndView("success");
    }

    @Resource
    public void setUserAuthService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }
}