package com.shnu.campus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guodi on 2020-04-08 11:14
 */
@Entity
@Getter
@Setter
@Table(name = "t_comment")
public class Comment {

    public Comment() {
    }

    @Id
    @GeneratedValue
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

    @ManyToOne
    private Activity activity;

    @OneToMany(mappedBy = "parentComment",fetch=FetchType.EAGER)
    private List<Comment> replyComments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;

    private String avatar;

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
