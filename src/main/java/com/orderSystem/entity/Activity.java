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
 * @date 2020/1/31 22:41
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_activity")
public class Activity {

    @TableId(type = IdType.AUTO,value = "activityId")
    private Integer activityId;

    @NotEmpty(message = "活动文字不能为空!")
    @TableField(value = "activityStr")
    private String activityStr;

    @TableField(value = "description")
    private String description;

    @TableField(value = "type")
    private Integer type;

    @TableField(value = "title")
    private String title;

    @TableField(value = "priceParam1")
    private String priceParam1;

    @TableField(value = "priceParam2")
    private String priceParam2;

    @TableField(value = "href")
    private String href;

    @TableField(value = "hrefStr")
    private String hrefStr;

    @TableField(value = "image")
    private String image;

    @TableField(value = "activityType")
    private String activityType;
}
