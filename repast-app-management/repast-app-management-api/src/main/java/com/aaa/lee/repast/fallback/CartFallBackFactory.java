package com.aaa.lee.repast.fallback;

import com.aaa.lee.repast.service.CartService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Company
 * @Author 杨盼灵
 * @Date 2020/3/24
 * @Description
 **/
@Component
public class CartFallBackFactory implements FallbackFactory<CartService> {
    @Override
    public CartService create(Throwable throwable) {
        CartService cartService =new CartService() {
            @Override
            public Map<String, Object> deleteProductFromCart(Long productId, String token) {
                System.out.println("熔断清空购物车的方法！");
                return null;
            }

            @Override
            public Map<String, Object> selectCartByToken(String token) {
                System.out.println("熔断查询购物车的方法！");
                return null;
            }

            @Override
            public Map<String, Object> canteenAddProductToCart(String token, Long productId) {
                System.out.println("熔断添加食堂商品到购物车");
                return null;
            }

            @Override
            public Boolean addCart(Map<String, Object> data, String token) {
                System.out.println("熔断添加商品到购物车的方法！");
                return null;
            }
        };
        return null;
    }
}
