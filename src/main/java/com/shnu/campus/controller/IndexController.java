package com.shnu.campus.controller;

import com.shnu.campus.entity.User;
import com.shnu.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by guodi on 2020-04-06 14:24
 */

@Controller
public class IndexController {

    @Autowired
    UserService userService;


    //登陆页面
    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    //验证登陆
    @RequestMapping("/login")
    @ResponseBody
    public Boolean login(String username,String password){
        boolean flag = "admin".equals(username)&&"123456".equals(password) ? true : false;
        return flag;
    }


    //另一种验证登录方式
    @RequestMapping("/loginPost")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user != null){
            session.setAttribute("user",user);
            return "home";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/index";
        }
    }

    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");//退出时，清除session
        return "redirect:/";
    }


}
