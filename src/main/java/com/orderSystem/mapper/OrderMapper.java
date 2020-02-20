package com.orderSystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orderSystem.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ahxiaoqi
 * @date 2020/1/26 14:00
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
