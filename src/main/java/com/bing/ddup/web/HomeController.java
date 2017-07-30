package com.bing.ddup.web;

import com.bing.ddup.model.User;
import com.bing.ddup.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class HomeController {
    // log4j2方式
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger();
    // slf4j方式
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HomeController.class);

    private UserService userService;

    @RequestMapping("home")
    public String home() throws SQLException {
        logger.info("hello home!");
//        logger.info("hello home!");
        User user = userService.login("admin", "1");
        logger.info(user.getUsername());
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

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
