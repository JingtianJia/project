package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.SkuStock;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SkuStockMapper extends Mapper<SkuStock> {

    SkuStock selectByProductId(Long productId);

}