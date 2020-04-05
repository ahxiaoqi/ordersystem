package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ordersystem.entity.Slide;
import com.ordersystem.entity.Spec;
import com.ordersystem.mapper.SpecMapper;
import com.ordersystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/4/5 18:48
 */
@Service
public class SpecService implements GeneralService<Spec> {

    @Autowired
    @SuppressWarnings("all")
    SpecMapper specMapper;

    @Override
    public Spec selectOneByWrapper(Spec spec) {
        QueryWrapper<Spec> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(spec);
        return specMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Spec> selectListByWrapper(Spec spec) {
        QueryWrapper<Spec> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(spec);
        return specMapper.selectList(queryWrapper);
    }

    @Override
    public void save(Spec spec) {
        if (Optional.ofNullable(spec.getSpecId()).isPresent()) {
            specMapper.updateById(spec);
        } else {
            specMapper.insert(spec);
        }
    }

    @Override
    public void deleteById(Integer id) {
        specMapper.deleteById(id);
    }
}
