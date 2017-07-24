package com.bing.ddup.web;

import com.bing.ddup.model.UserAuth;
import com.bing.ddup.service.UserAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Enumeration;

@Controller
@RequestMapping("/")
public class HomeController {
    // log4j2方式
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger();
    // slf4j方式
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HomeController.class);

    private UserAuthService userAuthService;

    @RequestMapping("home")
    public String home() throws SQLException {
        logger.info("hello home!");
//        logger.info("hello home!");
        UserAuth userAuth = userAuthService.login("admin", "1");
        logger.info(userAuth.getIdentifier());
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model,
                HttpServletRequest request, HttpServletResponse response) {
        LOG.info("hi {}！", "hello index!");
        HttpSession session = request.getSession();
        logger.info("sessionid : {}", session.getId());
        session.setAttribute("hello", "just a joke");
        return "index";
    }

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

    @Resource
    public void setUserAuthService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }
}
