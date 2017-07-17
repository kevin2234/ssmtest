package com.bing.ddup.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
    // log4j2方式
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger();
    // slf4j方式
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("home")
    public String home(){
        LOG.info("阿里巴巴最牛xxxxxxxxxxx！");
        logger.info("阿里巴巴最牛xxxxxxxxxxx！");
        System.out.println("hello home!");
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("hello index!");
        return "index";
    }
}
