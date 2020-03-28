package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ordersystem.entity.DoubleSlide;
import com.ordersystem.mapper.DoubleSlideMapper;
import com.ordersystem.service.GeneralService;
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
        if (Optional.ofNullable(doubleSlide.getDoubleSlideId()).isPresent()) {
            doubleSlideMapper.updateById(doubleSlide);
        } else {
            doubleSlideMapper.insert(doubleSlide);
        }

    }

    @Override
    public void deleteById(Integer id) {
        doubleSlideMapper.deleteById(id);
    }
}
