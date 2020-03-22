package com.orderSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orderSystem.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ahxiaoqi
 * @date 2020/1/26 13:56
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select categoryName from t_category where categoryId = #{categoryId}")
    String selectCategoryName(Integer categoryId);
}
