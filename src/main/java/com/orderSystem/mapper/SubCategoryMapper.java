package com.orderSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orderSystem.entity.SubCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ahxiaoqi
 * @date 2020/2/2 23:12
 */
@Mapper
public interface SubCategoryMapper extends BaseMapper<SubCategory> {


    @Select("select SubCategoryName from t_subcategory where subCategoryId = #{subCategoryId}")
    String selectSubCategoryName(Integer subCategoryId);
}
