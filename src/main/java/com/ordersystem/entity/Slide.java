package com.ordersystem.entity;

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
 * @date 2020/1/31 19:28
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_slide")
public class Slide {

    @TableId(type = IdType.AUTO, value = "slideId")
    private Integer slideId;

    @NotEmpty(message = "主标题不能为空!")
    @TableField(value = "mainTitle")
    private String mainTitle;

    @NotEmpty(message = "副标题不能为空!")
    @TableField(value = "doubleTitle")
    private String doubleTitle;

    @NotEmpty(message = "描述不能为空!")
    @TableField(value = "description")
    private String description;

    @NotEmpty(message = "链接不能为空!")
    @TableField(value = "href")
    private String href;

    @NotEmpty(message = "链接文字不能为空!")
    @TableField(value = "hrefStr")
    private String hrefStr;

    @TableField(value = "image")
    private String image;

    @TableField(value = "slideOrder")
    private Integer slideOrder;

    @TableField(value = "status")
    private Integer status;

}
