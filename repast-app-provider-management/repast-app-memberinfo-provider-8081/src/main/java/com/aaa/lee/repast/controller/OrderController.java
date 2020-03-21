package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Order;
//import com.aaa.lee.repast.service.OrderService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @Company
 * @Author Gotta
 * @Date 2020/3/18
 * @Description
 **/
public class OrderController /*extends CommonController<Order>*/ {
/*    @Autowired
    private OrderService orderService;
    @Override
    public BaseService<Order> getBaseService() {
        return orderService;
    }*/

    @PostMapping("/saveOrder")
    public ResultData saveOrder(@RequestBody Map map){
        return null;
        //TODO
    }
}
