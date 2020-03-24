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

}
