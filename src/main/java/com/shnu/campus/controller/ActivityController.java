package com.shnu.campus.controller;

import com.shnu.campus.entity.Activity;
import com.shnu.campus.entity.Apply;
import com.shnu.campus.entity.User;
import com.shnu.campus.service.ActivityService;
import com.shnu.campus.service.CommentService;
import com.shnu.campus.service.TagService;
import com.shnu.campus.service.TypeService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Created by guodi on 2020-04-20 00:13
 */
@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;


    private static final String INPUT = "activity-input";
    private static final String LIST = "activity";
    private static final String REDIRECT_LIST = "redirect:/home";


    @GetMapping("/activity")
    public String blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                            ActivityQuery activity, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", activityService.listActivity(pageable, activity));
        return LIST;
    }

    @PostMapping("/activity/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                             ActivityQuery activity, Model model) {
        model.addAttribute("page", activityService.listActivity(pageable, activity));
        return "activity :: blogList";
    }


    @GetMapping("/activity-input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("activity", new Activity());
        return INPUT;
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }


    @GetMapping("/activities/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Activity activity = activityService.getActivity(id);
        activity.init();
        model.addAttribute("blog",activity);
        return INPUT;
    }

    @GetMapping("/activity/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("activity", activityService.getAndConvert(id));
        model.addAttribute("apply", new Apply());
        model.addAttribute("comments", commentService.listCommentByActivityId(id));
        return "detail";
    }


    @PostMapping("/activityInput")
    public String post(@RequestParam("file") MultipartFile file, Activity activity, RedirectAttributes attributes,
                       HttpSession session, Model model ) {

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
            activity.setPicture("./images/89e4332c5c809edfe650cbb9b1de5ce0.jpg");
        }else{
            activity.setPicture("./images/"+fileName);
        }
        activity.setUser((User)session.getAttribute("user"));
        activity.setActivityPerson(((User) session.getAttribute("user")).getUsername());
        activity.setType(typeService.getType(activity.getType().getId()));
        Activity b;
        if (activity.getId() == null) {
            b =  activityService.saveActivity(activity);
        } else {
            b = activityService.updateActivity(activity.getId(), activity);
        }

        if (b == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return REDIRECT_LIST;
    }

}
