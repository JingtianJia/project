package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.CartItem;
import com.aaa.lee.repast.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "下单", tags = "下单")
public class OrderController {
    @Autowired
    private IRepastService iRepastService;
    @GetMapping("/xiaDao")
    @ApiOperation(value = "下单", notes = "下单(token，shopId)")
    ResultData xiaDan(@RequestParam("token") String token, @RequestBody List<CartItem> cartItems){

        return iRepastService.xiaDan(token, cartItems);
    }
}
