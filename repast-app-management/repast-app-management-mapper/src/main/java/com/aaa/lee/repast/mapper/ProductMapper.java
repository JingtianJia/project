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
    //通过id查询商品
    Product selectProductById(Long productId);

    //更新对应商品的库存，在原来的基础上加
    Product updateStockById (Product product);
}