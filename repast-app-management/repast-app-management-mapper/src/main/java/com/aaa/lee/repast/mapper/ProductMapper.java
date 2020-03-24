package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Product;
import tk.mybatis.mapper.common.Mapper;

/**
 *  商品mapper
 */
public interface ProductMapper extends Mapper<Product> {
    //根据店铺id获取对象
    Product selectProductGiftPoint(int id);
    //修改商品的库存
    Integer updateProductStock(Product product);
    Product selectProductById(Long productId);
    //8分钟未开启，加入购物车修改商品表库存的数量
}