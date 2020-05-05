package com.ordersystem.entity.dto;

import com.ordersystem.entity.Comment;
import lombok.Data;

/**
 * @author ahxiaoqi
 * @date 2020/5/6 1:20
 */
@Data
public class CommentAddDto extends Comment {
    private Integer orderSubId;
}
