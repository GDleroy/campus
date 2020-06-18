package com.shnu.campus.service;

import com.shnu.campus.entity.Comment;

import java.util.List;

/**
 * Created by guodi on 2020-04-20 14:58
 */
public interface CommentService {

    List<Comment> listCommentByActivityId(Long activityId);

    Comment saveComment(Comment comment);

    void deleteCommentByActivityId(Long id);
}
