package com.ordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.entity.Order;
import com.ordersystem.entity.OrderSub;
import com.ordersystem.entity.Product;
import com.ordersystem.entity.dto.OrderDto;
import com.ordersystem.entity.dto.OrderSubDto;
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

    @Results({
            @Result(column = "productId", property = "productId"),
            @Result(property = "product", column = "productId", one = @One(select = "com.ordersystem.mapper.OrderMapper.selectProductById"), jdbcType = JdbcType.VARCHAR)
    })
    @Select("select * from t_order_sub a left join t_order b on a.orderId = b.orderId where b.accountId = #{orderDto.accountId}" +
            " and a.isComment = #{orderDto.isComment}")
    IPage<OrderSubDto> innitMyOrderList(OrderDto orderDto, Page<OrderDto> page);

    @Select("select * from t_product where productId = #{productId}")
    Product selectProductById(int productId);
}
