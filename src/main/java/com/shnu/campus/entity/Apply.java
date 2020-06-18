package com.shnu.campus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by guodi on 2020-05-20 17:49
 */
@Entity
@Getter
@Setter
public class Apply {

    public Apply() {
    }

    @Id
    @GeneratedValue
    private Long  id;

    private String name;//文章的作者

    private String email;

    private Long activityId;

    private String activityTitle;

    private String username;//报名的人姓名

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", activityId=" + activityId +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
