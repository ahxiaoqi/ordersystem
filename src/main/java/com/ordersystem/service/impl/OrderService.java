package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.entity.Order;
import com.ordersystem.entity.dto.OrderDto;
import com.ordersystem.mapper.OrderMapper;
import com.ordersystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/3/29 21:04
 */
@Service
public class OrderService implements GeneralService<Order> {

    @Autowired
    @SuppressWarnings("all")
    OrderMapper orderMapper;


    @Override
    public Order selectOneByWrapper(Order order) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(order);
        return orderMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Order> selectListByWrapper(Order order) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(order);
        return orderMapper.selectList(queryWrapper);
    }

    @Override
    public void save(Order order) {
        if (Optional.ofNullable(order.getOrderId()).isPresent()) {
            orderMapper.updateById(order);
        } else {
            orderMapper.insert(order);
        }
    }

    @Override
    public void deleteById(Integer id) {
        orderMapper.deleteById(id);
    }

    public OrderDto getOrderDetail(Integer orderId) {
        return orderMapper.getOrderDetail(orderId);
    }

    public IPage<Order> innitSlide(Page<Order> page, Order order) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(order);
        return orderMapper.selectPage(page, queryWrapper);
    }
}
