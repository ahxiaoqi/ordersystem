package com.orderSystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;


/**
 * @author ahxiaoqi
 * @date 2020/1/26 14:01
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_product")
public class Product {

    @TableId(type = IdType.AUTO,value = "productId")
    private Integer productId;

    @TableField(value = "categoryId")
    private Integer categoryId;

    @TableField(value = "subCategoryId")
    private Integer subCategoryId;

    @TableField(value = "productName")
    private String productName;

    @TableField(value = "price")
    private Integer price;

    @TableField(value = "image")
    private String image;

    @TableField(value = "detailImage")
    private String detailImage;

    @TableField(value = "description")
    private String description;

    @TableField(value = "createTime")
    private String createTime;

    @TableField(value = "productTagId")
    private Integer productTagId;

    @TableField(value = "activityId")
    private Integer activityId;

    @TableField(value = "isNew")
    private Integer isNew;

}
