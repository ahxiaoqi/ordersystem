package com.orderSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderSystem.entity.Product;
import com.orderSystem.entity.SubCategory;
import com.orderSystem.mapper.SubCategoryMapper;
import com.orderSystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/2/2 23:12
 */
@Service
public class SubCategoryService implements GeneralService<SubCategory> {

    @Autowired
    @SuppressWarnings("all")
    SubCategoryMapper subCategoryMapper;

    @Override
    public SubCategory selectOneByWrapper(SubCategory subCategory) {
        QueryWrapper<SubCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(subCategory);
        return subCategoryMapper.selectOne(queryWrapper);
    }

    @Override
    public List<SubCategory> selectListByWrapper(SubCategory subCategory) {
        QueryWrapper<SubCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(subCategory);
        return subCategoryMapper.selectList(queryWrapper);
    }

    @Override
    public void save(SubCategory subCategory) {
        QueryWrapper<SubCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(subCategory);
        if (Optional.ofNullable(subCategory.getSubCategoryId()).isPresent()) {
            subCategoryMapper.update(subCategory,queryWrapper);
        } else {
            subCategoryMapper.insert(subCategory);
        }
    }

    @Override
    public void deleteById(Integer id) {
        subCategoryMapper.deleteById(id);
    }

    public IPage<SubCategory> innitSubCategory(Integer categoryId, Page<SubCategory> page) {
        QueryWrapper<SubCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(SubCategory.builder().categoryId(categoryId).build());
        return subCategoryMapper.selectPage(page,queryWrapper);
    }
}
