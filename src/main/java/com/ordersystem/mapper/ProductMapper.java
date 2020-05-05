package com.ordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.entity.Product;
import com.ordersystem.entity.dto.CommentDto;
import com.ordersystem.entity.dto.ProductDto;
import com.ordersystem.entity.dto.productCarDto;
import com.ordersystem.provider.productMapperProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author ahxiaoqi
 * @date 2020/1/26 14:04
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Select("SELECT a.*,b.activityStr,b.activityType,c.productTagStr FROM t_product a  LEFT JOIN `t_activity` b ON\n" +
            "a.`activityId` = b.activityId LEFT JOIN  t_product_tag c ON a.`productTagId` = c.`productTagId` ORDER BY RAND() LIMIT #{num}")
    List<ProductDto> selectHotProduct(int num);

    @Select("SELECT b.*,a.detail AS commentDetail,a.month,a.year,a.day FROM t_comment a LEFT JOIN t_product b ON \n" +
            "a.productId = b.productId ORDER BY a.commentId LIMIT #{size}")
    List<ProductDto> innitNewCommentBox(Integer size);

    @Select("select a.price,a.image,a.description,b.year,b.month,b.day from t_product a left join t_comment b on a.productId = b.productId limit #{start},#{end}")
    List<CommentDto> innitFooterGoods(@Param("start")int start,@Param("end")int end);

    @Results({
            @Result(column = "categoryId", property = "categoryId"),
            @Result(property = "categoryName", column = "categoryId", one = @One(select = "com.ordersystem.mapper.CategoryMapper.selectCategoryName"),jdbcType = JdbcType.VARCHAR),
            @Result(column = "subCategoryId", property = "subCategoryId"),
            @Result(property = "subCategoryName", column = "subCategoryId", one = @One(select = "com.ordersystem.mapper.SubCategoryMapper.selectSubCategoryName"),jdbcType = JdbcType.VARCHAR),
    })
    @SelectProvider(value = productMapperProvider.class,method = "innitProductListBox")
    IPage<ProductDto> innitProductList(Page<Product> page, Product product);

    @Select("select * from t_product  where productId = #{productId}")
    @Results({
            @Result(column = "productId", property = "productId"),
            @Result(property = "specList", column = "productId", many = @Many(select = "com.ordersystem.mapper.SpecMapper.selectSpecByProductId", fetchType = FetchType.EAGER))
    })
    ProductDto innitProductDetail(Integer productId);

    @Select("SELECT a.productId,a.productName,b.specId,b.specTitle,a.image FROM `t_product` a LEFT JOIN `t_spec` b ON " +
            "a.`productId` = b.`productId` WHERE b.productId = #{productId} AND b.`specId` = #{specId}")
    productCarDto getCarProductDetail(productCarDto carDto);
}
