package com.ordersystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ahxiaoqi
 * @date 2020/3/29 23:27
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_order_sub")
public class OrderSub {

    @TableId(type = IdType.AUTO, value = "orderSubId")
    private Integer orderSubId;

    @TableField(value = "orderSubCode")
    private String orderSubCode;

    @TableField(value = "orderId")
    private Integer orderId;

    @TableField(value = "orderCode")
    private String orderCode;

    @TableField(value = "specId")
    private Integer specId;

    @TableField(value = "productId")
    private Integer productId;

    @TableField(value = "count")
    private Integer count;

    @TableField(value = "price")
    private Integer price;

    @TableField(value = "specType")
    private Integer specType;

    @TableField(value = "createTime")
    private String createTime;

}
