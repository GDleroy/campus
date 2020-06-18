package com.shnu.campus.service.impl;

import com.shnu.campus.NotFoundException;
import com.shnu.campus.dao.ActivityRepository;
import com.shnu.campus.entity.Activity;
import com.shnu.campus.entity.Type;
import com.shnu.campus.entity.User;
import com.shnu.campus.service.ActivityService;
import com.shnu.campus.utils.MyBeanUtils;
import com.shnu.campus.vo.ActivityQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created by guodi on 2020-04-20 10:14
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public Page<Activity> listActivityByUser(User user,Pageable pageable) {
        return activityRepository.findByUser(user, pageable);
    }

    @Override
    public Activity getActivity(Long id) {
        return activityRepository.getOne(id);
    }

    @Transactional
    @Override
    public Activity getAndConvert(Long id) {
        Activity activity = activityRepository.getOne(id);
        if (activity == null) {
            throw new NotFoundException("该活动不存在");
        }
        Activity b = new Activity();
        BeanUtils.copyProperties(activity,b);
        String content = b.getActivityContent();
        b.setActivityContent(content);

        activityRepository.updateViews(id);
        return b;
    }

    @Override
    public Page<Activity> listActivity(Pageable pageable, ActivityQuery activity) {
        return activityRepository.findAll(new Specification<Activity>() {
            @Override
            public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(activity.getTitle()) && activity.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+activity.getTitle()+"%"));
                }
                if (activity.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), activity.getTypeId()));
                }
                if (activity.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), activity.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Activity> listActivity(Pageable pageable) {
        return activityRepository.findAll(pageable);
    }

    @Override
    public Page<Activity> listActivity(Long tagId, Pageable pageable) {
        return activityRepository.findAll(new Specification<Activity>() {
            @Override
            public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Page<Activity> listActivityByTypeId(Long typeId, Pageable pageable) {

        return activityRepository.findByTypeId(typeId,pageable);
    }


    @Override
    public Page<Activity> listActivity(String query, Pageable pageable) {
        return activityRepository.findByQuery(query,pageable);
    }

    @Override
    public Page<Activity> listActivities(String warnStatus, Pageable pageable) {
        return activityRepository.findByWarnStatus(warnStatus,pageable);
    }

    @Override
    public List<Activity> listRecommendActivityTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return activityRepository.findTop(pageable);
    }

    @Override
    public Map<String, List<Activity>> archiveActivity() {
        List<String> years = activityRepository.findGroupYear();
        Map<String, List<Activity>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, activityRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countActivity() {
        return activityRepository.count();
    }

    @Override
    public String findAuthorNameByActivityId(Long activityId) {

        return null;
    }

    @Transactional
    @Override
    public Activity saveActivity(Activity activity) {
        if(activity.getId() == null){
            activity.setCreateTime(new Date());
            activity.setUpdateTime(new Date());
            activity.setViews(0);
        }else {
            activity.setUpdateTime(new Date());
        }
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Long id, Activity activity) {
        Activity b = activityRepository.findById(id).get();
        if (b == null) {
            throw new NotFoundException("该不存在");
        }
        BeanUtils.copyProperties(activity,b, MyBeanUtils.getNullPropertyNames(activity));
        b.setUpdateTime(new Date());
        return activityRepository.save(b);
    }

    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public void deleteActivitiesByTypeId(Long id) {
        activityRepository.deleteActivitiesByTypeId(id);
    }

    @Override
    public void deleteActivitiesByUserId(Long id) {
        activityRepository.deleteActivitiesByUserId(id);
    }
}
