package com.shnu.campus.service.impl;

import com.shnu.campus.dao.ApplyRepository;
import com.shnu.campus.entity.Apply;
import com.shnu.campus.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by guodi on 2020-06-16 23:07
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    ApplyRepository applyRepository;

    @Override
    public Page<Apply> listApplyByAuthor(String author, Pageable pageable) {
        return applyRepository.findByName(author,pageable);
    }

    @Override
    public Page<Apply> listApplyByUsername(String username, Pageable pageable) {
        return applyRepository.findByUsername(username,pageable);
    }

    @Override
    public void deleteApplyByActivityIdAndName(Long id, String name) {
        applyRepository.deleteApplyByActivityIdAndUsername(id,name);
    }
}
