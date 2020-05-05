package com.ordersystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.data.ReturnData;
import com.ordersystem.entity.Account;
import com.ordersystem.entity.Order;
import com.ordersystem.entity.OrderSub;
import com.ordersystem.entity.dto.CarOrderDto;
import com.ordersystem.entity.dto.OrderBuyNowDto;
import com.ordersystem.entity.dto.OrderDto;
import com.ordersystem.entity.dto.OrderSubDto;
import com.ordersystem.mapper.OrderMapper;
import com.ordersystem.mapper.OrderSubMapper;
import com.ordersystem.service.GeneralService;
import com.ordersystem.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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
    @Transactional
    public ReturnData buyNow(OrderBuyNowDto orderBuyNowDto, HttpSession session) {
        // 后续登录处理用户id,不适用查询

        if ("".equals(orderBuyNowDto.getUserName()) || !Optional.ofNullable(orderBuyNowDto.getUserName()).isPresent()
                || !Optional.ofNullable(session.getAttribute("account")).isPresent()) {
            return ReturnData.returnError(1001, "请先登录");
        }
        Account account = accountService.selectOneByWrapper(Account.builder().userName(orderBuyNowDto.getUserName()).build());
        if (!Optional.ofNullable(account).isPresent()) {
            return ReturnData.returnError(1002, "用户不存在");
        }
        Order order = Order.builder()
                .orderCode("N" + snowFlake.nextId())
                .accountId(account.getAccountId())
                .price(orderBuyNowDto.getPrice())
                .tel(orderBuyNowDto.getTel())
                .address(orderBuyNowDto.getAddress())
                .build();
        save(order);
        OrderSub orderSub = OrderSub.builder()
                .orderSubCode("S" + snowFlake.nextId())
                .orderCode(order.getOrderCode())
                .orderId(order.getOrderId())
                .productId(orderBuyNowDto.getProductId())
                .specId(orderBuyNowDto.getSpecId())
                .price(orderBuyNowDto.getPrice())
                .specType(orderBuyNowDto.getSpecType())
                .count(orderBuyNowDto.getCount())
                .build();
        orderSubService.save(orderSub);
        return ReturnData.returnData(null);
    }

    public IPage<OrderSubDto> innitMyOrderList(OrderDto orderDto, Page<OrderDto> page) {
        return orderMapper.innitMyOrderList(orderDto, page);
    }

    @Transactional
    public ReturnData carBuy(CarOrderDto orderDto, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        Order order = Order.builder()
                .orderCode("N" + snowFlake.nextId())
                .accountId(account.getAccountId())
                .price(orderDto.getPrice())
                .tel(orderDto.getTel())
                .address(orderDto.getAddress())
                .build();
        save(order);

        JSONArray array = JSON.parseArray(orderDto.getOrderSub());
        array.forEach(i->{
            OrderSub tmp = JSONObject.toJavaObject((JSON) i, OrderSub.class);
            OrderSub orderSub = OrderSub.builder()
                .orderSubCode("S" + snowFlake.nextId())
                .orderCode(order.getOrderCode())
                .orderId(order.getOrderId())
                .productId(tmp.getProductId())
                .specId(tmp.getSpecId())
                .price(tmp.getPrice())
                .specType(tmp.getSpecType())
                .count(tmp.getCount())
                .build();
        orderSubService.save(orderSub);
        });
        return ReturnData.returnData(null);
    }
}
