package com.shnu.campus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by guodi on 2020-04-11 21:34
 */
@Controller
public class HomeController {
    //首页
    @RequestMapping("/home")
    public String home(HttpSession session){
        System.out.print(session);
        return "home";
    }

}
