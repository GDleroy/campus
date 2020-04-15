package com.shnu.campus.service.impl;

import com.shnu.campus.dao.UserRepository;
import com.shnu.campus.entity.User;
import com.shnu.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guodi on 2020-04-15 21:35
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username,password);
        return user;
    }
}
