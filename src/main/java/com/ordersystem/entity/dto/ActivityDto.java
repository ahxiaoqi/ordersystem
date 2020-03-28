package com.ordersystem.entity.dto;

import lombok.Data;

/**
 * @author ahxiaoqi
 * @date 2020/2/1 14:02
 */
@Data
public class ActivityDto {

    private Integer activityId;

    private Integer productId;

    private Integer priceParam1;

    private Integer priceParam2;

    private String image;

    private String description;

    private String productName;

    private String activityStr;
}
