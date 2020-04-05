package com.ordersystem.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.entity.Product;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/2/3 19:03
 */
public class productMapperProvider {
    Logger logger = LoggerFactory.getLogger(productMapperProvider.class);

    public String innitProductListBox(Page<Product> page, Product product) {
        String sql = new SQL() {{
            SELECT("a.*,b.activityStr,b.activityType,c.productTagStr");
            FROM("t_product a");
            LEFT_OUTER_JOIN("t_activity b ON a.`activityId` = b.`activityId` ");
            LEFT_OUTER_JOIN("t_product_tag c ON a.productTagId = c.productTagId");
            if (Optional.ofNullable(product.getCategoryId()).isPresent()) {
                if (product.getCategoryId() > 0) {
                    WHERE("a.categoryId = #{product.categoryId}");
                }
            }
            if (Optional.ofNullable(product.getSubCategoryId()).isPresent()) {
                if (product.getSubCategoryId() > 0) {
                    WHERE("a.subCategoryId = #{product.subCategoryId}");
                }
            }
            if (Optional.ofNullable(product.getProductName()).isPresent() && !"".equals(product.getProductName())) {
                WHERE("productName like concat('%',#{product.productName},'%')");
            }
            ORDER_BY("a.createTime asc");
        }}.toString();
//        logger.info(sql);
        return sql;
    }

}
