package com.ordersystem.entity.dto;

import com.ordersystem.entity.OrderSub;
import com.ordersystem.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ahxiaoqi
 * @date 2020/5/6 0:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderSubDto extends OrderSub {

    private Product product;

}
