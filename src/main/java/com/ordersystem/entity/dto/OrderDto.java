package com.ordersystem.entity.dto;


import com.ordersystem.entity.OrderSub;
import lombok.Data;

import java.util.List;

/**
 * @author ahxiaoqi
 * @date 2020/3/29 23:25
 */
@Data
public class OrderDto {
    private Integer orderId;

    private String orderCode;

    private Integer accountId;

    private Integer price;

    private Integer status;

    private String createTime;

    private List<OrderSub> orderSub;
}
