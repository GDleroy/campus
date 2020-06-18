package com.shnu.campus.dao;

import com.shnu.campus.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by guodi on 2020-04-20 15:52
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByActivityIdAndParentCommentNull(Long activityId, Sort sort);

    @Transactional
    void deleteByActivityId(Long id);
}
