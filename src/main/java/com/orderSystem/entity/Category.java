package com.orderSystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author ahxiaoqi
 * @date 2020/1/26 13:41
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_category")
public class Category  {

    @TableId(type = IdType.AUTO,value = "categoryId")
    private Integer categoryId;

    @NotEmpty(message = "用户名不能为空!")
    @TableField(value = "categoryName")
    private String categoryName;

    @TableField(value = "createTime")
    private String createTime;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "orders")
    private Integer orders;

    @TableField(value = "tag")
    private String tag;

    @TableField(value = "description")
    private String description;

    @TableField(value = "image")
    private String image;


}
