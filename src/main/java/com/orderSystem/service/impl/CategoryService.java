package com.orderSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderSystem.entity.Account;
import com.orderSystem.entity.Category;
import com.orderSystem.entity.SubCategory;
import com.orderSystem.mapper.AccountMapper;
import com.orderSystem.mapper.CategoryMapper;
import com.orderSystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/1/27 13:20
 */
@Service
public class CategoryService implements GeneralService<Category>{

    @Autowired
    @SuppressWarnings("all")
    CategoryMapper categoryMapper;

    @Override
    public Category selectOneByWrapper(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(category);
        return categoryMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Category> selectListByWrapper(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(category);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public void save(Category category) {
        if (Optional.ofNullable(category.getCategoryId()).isPresent()) {
            categoryMapper.updateById(category);
        } else {
            categoryMapper.insert(category);
        }

    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

}
