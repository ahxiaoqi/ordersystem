package com.orderSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orderSystem.entity.Activity;
import com.orderSystem.entity.Slide;
import com.orderSystem.entity.dto.ActivityDto;
import com.orderSystem.mapper.ActivityMapper;
import com.orderSystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/1/31 22:47
 */
@Service
public class ActivityService implements GeneralService<Activity> {

    @Autowired
    @SuppressWarnings("all")
    ActivityMapper activityMapper;

    @Override
    public Activity selectOneByWrapper(Activity activity) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(activity);
        return activityMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Activity> selectListByWrapper(Activity activity) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(activity);
        return activityMapper.selectList(queryWrapper);
    }

    @Override
    public void save(Activity activity) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(activity);
        if (Optional.ofNullable(activity.getActivityId()).isPresent()) {
            activityMapper.update(activity, queryWrapper);
        } else {
            activityMapper.insert(activity);
        }

    }

    @Override
    public void deleteById(Integer id) {
        activityMapper.deleteById(id);
    }

    public List<ActivityDto> innitMinPriceGoodsBox(Integer type,Integer activityType, Integer start, Integer end) {
        return activityMapper.innitMinPriceGoodsBox(type,activityType,start, end);
    }
}
