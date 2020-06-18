package com.shnu.campus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guodi on 2020-04-09 22:06
 */
@Entity
@Getter
@Setter
@Table(name = "t_user")
public class User {

    public User() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name="password")
    private String password;

    @Column(name="gender")
    private String gender;

    @Column(name="role")
    private String role;

    @Column(name="email")
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_time")
    private Date updateTime;

    private String avatar;

    @Column(name = "picture")
    private String picture;

    private Integer type;

    private String info;

    @Column(name = "birthday")
    private String birthday;

    @OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
    private List<Activity> activities = new ArrayList<>();


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", avatar='" + avatar + '\'' +
                ", activities=" + activities +
                '}';
    }
}
