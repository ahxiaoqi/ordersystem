package com.orderSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orderSystem.entity.Account;
import com.orderSystem.entity.Slide;
import com.orderSystem.mapper.SlideMapper;
import com.orderSystem.service.GeneralService;
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
        return slideMapper.selectList(queryWrapper);
    }

    @Override
    public void save(Slide slide) {
        QueryWrapper<Slide> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(slide);
        if (Optional.ofNullable(slide.getSlideId()).isPresent()) {
            slideMapper.update(slide,queryWrapper);
        } else {
            slideMapper.insert(slide);
        }

    }

    @Override
    public void deleteById(Integer id) {
        slideMapper.deleteById(id);
    }
}
