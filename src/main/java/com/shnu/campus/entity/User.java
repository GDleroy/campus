package com.shnu.campus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by guodi on 2020-04-09 22:06
 */
@Entity
@Getter
@Setter
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @Column(name="username")
    public String username;

    @Column(name="password")
    public String password;

    @Column(name="gender")
    public String gender;

    @Column(name="role")
    public String role;

    @Column(name="create_time")
    public String createTime;

    @Column(name="update_time")
    public String updateTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", createtime='" + createTime + '\'' +
                ", updatetime='" + updateTime + '\'' +
                '}';
    }
}
