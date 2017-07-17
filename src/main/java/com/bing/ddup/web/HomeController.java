package com.bing.ddup.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping("home")
    public String home(){
        System.out.println("hello home!");
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("hello index!");
        return "index";
    }
}
