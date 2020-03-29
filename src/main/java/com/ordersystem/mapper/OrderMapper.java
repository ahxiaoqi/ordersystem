package com.ordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ordersystem.entity.Order;
import com.ordersystem.entity.dto.OrderDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * @author ahxiaoqi
 * @date 2020/1/26 14:00
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Results({
            @Result(column = "orderId", property = "orderId"),
            @Result(property = "orderSub", column = "orderId", one = @One(select = "com.ordersystem.mapper.OrderSubMapper.selectByOderId"), jdbcType = JdbcType.VARCHAR),
    })
    @Select("select * from t_order where orderId = #{orderId}")
    OrderDto getOrderDetail(Integer orderId);
}
