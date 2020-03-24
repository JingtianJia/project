package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.aaa.lee.repast.staticstatus.RequestProperties.PRODUCT_ID;
import static com.aaa.lee.repast.staticstatus.RequestProperties.TOKEN;

/**
 * @Company
 * @Author 杨盼灵
 * @Date 2020/3/24
 * @Description
 **/
@RestController
@Api(value = "购物车",tags = "购物车的所有接口")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;

    /**
     * 从购物车中删除商品
     */
    @PostMapping("/deleteProductFromCart")
    @ApiOperation(value = "删除",notes = "从购物车中删除商品")
    public Map<String,Object> deleteProductFromCart(@RequestParam(PRODUCT_ID) Long productId, @RequestParam(TOKEN) String token){
        return cartService.deleteProductFromCart(productId,token);
    }

    /**
     * 通过token查询该用户的购物车
     */
    @PostMapping("/selectCartByToken")
    @ApiOperation(value = "查询购物车",notes = "通过token查询该用户的购物车")
    public Map<String,Object> selectCartByToken(@RequestParam(TOKEN)String token){
        return cartService.selectCartByToken(token);
    }

    /**
     *  添加食堂商品到购物车
     */
    @PostMapping("/canteenAddProductToCart")
    @ApiOperation(value = "食堂添加",notes = "添加食堂商品到购物车")
    public Map<String,Object> canteenAddProductToCart(@RequestParam(TOKEN) String token, @RequestParam(PRODUCT_ID) Long productId){
        return cartService.canteenAddProductToCart(token,productId);
    }

    @PostMapping("/addCart")
    @ApiOperation(value = "购物车",notes = "用户添加商品到购物车操作")
    public ResultData addCart(@RequestBody Map<String, Object> data, @RequestParam(TOKEN) String token){
        Boolean ifSuccess = cartService.addCart(data,token);
        if (ifSuccess){
            return super.addFailed("添加成功");
        }else {
            return super.addFailed("添加失败");
        }
    }
}
