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
@TableName("t_subcategory")
public class SubCategory {

    @TableId(type = IdType.AUTO,value = "subCategoryId")
    private Integer subCategoryId;

    @NotEmpty(message = "子分类名称不能为空!")
    @TableField(value = "subCategoryName")
    private String subCategoryName;

    @TableField(value = "createTime")
    private String createTime;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "image")
    private String image;

    @TableField(value = "categoryId")
    private Integer categoryId;

}
