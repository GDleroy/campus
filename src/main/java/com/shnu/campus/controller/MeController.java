package com.shnu.campus.controller;

import com.shnu.campus.entity.Activity;
import com.shnu.campus.entity.Apply;
import com.shnu.campus.entity.User;
import com.shnu.campus.service.ActivityService;
import com.shnu.campus.service.ApplyService;
import com.shnu.campus.service.CommentService;
import com.shnu.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by guodi on 2020-05-14 18:20
 */
@Controller
public class MeController {

    @Autowired
    UserService userService;
    @Autowired
    ActivityService activityService;
    @Autowired
    ApplyService applyService;
    @Autowired
    CommentService commentService;

    private static final String ME = "me";
    private static final String REDIRECT_LIST = "redirect:/me";

    @GetMapping("/me")
    public String tests(HttpSession session,Model model,
                        @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        User user = (User)session.getAttribute("user");
        String name = user.getUsername();
        model.addAttribute("join",applyService.listApplyByAuthor(name,pageable));//参加我的
        model.addAttribute("meJoin",applyService.listApplyByUsername(name,pageable));//我参加的
        model.addAttribute("user", user);
        model.addAttribute("page", activityService.listActivityByUser(user,pageable));
        return ME;
    }

    @PostMapping("/me")//修改个人信息
    public String post(User user, Model model, RedirectAttributes attributes,
                       @RequestParam("file") MultipartFile file){
        User u;
        User user1 = (User)model.getAttribute("user");
        String fileName = file.getOriginalFilename();
        String path = "/Users/guodi/Documents/project/campus/src/main/resources/static/images";
        // 新建文件
        File filepath = new File(path, fileName);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try{
            file.transferTo(new File(path+File.separator+fileName));
        }catch (IOException e){
            e.printStackTrace();
        };
        if(("").equals(fileName) || null == fileName){
            user1.setAvatar("./images/89e4332c5c809edfe650cbb9b1de5ce0.jpg");
        }else{
            user1.setAvatar("./images/"+fileName);
        }

        user1.setUpdateTime(new Date());
        u = userService.updateUserById(user.getId(),user1);
        if (u == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return REDIRECT_LIST;
    }



    @PostMapping("/information")
    public String post(User user, RedirectAttributes attributes, HttpSession session) throws Exception{
        User sessionUser = (User)session.getAttribute("user");
        User b;
        if (sessionUser.getId() == null) {
            b =  userService.saveUser(user);
        } else {
            b = userService.updateUserById(sessionUser.getId(),user);
        }

        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "me :: userList";
    }

    @GetMapping("/activity/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        commentService.deleteCommentByActivityId(id);
        activityService.deleteActivity(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

    @GetMapping("/apply/{id}/delete")
    public String getDetailUser(@PathVariable Long id,
                                HttpSession session,RedirectAttributes attributes){
        User sessionUser = (User)session.getAttribute("user");
        applyService.deleteApplyByActivityIdAndName(id,sessionUser.getUsername());
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }
}
