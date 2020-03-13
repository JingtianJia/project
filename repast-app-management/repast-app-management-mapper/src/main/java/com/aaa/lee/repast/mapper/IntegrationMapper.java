package com.aaa.lee.repast.mapper;


import com.aaa.lee.repast.model.Integration;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IntegrationMapper extends Mapper<Integration> {
    List<Integration> selectIntegrationByMemberId(@Param("memberId") String memberId);
}