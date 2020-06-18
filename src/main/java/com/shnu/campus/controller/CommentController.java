package com.shnu.campus.controller;

import com.shnu.campus.entity.Activity;
import com.shnu.campus.entity.Comment;
import com.shnu.campus.entity.User;
import com.shnu.campus.service.ActivityService;
import com.shnu.campus.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by guodi on 2020-04-20 21:30
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ActivityService activityService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{activityId}")
    public String comments(@PathVariable Long activityId, Model model) {
        model.addAttribute("comments", commentService.listCommentByActivityId(activityId));
        Activity activity = new Activity();
        activity.setId(activityId);
        model.addAttribute("activity", activity);
        return "detail :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long activityId = comment.getActivity().getId();
        comment.setActivity(activityService.getActivity(activityId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + activityId;
    }


}
