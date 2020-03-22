package com.aaa.lee.repast.service;

import com.aaa.lee.repast.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Company
 * @Author Gotta
 * @Date 2020/3/21
 * @Description
 **/
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * @Author Gotta
     * @Description 根据商品id回滚库存数量
     * @Date 10:43 2020/3/21
     * @Param order
     * @return java.lang.Boolean
     **/
    public Boolean rollbackStock(Long id,Integer num){
        if(productMapper.rollbackStock(id,num) != 1){
            return false;
        }
        return true;
    }
}
