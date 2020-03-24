package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonCart;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.model.CartItem;
import com.aaa.lee.repast.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.lee.repast.staticstatus.RequestProperties.PRODUCT_ID;
import static com.aaa.lee.repast.staticstatus.RequestProperties.TOKEN;
import static com.aaa.lee.repast.status.StaticCode.MSG;
import static com.aaa.lee.repast.status.StaticCode.RESULT;
/**
 * @Company
 * @Author 杨盼灵
 * @Date 2020/3/24
 * @Description
 **/
@RestController
public class CartController extends CommonController<CartItem> {

    @Autowired
    private CartService cartService;
    @Autowired
    private CommonCart commonCart;

    @Override
    public BaseService<CartItem> getBaseService() {
        return cartService;
    }

    /**
     * 从购物车中删除该商品
     */
    @PostMapping("/deleteProductFromCart")
    public Map<String,Object> deleteProductFromCart(@RequestParam(PRODUCT_ID) Long productId, @RequestParam(TOKEN) String token){
        Boolean ifSuccess = cartService.deleteProductFromCart(productId, token);
        Map<String,Object> map = new HashMap<>();
        if (ifSuccess){
            map.put(MSG,super.delFailed("删除该商品成功"));
        }else{
            map.put(MSG,super.delFailed("删除该商品失败"));
        }
        return map;
    }

    /**
     *  通过token查询该用户的购物车
     */
    @PostMapping("/selectCartByToken")
    public Map<String,Object> selectCartByToken(@RequestParam(TOKEN)String token){
        List<CartItem> cartItems = cartService.selectCartByToken(token);
        Map<String,Object> map = new HashMap<>();
        if (cartItems.size() > 0){
            map.put(MSG,super.operationSuccess("查询成功"));
            map.put(RESULT,cartItems);
        }else {
            map.put(MSG,super.operationFailed("查询失败"));
        }
        return map;
    }

    /**
     * 添加食堂商品到购物车
     */
    @PostMapping("/canteenAddProductToCart")
    public Map<String,Object> canteenAddProductToCart(@RequestParam(TOKEN) String token, @RequestParam(PRODUCT_ID) Long productId) {
        Boolean ifSuccess = cartService.canteenAddProductToCart(token, productId);
        Map<String, Object> map = new HashMap<>();
        if (ifSuccess) {
            map.put(MSG, super.operationSuccess("添加成功"));
        } else {
            map.put(MSG, super.operationFailed("添加失败"));
        }
        return map;
    }

    /**
     * 添加商品到购物车
     **/
    @PostMapping("/addCart")
    public Boolean addCart(@RequestBody Map<String,Object> data, @RequestParam(TOKEN) String token) {
        return cartService.addCart(data,token,commonCart);
    }
}
