package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Product;
import tk.mybatis.mapper.common.Mapper;

/**
 *  商品mapper
 */
public interface ProductMapper extends Mapper<Product> {
    //根据店铺id获取对象
    Product selectProductGiftPoint(int shopId);
}