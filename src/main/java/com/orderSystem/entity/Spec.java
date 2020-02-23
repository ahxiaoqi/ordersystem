package com.orderSystem.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (TSpec)表实体类
 *
 * @author makejava
 * @since 2020-02-23 22:17:02
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_spec")
public class Spec{
    @TableId(type = IdType.AUTO, value = "specId")
    private Integer specId;

    //逗号,分隔价格,暂时按大中小三个分量存储
    @TableField(value = "subCategoryName")
    private String price;

    @TableField(value = "specTitle")
    private String specTitle;

    //描述
    @TableField(value = "description")
    private String description;

    //规格图
    @TableField(value = "image")
    private String image;

    //产品id
    @TableField(value = "productId")
    private Integer productId;

    @TableField(value = "createTime")
    private Date createTime;


}