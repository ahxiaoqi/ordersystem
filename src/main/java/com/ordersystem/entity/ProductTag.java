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
 * @date 2020/3/28 22:12
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_product_tag")
public class ProductTag {

    @TableId(type = IdType.AUTO, value = "productTagId")
    private Integer productTagId;

    @TableField(value = "productTagStr")
    private Integer productTagStr;
}
