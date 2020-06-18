package com.shnu.campus.service.impl;

import com.shnu.campus.dao.ApplyRepository;
import com.shnu.campus.entity.Apply;
import com.shnu.campus.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by guodi on 2020-05-21 00:54
 */
@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    ApplyRepository applyRepository;

    @Transactional
    @Override
    public Apply saveApply(Apply apply) {
        if(apply.getId() == null){
            apply.setCreateTime(new Date());
            apply.setUpdateTime(new Date());
        }else{
            apply.setUpdateTime(new Date());
        }
        return applyRepository.save(apply);
    }
}
