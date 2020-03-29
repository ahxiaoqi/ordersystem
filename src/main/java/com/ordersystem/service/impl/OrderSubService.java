package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ordersystem.entity.Order;
import com.ordersystem.entity.OrderSub;
import com.ordersystem.mapper.OrderSubMapper;
import com.ordersystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/3/29 23:34
 */
@Service
public class OrderSubService implements GeneralService<OrderSub> {

    @Autowired
    @SuppressWarnings("all")
    OrderSubMapper orderSubMapper;

    @Override
    public OrderSub selectOneByWrapper(OrderSub orderSub) {
        QueryWrapper<OrderSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(orderSub);
        return orderSubMapper.selectOne(queryWrapper);
    }

    @Override
    public List<OrderSub> selectListByWrapper(OrderSub orderSub) {
        QueryWrapper<OrderSub> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(orderSub);
        return orderSubMapper.selectList(queryWrapper);
    }

    @Override
    public void save(OrderSub orderSub) {
        if (Optional.ofNullable(orderSub.getOrderSubId()).isPresent()) {
            orderSubMapper.updateById(orderSub);
        } else {
            orderSubMapper.insert(orderSub);
        }
    }

    @Override
    public void deleteById(Integer id) {
        orderSubMapper.deleteById(id);
    }
}
