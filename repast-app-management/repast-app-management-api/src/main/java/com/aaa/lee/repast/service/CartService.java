package com.aaa.lee.repast.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static com.aaa.lee.repast.staticstatus.RequestProperties.PRODUCT_ID;
import static com.aaa.lee.repast.staticstatus.RequestProperties.TOKEN;

@FeignClient(value = "orderinfo-interface",contextId = "cart")
public interface CartService {
    /**
     * 删除订单方法

     */
    @PostMapping("/deleteProductFromCart")
    Map<String,Object> deleteProductFromCart(@RequestParam(PRODUCT_ID) Long productId, @RequestParam(TOKEN) String token);

    @PostMapping("/selectCartByToken")
    Map<String,Object> selectCartByToken(@RequestParam(TOKEN) String token);

    /**
     * 添加食堂商品到购物车
     */
    @PostMapping("/canteenAddProductToCart")
    Map<String,Object> canteenAddProductToCart(@RequestParam(TOKEN) String token, @RequestParam(PRODUCT_ID) Long productId);
    /**
    * //添加商品到购物车
     **/
    @PostMapping("/addCart")
    Boolean addCart(@RequestBody Map<String, Object> data, @RequestParam(TOKEN) String token);
}
