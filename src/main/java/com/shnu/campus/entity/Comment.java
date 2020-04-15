package com.shnu.campus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by guodi on 2020-04-08 11:14
 */
@Entity
@Getter
@Setter
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="comment_content")
    private String content;

    @Column(name="comment_owner")
    private String owner;

    @Column(name="comment_time")
    private Date commentTime;

    @Column(name="create_time")
    private Date createTime;

    @Column(name="update_time")
    private Date updateTime;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", owner='" + owner + '\'' +
                ", commentTime=" + commentTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
