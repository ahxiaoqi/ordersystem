package com.ordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ordersystem.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ahxiaoqi
 * @date 2020/2/1 0:26
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select * from t_comment order by createTime desc limit 1,#{size} ")
    List<Comment> innitCommentBox(int size);

    @Select("select * from  t_comment ORDER BY RAND() LIMIT #{num}")
    List<Comment> innitCommentBoxRand(Integer num);
}
