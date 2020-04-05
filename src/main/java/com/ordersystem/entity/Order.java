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
 * @date 2020/1/26 13:57
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_order")
public class Order {

    @TableId(type = IdType.AUTO,value = "orderId")
    private Integer orderId;

    @TableField(value = "orderCode")
    private String orderCode;

    @TableField(value = "accountId")
    private Integer accountId;

    @TableField(value = "price")
    private Integer price;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "createTime")
    private String createTime;

}
