package com.ordersystem.entity.dto;

import com.ordersystem.entity.Product;
import lombok.Data;

/**
 * @author ahxiaoqi
 * @date 2020/2/1 14:02
 */
@Data
public class ActivityDto {

    private Integer activityId;

    private Integer productId;

    private Integer type;

    private Integer priceParam1;

    private Integer priceParam2;

    private String image;

    private String description;

    private String productName;

    private String activityStr;

    private String href;

    private String hrefStr;

    private String title;

    private Integer activityType;

    private Integer status;

    private ProductDto product;
}
