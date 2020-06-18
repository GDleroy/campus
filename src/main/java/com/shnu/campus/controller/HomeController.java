package com.shnu.campus.controller;

import com.shnu.campus.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;



import javax.servlet.http.HttpSession;

/**
 * Created by guodi on 2020-04-11 21:34
 */
@Controller
public class HomeController {

    @Autowired
    ActivityService activityService;
    //首页
    @GetMapping("/home")
    public String home(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model, HttpSession session){
        model.addAttribute("page",activityService.listActivity(pageable));
        return "home";
    }

}
