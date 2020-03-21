package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.ProductCategory;
import tk.mybatis.mapper.common.Mapper;

/**
 *  产品类别mapper
 */
public interface ProductCategoryMapper extends Mapper<ProductCategory> {
    //根据id查询产品类别
    ProductCategory selectProductCategoryById(Long id);
}