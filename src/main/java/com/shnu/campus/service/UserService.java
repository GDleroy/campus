package com.shnu.campus.service;

import com.shnu.campus.entity.User;

/**
 * Created by guodi on 2020-04-15 21:29
 */
public interface UserService {


   User checkUser(String username,String password);
}
