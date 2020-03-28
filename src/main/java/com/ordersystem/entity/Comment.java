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
 * @date 2020/2/1 0:23
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_comment")
public class Comment {

    @TableId(type = IdType.AUTO,value = "commentId")
    private Integer commentId;

    @TableField(value = "title")
    private String title;

    @TableField(value = "detail")
    private String detail;

    @TableField(value = "image")
    private String image;

    @TableField(value = "month")
    private String month;

    @TableField(value = "day")
    private String day;

    @TableField(value = "createTime")
    private String createTime;

    @TableField(value = "orderId")
    private Integer orderId;

    @TableField(value = "productId")
    private Integer productId;
}
