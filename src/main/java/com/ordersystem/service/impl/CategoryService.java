package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.entity.Category;
import com.ordersystem.entity.SubCategory;
import com.ordersystem.mapper.CategoryMapper;
import com.ordersystem.mapper.SubCategoryMapper;
import com.ordersystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/1/27 13:20
 */
@Service
public class CategoryService implements GeneralService<Category> {

    @Autowired
    @SuppressWarnings("all")
    CategoryMapper categoryMapper;

    @Autowired
    @SuppressWarnings("all")
    SubCategoryMapper subCategoryMapper;


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
        QueryWrapper<SubCategory> queryWrapper = new QueryWrapper<>();
        SubCategory build = SubCategory.builder().categoryId(id).build();
        subCategoryMapper.delete(queryWrapper.setEntity(build));
    }

    public IPage<Category> innitCategory(Category category, Page<Category> page) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(category);
        return categoryMapper.selectPage(page, queryWrapper);
    }
}
