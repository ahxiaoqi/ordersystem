package com.ordersystem.entity.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @author ahxiaoqi
 * @date 2020/3/29 23:25
 */
@Data
public class CarOrderDto implements Serializable {
    private Integer orderId;

    private String orderCode;

    private Integer accountId;

    private Integer price;

    private Integer status;

    private String createTime;

    private Integer isComment;

    private Integer tel;

    private String address;

    private String orderSub;
}
