package com.shnu.campus.controller;

import com.shnu.campus.entity.Activity;
import com.shnu.campus.entity.Apply;
import com.shnu.campus.entity.User;
import com.shnu.campus.service.ActivityService;
import com.shnu.campus.service.UserService;
import com.shnu.campus.vo.ActivityQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by guodi on 2020-04-06 14:24
 */

@Controller
public class IndexController {

    @Autowired
    UserService userService;
    @Autowired
    private ActivityService activityService;

    //登陆页面
    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    //登陆页面
    @RequestMapping("/test")
    public String test(){
        return "test";
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
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        HttpSession session,
                        ActivityQuery activity,
                        RedirectAttributes attributes, Model model){
        User user = userService.checkUser(username,password);
        if(user != null){
            session.setAttribute("user",user);
            if("admin".equals(username)){
                model.addAttribute("page",activityService.listActivity(pageable));
                return "admin/admin";
            }else {
                model.addAttribute("page",activityService.listActivity(pageable));
                return "home";
            }
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/index";
        }
    }

    //另一种验证登录方式
    @RequestMapping("/activityPage")
    public String page(Model model, @RequestParam(value = "page",defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "8") Integer size){
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Activity> activities = activityService.listActivity(pageable);
        model.addAttribute("page",activities);
        return "home";
    }
    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");//退出时，清除session
        return "redirect:/index";
    }

    //用于注册账号
    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password-one") String passwordOne,
                           @RequestParam("password-two") String passwordTwo,
                           @RequestParam("email") String email,
                           RedirectAttributes attributes){
        if(username == null || passwordOne == null || passwordTwo == null){
            attributes.addFlashAttribute("message","请填写完整用户名或密码");
            return "redirect:/index";
        }else {
            if(passwordOne.length()<6 ||passwordTwo.length()<6){
                attributes.addFlashAttribute("message","密码小于6位不能注册");
                return "redirect:/index";
            }
            if(!passwordOne.equals(passwordTwo)){
                attributes.addFlashAttribute("message","两次密码不一致不能注册");
                return "redirect:/index";
            }
            User user = userService.checkUserByName(username);
            if(user != null){
                attributes.addFlashAttribute("message","用户已存在不能注册");
                return "redirect:/index";
            }else {
                User user1 = new User();
                user1.setUsername(username);
                user1.setPassword(passwordOne);
                user1.setEmail(email);
                user1.setAvatar("https://unsplash.it/100/100?image=1005");
                User user2 = userService.saveUser(user1);
                if(user2!=null){
                    //保存用户信息
                    attributes.addFlashAttribute("message","用户注册成功");
                    return "redirect:/index";
                }
                return "redirect:/index";
            }
        }

    }

}
