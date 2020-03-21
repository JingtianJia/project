package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Product;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 *  商品mapper
 */
public interface ProductMapper extends Mapper<Product> {
    //根据店铺id获取对象
    Product selectProductGiftPoint(int id);
    //修改商品的库存
    Integer updateProductStock(Product product);
    //根据商品id和数量回滚库存
    Integer rollbackStock(@Param("id") Long id, @Param("num") Integer num);
}