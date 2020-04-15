package com.shnu.campus.dao;

import com.shnu.campus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by guodi on 2020-04-15 21:40
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
