package com.shnu.campus.controller;

import com.shnu.campus.entity.Type;
import com.shnu.campus.service.ActivityService;
import com.shnu.campus.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by guodi on 2020-05-19 01:36
 */
@Controller
public class TypeController {

    @Autowired
    ActivityService activityService;

    @Autowired
    TypeService typeService;


    @GetMapping("/type")
    public String tests(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("types", typeService.listType(pageable));
        model.addAttribute("activity",activityService.listActivity(pageable));
        return "type";
    }

    @RequestMapping("/type/{id}")
    public String type(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @PathVariable Long id, Model model){

        model.addAttribute("types", typeService.listType(pageable));
        model.addAttribute("activity",activityService.listActivityByTypeId(id,pageable));

        return "type";
    }
}
