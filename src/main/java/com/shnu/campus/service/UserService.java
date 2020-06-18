package com.shnu.campus.service;

import com.shnu.campus.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by guodi on 2020-04-15 21:29
 */
public interface UserService {


   User checkUser(String username,String password);

   User checkUserByName(String username);

   User getUser(Long id);

   User saveUser(User user);

   User updateUserById(Long id, User user);

   Page<User> listUser(Pageable pageable);

   void deleteUser(Long id);

}
