package com.ordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ordersystem.entity.Activity;
import com.ordersystem.entity.dto.ActivityDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ahxiaoqi
 * @date 2020/1/31 22:47
 */
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("  SELECT b.description,a.activityStr,a.activityId,b.productId,a.priceParam1,a.priceParam2,b.image,b.productName FROM `t_product` b\n" +
            " LEFT JOIN `t_activity` a ON a.`activityId` = b.activityId WHERE a.type=#{type} and a.activityType = #{activityType} limit #{start},#{end}")
    List<ActivityDto> innitMinPriceGoodsBox(@Param("type") int type, @Param("activityType") int activityType, @Param("start") int start, @Param("end") int end);
}
