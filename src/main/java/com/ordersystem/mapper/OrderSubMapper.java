package com.ordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ordersystem.entity.OrderSub;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author ahxiaoqi
 * @date 2020/3/29 23:32
 */
@Mapper
public interface OrderSubMapper extends BaseMapper<OrderSub> {

    @Select("select * from t_order_sub where orderId = #{orderId}")
    OrderSub selectByOderId(@Param("orderId") int orderId);
}
