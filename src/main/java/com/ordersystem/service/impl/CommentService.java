package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ordersystem.data.ReturnData;
import com.ordersystem.entity.*;
import com.ordersystem.entity.dto.CommentAddDto;
import com.ordersystem.mapper.CommentMapper;
import com.ordersystem.mapper.OrderMapper;
import com.ordersystem.mapper.OrderSubMapper;
import com.ordersystem.mapper.ProductMapper;
import com.ordersystem.service.GeneralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/2/1 0:26
 */
@Service
public class CommentService implements GeneralService<Comment> {

    Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    @SuppressWarnings("all")
    CommentMapper commentMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderSubMapper orderSubMapper;

    @Resource
    ProductMapper productMapper;

    @Override
    public Comment selectOneByWrapper(Comment comment) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(comment);
        return commentMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Comment> selectListByWrapper(Comment comment) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(comment);
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public void save(Comment comment) {
        if (Optional.ofNullable(comment.getCommentId()).isPresent()) {
            commentMapper.updateById(comment);
        } else {
            commentMapper.insert(comment);
        }

    }

    @Override
    public void deleteById(Integer id) {
        commentMapper.deleteById(id);
    }

    public List<Comment> innitCommentBox(int size) {
        return commentMapper.innitCommentBox(size);
    }

    public List<Comment> innitCommentBoxRand(Integer num) {
        return commentMapper.innitCommentBoxRand(num);
    }

    public ReturnData productComment(CommentAddDto comment, HttpSession session) {
        try {
            Account account = (Account) session.getAttribute("account");
            OrderSub orderSub = orderSubMapper.selectById(comment.getOrderSubId());
            orderSub.setIsComment(1);
            orderSubMapper.updateById(orderSub);
            Product product = productMapper.selectById(comment.getProductId());
            LocalDateTime now = LocalDateTime.now();
            commentMapper.insert(Comment.builder()
                    .title(account.getUserName() + "购买" + product.getProductName() + "的评论")
                    .detail(comment.getDetail())
                    .orderId(comment.getOrderId())
                    .image(product.getImage())
                    .day(now.getDayOfMonth())
                    .month(now.getMonth().getValue())
                    .productId(comment.getProductId())
                    .year(now.getYear())
                    .build());
        } catch (Exception e) {
            logger.info("评论出错{}", e.getMessage());
            return ReturnData.returnError(1001, "评论出错");
        }
        return ReturnData.returnSuccess(null);
    }
}
