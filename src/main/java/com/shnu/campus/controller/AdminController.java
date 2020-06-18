package com.shnu.campus.controller;

import com.shnu.campus.entity.Type;
import com.shnu.campus.service.*;
import com.shnu.campus.vo.ActivityQuery;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by guodi on 2020-06-15 13:55
 */
@Controller
public class AdminController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;


    private static final String LIST = "admin/admin";

    @GetMapping("/admin")
    public String blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        ActivityQuery activity, Model model) {
        model.addAttribute("page", activityService.listActivity(pageable, activity));
        return LIST;
    }

    @GetMapping("/class")
    public String find(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/class";
    }

    @GetMapping("/warning")
    public String warn(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",activityService.listActivities("1",pageable));
        return "admin/warn";
    }

    @GetMapping("/userinfo")
    public String user(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page",userService.listUser(pageable));
        return "admin/userinfo";
    }

    @PostMapping("/saveType")
    public String saveType(@RequestParam("name") String name,
                           RedirectAttributes attributes,
                           Type type){
        Type t;
        Type a = typeService.getTypeByName(name);
        if(a != null){
            attributes.addFlashAttribute("message", "类别名相同，不能新增");
            return "redirect:/class";
        }
        if (type.getId() == null) {
            t=typeService.saveType(type);
        } else {
            t=typeService.updateType(type.getId(),type);
        }

        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/class";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        activityService.deleteActivitiesByTypeId(id);//先删除父类中
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/class";
    }

    @GetMapping("activities/{id}/delete")
    public String deleteActivity(@PathVariable Long id, RedirectAttributes attributes){
        commentService.deleteCommentByActivityId(id);
        activityService.deleteActivity(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin";
    }

    @GetMapping("user/{id}/delete")
    public String deleteUser(@PathVariable Long id, RedirectAttributes attributes){
        activityService.deleteActivitiesByUserId(id);
        userService.deleteUser(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/userinfo";
    }
}
