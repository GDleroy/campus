package com.shnu.campus.service;

import com.shnu.campus.entity.Activity;
import com.shnu.campus.entity.User;
import com.shnu.campus.vo.ActivityQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by guodi on 2020-04-20 10:12
 */
public interface ActivityService {

    Activity getActivity(Long id);

    Activity getAndConvert(Long id);

    Page<Activity> listActivity(Pageable pageable, ActivityQuery blog);

    Page<Activity> listActivity(Pageable pageable);

    Page<Activity> listActivity(Long tagId,Pageable pageable);

    Page<Activity> listActivityByTypeId(Long typeId,Pageable pageable);

    Page<Activity> listActivity(String query,Pageable pageable);

    Page<Activity> listActivities(String warnStatus,Pageable pageable);

    List<Activity> listRecommendActivityTop(Integer size);

    Page<Activity> listActivityByUser(User user,Pageable pageable);

    Map<String,List<Activity>> archiveActivity();

    Long countActivity();

    String findAuthorNameByActivityId(Long activityId);

    Activity saveActivity(Activity activity);

    Activity updateActivity(Long id,Activity activity);

    void deleteActivity(Long id);

    void deleteActivitiesByTypeId(Long id);

    void deleteActivitiesByUserId(Long id);
}
