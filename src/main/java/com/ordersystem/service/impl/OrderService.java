package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.data.ReturnData;
import com.ordersystem.entity.Account;
import com.ordersystem.entity.Order;
import com.ordersystem.entity.OrderSub;
import com.ordersystem.entity.dto.OrderBuyNowDto;
import com.ordersystem.entity.dto.OrderDto;
import com.ordersystem.mapper.OrderMapper;
import com.ordersystem.mapper.OrderSubMapper;
import com.ordersystem.service.GeneralService;
import com.ordersystem.util.SnowFlake;
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

    @Autowired
    OrderSubService orderSubService;

    @Autowired
    AccountService accountService;

    @Autowired
    SnowFlake snowFlake;

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

    public IPage<Order> innitOrder(Page<Order> page, Order order) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(order);
        queryWrapper.orderByDesc("createTime");
        return orderMapper.selectPage(page, queryWrapper);
    }

    /**
     * 直接购买
     *
     * @param orderBuyNowDto
     */
    public ReturnData buyNow(OrderBuyNowDto orderBuyNowDto) {
        // 后续登录处理用户id,不适用查询
        if("".equals(orderBuyNowDto.getUserName())||!Optional.ofNullable(orderBuyNowDto.getUserName()).isPresent()){
            return ReturnData.returnError(1001,"请先登录");
        }
        Account account = accountService.selectOneByWrapper(Account.builder().userName(orderBuyNowDto.getUserName()).build());
        if(!Optional.ofNullable(account).isPresent()){
            return ReturnData.returnError(1002,"用户不存在");
        }
        Order order = Order.builder()
                .orderCode("N" + snowFlake.nextId())
                .accountId(account.getAccountId())
                .price(orderBuyNowDto.getPrice())
                .build();
        save(order);
        OrderSub orderSub = OrderSub.builder()
                .orderSubCode("S" + snowFlake.nextId())
                .orderCode(order.getOrderCode())
                .orderId(order.getOrderId())
                .price(orderBuyNowDto.getPrice())
                .count(orderBuyNowDto.getCount())
                .build();
        orderSubService.save(orderSub);
        return ReturnData.returnData(null);
    }
}
