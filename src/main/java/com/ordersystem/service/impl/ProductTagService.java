package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ordersystem.entity.ProductTag;
import com.ordersystem.entity.ProductTag;
import com.ordersystem.mapper.ProductTagMapper;
import com.ordersystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/3/28 23:04
 */
@Service
public class ProductTagService implements GeneralService<ProductTag> {

    @Autowired
    @SuppressWarnings("all")
    ProductTagMapper productTagMapper;

    @Override
    public ProductTag selectOneByWrapper(ProductTag productTag) {
        QueryWrapper<ProductTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(productTag);
        return productTagMapper.selectOne(queryWrapper);
    }

    @Override
    public List<ProductTag> selectListByWrapper(ProductTag productTag) {
        QueryWrapper<ProductTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(productTag);
        return productTagMapper.selectList(queryWrapper);
    }

    @Override
    public void save(ProductTag productTag) {
        if (Optional.ofNullable(productTag.getProductTagId()).isPresent()) {
            productTagMapper.updateById(productTag);
        } else {
            productTagMapper.insert(productTag);
        }
    }

    @Override
    public void deleteById(Integer id) {
        productTagMapper.deleteById(id);
    }
}
