package com.shnu.campus.dao;

import com.shnu.campus.entity.Activity;
import com.shnu.campus.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by guodi on 2020-04-20 10:42
 */
public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {

    @Query("select b from Activity b where b.recommend = true")
    List<Activity> findTop(Pageable pageable);

    @Query("select b from Activity b where b.user = ?1")
    Page<Activity> findByUser(User user, Pageable pageable);

    @Query("select b from Activity b where b.activityTitle like ?1 or b.activityContent like ?1")
    Page<Activity> findByQuery(String query, Pageable pageable);

    @Query("select b from Activity b where b.warnStatus = ?1 ")
    Page<Activity> findByWarnStatus(String warnStatus, Pageable pageable);

    Page<Activity> findByTypeId(Long typeId, Pageable pageable);

    @Query("select b.activityPerson from Activity b where b.id = ?1")
    String findByActivityId(Long id);

    @Transactional
    @Modifying
    @Query("update Activity b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format',b.updateTime,'%Y') as year from Activity b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Activity b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Activity> findByYear(String year);

    @Transactional
    void deleteActivitiesByTypeId(Long id);

    @Transactional
    void deleteActivitiesByUserId(Long id);
}
