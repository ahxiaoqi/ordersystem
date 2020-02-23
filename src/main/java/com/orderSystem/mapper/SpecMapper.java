package com.orderSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orderSystem.entity.Spec;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ahxiaoqi
 * @date 2020/2/23 22:35
 */
@Mapper
public interface SpecMapper extends BaseMapper<Spec> {

    @Select("select * from t_spec where productId = ${productId}")
    List<Spec> selectSpecByProductId(Integer productId);

}
