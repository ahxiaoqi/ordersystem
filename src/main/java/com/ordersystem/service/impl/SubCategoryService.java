package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.entity.SubCategory;
import com.ordersystem.mapper.SubCategoryMapper;
import com.ordersystem.service.GeneralService;
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
        if (Optional.ofNullable(subCategory.getSubCategoryId()).isPresent()) {
            subCategoryMapper.updateById(subCategory);
        } else {
            subCategoryMapper.insert(subCategory);
        }
    }

    @Override
    public void deleteById(Integer id) {
        subCategoryMapper.deleteById(id);
    }

    public IPage<SubCategory> innitSubCategory(SubCategory subCategory, Page<SubCategory> page) {
        QueryWrapper<SubCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(subCategory);
        return subCategoryMapper.selectPage(page,queryWrapper);
    }
}
