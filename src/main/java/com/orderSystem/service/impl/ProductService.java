package com.orderSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderSystem.entity.Category;
import com.orderSystem.entity.Comment;
import com.orderSystem.entity.Product;
import com.orderSystem.entity.dto.CommentDto;
import com.orderSystem.entity.dto.ProductDto;
import com.orderSystem.mapper.ProductMapper;
import com.orderSystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/1/29 13:21
 */
@Service
public class ProductService  implements GeneralService<Product> {

    @Autowired
    @SuppressWarnings("all")
    ProductMapper productMapper;

    @Override
    public Product selectOneByWrapper(Product product) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(product);
        return productMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Product> selectListByWrapper(Product product) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(product);
        return productMapper.selectList(queryWrapper);
    }

    @Override
    public void save(Product product) {
        if (Optional.ofNullable(product.getProductId()).isPresent()) {
            productMapper.updateById(product);
        } else {
            productMapper.insert(product);
        }

    }

    @Override
    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }

    public List<ProductDto> selectHotProduct(int num) {
        return productMapper.selectHotProduct(num);
    }

    public  IPage<ProductDto> initNewProduct(Page<Product> page) {
        return productMapper.innitProductList(page,new Product());
    }

    public List<ProductDto> innitNewCommentBox(Integer size) {
        return productMapper.innitNewCommentBox(size);
    }

    public List<CommentDto> innitFooterGoods(Integer start, Integer end) {
        return productMapper.innitFooterGoods(start,end);
    }

    public IPage<ProductDto> innitProductListBox(Product product, Page<Product> page) {
        return productMapper.innitProductList(page,product);
    }

    public ProductDto innitProductDetail(Integer productId) {
        return productMapper.innitProductDetail(productId);
    }

}
