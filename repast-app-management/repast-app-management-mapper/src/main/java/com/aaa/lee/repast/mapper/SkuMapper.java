package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Sku;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SkuMapper extends Mapper<Sku> {
    Sku selectSkuById(Long productId);
}