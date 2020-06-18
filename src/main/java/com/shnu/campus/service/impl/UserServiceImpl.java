package com.shnu.campus.service.impl;

import com.shnu.campus.NotFoundException;
import com.shnu.campus.dao.UserRepository;
import com.shnu.campus.entity.Activity;
import com.shnu.campus.entity.User;
import com.shnu.campus.service.UserService;
import com.shnu.campus.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by guodi on 2020-04-15 21:35
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUserByName(String username) {
        User user =userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User checkUser(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username,password);
        return user;
    }

    @Override
    public User saveUser(User user) {
        if(user.getId() == null){
            user.setCreateTime(new Date());
        }else {
            user.setUpdateTime(new Date());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(Long id, User user) {
        User b = userRepository.findById(id).get();
        if (b == null) {
            throw new NotFoundException("该不存在");
        }
        BeanUtils.copyProperties(user,b, MyBeanUtils.getNullPropertyNames(user));
        b.setUpdateTime(new Date());
        return userRepository.save(b);
    }

    @Override
    public Page<User> listUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
