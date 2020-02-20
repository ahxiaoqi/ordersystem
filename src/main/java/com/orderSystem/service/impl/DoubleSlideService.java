package com.orderSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orderSystem.entity.DoubleSlide;
import com.orderSystem.entity.Slide;
import com.orderSystem.mapper.DoubleSlideMapper;
import com.orderSystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/1/31 20:22
 */
@Service
public class DoubleSlideService implements GeneralService<DoubleSlide> {

    @Autowired
    @SuppressWarnings("all")
    DoubleSlideMapper doubleSlideMapper;

    @Override
    public DoubleSlide selectOneByWrapper(DoubleSlide doubleSlide) {
        QueryWrapper<DoubleSlide> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(doubleSlide);
        return doubleSlideMapper.selectOne(queryWrapper);
    }

    @Override
    public List<DoubleSlide> selectListByWrapper(DoubleSlide doubleSlide) {
        QueryWrapper<DoubleSlide> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(doubleSlide);
        return doubleSlideMapper.selectList(queryWrapper);
    }

    @Override
    public void save(DoubleSlide doubleSlide) {
        QueryWrapper<DoubleSlide> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(doubleSlide);
        if (Optional.ofNullable(doubleSlide.getDoubleSlideId()).isPresent()) {
            doubleSlideMapper.update(doubleSlide, queryWrapper);
        } else {
            doubleSlideMapper.insert(doubleSlide);
        }

    }

    @Override
    public void deleteById(Integer id) {
        doubleSlideMapper.deleteById(id);
    }
}
