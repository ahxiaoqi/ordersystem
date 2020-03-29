package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.entity.Activity;
import com.ordersystem.entity.Slide;
import com.ordersystem.mapper.SlideMapper;
import com.ordersystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/1/31 19:35
 */
@Service
public class SlideService implements GeneralService<Slide> {

    @Autowired
    @SuppressWarnings("all")
    SlideMapper slideMapper;

    @Override
    public Slide selectOneByWrapper(Slide slide) {
        QueryWrapper<Slide> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(slide);
        return slideMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Slide> selectListByWrapper(Slide slide) {
        QueryWrapper<Slide> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(slide);
        queryWrapper.orderByAsc("slideOrder");
        return slideMapper.selectList(queryWrapper);
    }

    @Override
    public void save(Slide slide) {
        if (Optional.ofNullable(slide.getSlideId()).isPresent()) {
            slideMapper.updateById(slide);
        } else {
            slideMapper.insert(slide);
        }

    }

    @Override
    public void deleteById(Integer id) {
        slideMapper.deleteById(id);
    }

    public IPage<Slide> innitSlide(Page<Slide> page, Slide slide) {
        QueryWrapper<Slide> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(slide);
        queryWrapper.orderByAsc("slideOrder");
        return slideMapper.selectPage(page, queryWrapper);
    }
}
