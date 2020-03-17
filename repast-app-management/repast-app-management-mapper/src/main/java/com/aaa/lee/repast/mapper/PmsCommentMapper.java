package com.aaa.lee.repast.mapper;


import com.aaa.lee.repast.model.PmsComment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsCommentMapper extends Mapper<PmsComment> {
    List<PmsComment> selectPmsCommentById(long shopId);
    List<PmsComment> selectPmsCommentByOrderId(long orderId);
    int insertComment(PmsComment pmsComment);
    int deleteComment(PmsComment pmsComment);
}