package com.shnu.campus.service;

import com.shnu.campus.entity.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by guodi on 2020-06-16 23:04
 */
public interface ApplyService {

    //查询参加我的活动的人的信息
    Page<Apply> listApplyByAuthor(String author, Pageable pageable);


    //查询我参加的活动
    Page<Apply> listApplyByUsername(String username, Pageable pageable);

    void deleteApplyByActivityIdAndName(Long id,String name);

}
