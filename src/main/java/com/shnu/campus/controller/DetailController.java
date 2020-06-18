package com.shnu.campus.controller;

import com.shnu.campus.entity.Activity;
import com.shnu.campus.entity.Apply;
import com.shnu.campus.service.ActivityService;
import com.shnu.campus.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by guodi on 2020-05-20 16:49
 */
@Controller
public class DetailController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private DetailService detailService;

    //返回首页
    @GetMapping("/activity/home")
    public String blog(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model, HttpSession session) {
        model.addAttribute("activity",null);
        model.addAttribute("page",activityService.listActivity(pageable));
        return "redirect:/home";
    }

    //申请者报名
    @PostMapping("activity/apply")
    public String apply(Apply apply, RedirectAttributes attributes){

        Apply a;
        a = detailService.saveApply(apply);

        if (a == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        System.out.print("用户申请，保存成功！");
        return "redirect:/me";
    }

    @PostMapping("/activity/warnApply")
    public String warn(Activity activity,RedirectAttributes attributes){
        Activity b;
        if(activity.getId() != null){
            activity.setWarnStatus("1");
            b=activityService.updateActivity(activity.getId(),activity);
        }else {
            b = null;
        }
        if (b == null ) {
            attributes.addFlashAttribute("message", "举报失败");
        } else {
            attributes.addFlashAttribute("message", "举报成功");
        }

        return "redirect:/home";
    }


    //注销
    @GetMapping("/activity/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");//退出时，清除session
        return "redirect:/index";
    }
}
