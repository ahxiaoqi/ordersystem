package com.ordersystem.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ahxiaoqi
 * @date 2020/4/6 2:22
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderBuyNowDto {
    private Integer orderId;

    private String orderCode;

    private Integer accountId;

    private Integer price;

    private Integer status;

    private Integer count;

    private Integer specId;

    private Integer specType;

    private String createTime;

    private String userName;
}
