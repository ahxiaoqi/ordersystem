package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.data.ReturnData;
import com.ordersystem.entity.Activity;
import com.ordersystem.entity.Product;
import com.ordersystem.entity.dto.ActivityDto;
import com.ordersystem.mapper.ActivityMapper;
import com.ordersystem.service.GeneralService;
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
        if (Optional.ofNullable(activity.getActivityId()).isPresent()) {
            activityMapper.updateById(activity);
        } else {
            activityMapper.insert(activity);
        }

    }

    @Override
    public void deleteById(Integer id) {
        activityMapper.deleteById(id);
    }

    public List<ActivityDto> innitMinPriceGoodsBox(Integer type, Integer activityType, Integer start, Integer end) {
        return activityMapper.innitMinPriceGoodsBox(type, activityType, start, end);
    }

    public IPage<ActivityDto> innitActivity(Page<Activity> page, Activity activity) {
        return activityMapper.innitActivity(page, activity);
    }

    public ReturnData saveAndCheckCount(Activity activity) {
        // 首先关闭所有活动
        if (activity.getStatus() == 1) {
            UpdateWrapper<Activity> wrapper = new UpdateWrapper<>();
            activityMapper.update(Activity.builder().status(2).build(), wrapper);
        }
        if (Optional.ofNullable(activity.getActivityId()).isPresent()) {
            activityMapper.updateById(activity);
        } else {
            activityMapper.insert(activity);
        }
        return ReturnData.returnData(null);
    }
}
