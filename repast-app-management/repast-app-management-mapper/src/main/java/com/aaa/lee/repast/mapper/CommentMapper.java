package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Comment;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Company
 * @Author 杨盼灵
 * @Date 2020/3/24
 * @Description
 **/
@Repository
public interface CommentMapper extends Mapper<Comment> {
    /**
     * 通过productId获取评价
     */
    Comment selectCommentById(Long productId);
}
