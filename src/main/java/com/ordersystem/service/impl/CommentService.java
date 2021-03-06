package com.ordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ordersystem.entity.Comment;
import com.ordersystem.mapper.CommentMapper;
import com.ordersystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/2/1 0:26
 */
@Service
public class CommentService implements GeneralService<Comment> {

    @Autowired
    @SuppressWarnings("all")
    CommentMapper commentMapper;

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
}
