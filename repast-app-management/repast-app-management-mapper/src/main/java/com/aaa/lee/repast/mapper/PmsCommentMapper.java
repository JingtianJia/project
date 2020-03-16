package com.aaa.lee.repast.mapper;


import com.aaa.lee.repast.model.PmsComment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsCommentMapper extends Mapper<PmsComment> {
    List<PmsComment> selectpmsCommentbyid(long shopid);
    List<PmsComment> selectpmsCommentbyorderid(long orderid);
    int insertcomment(PmsComment pmsComment);
    int deletecomment(PmsComment pmsComment);
}