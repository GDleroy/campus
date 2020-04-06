package com.shnu.campus.controller;

import com.shnu.campus.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by guodi on 2020-04-06 14:24
 */

@Controller
public class IndexController {

    @RequestMapping("/{id}/{name}")
    public String index(){
//        int i = 9/0;
//        String activity = null;
//        if(activity == null){
//            throw new NotFoundException("活动未找到！");
//        }
        System.out.print("----shouye--");
        return "index";
    }
}
