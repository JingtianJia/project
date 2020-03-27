package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.CartItem;
import com.aaa.lee.repast.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author Gotta
 * @Date 2020/3/18
 * @Description
 **/
public class OrderController /*extends CommonController<Order>*/ {
    @Autowired
    private OrderService orderService;
/*    @Override
    public BaseService<Order> getBaseService() {
        return orderService;
    }*/

    @PostMapping("/saveOrder")
    public ResultData saveOrder(@RequestBody Map map){
        return null;
        //TODO
    }
    @PostMapping("/xiaDan")
    public ResultData xiaDan(@RequestParam("token") String token, @RequestBody List<CartItem> cartItems){
        return orderService.insertOrder(token, cartItems);

    }
}
