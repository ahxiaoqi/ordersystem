package com.orderSystem.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.orderSystem.entity.Category;
import com.orderSystem.entity.Spec;
import com.orderSystem.entity.SubCategory;
import lombok.Data;

import java.util.List;

/**
 * @author ahxiaoqi
 * @date 2020/2/3 16:12
 */
@Data
public class ProductDto {

    private Integer productId;

    private Integer categoryId;

    private Integer subCategoryId;

    private String productName;

    private Integer price;

    private String image;

    private String detailImage;

    private String description;

    private String createTime;

    private Integer productTagId;

    private Integer activityId;

    private Integer isNew;

    private String productTagStr;

    private String activityStr;

    private String month;

    private String year;

    private String day;

    private String commentDetail;

    private Integer activityType;

    private List<Spec> specList;

    private String categoryName;

    private String subCategoryName;
}
