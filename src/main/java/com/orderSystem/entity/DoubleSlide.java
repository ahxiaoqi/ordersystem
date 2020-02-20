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
 * @date 2020/1/31 19:28
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_double_slide")
public class DoubleSlide {

    @TableId(type = IdType.AUTO,value = "doubleSlideId")
    private Integer doubleSlideId;

    @NotEmpty(message = "主标题不能为空!")
    @TableField(value = "title")
    private String title;

    @NotEmpty(message = "详情不能为空!")
    @TableField(value = "detail")
    private String detail;

    @NotEmpty(message = "图片地址不能为空!")
    @TableField(value = "image")
    private String image;

    @NotEmpty(message = "链接不能为空!")
    @TableField(value = "href")
    private String href;

    @NotEmpty(message = "链接文字不能为空!")
    @TableField(value = "hrefStr")
    private String hrefStr;

    @TableField(value = "status")
    private Integer status;

}
