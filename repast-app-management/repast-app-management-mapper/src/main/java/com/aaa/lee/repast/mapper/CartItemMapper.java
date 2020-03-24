package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.CartItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import static com.aaa.lee.repast.status.StaticCode.*;

@Repository
public interface CartItemMapper extends Mapper<CartItem> {
    /**
     *      通过productId和memberId删除购物车该商品
     */
    Integer deleteByProductIdAndMemberId(@Param("productId") Long productId, @Param("memberId") Long memberId);

    /**
     * 通过token查询该用户的购物车信息
     */
    List<CartItem> selectCartByMemberId(Long memberId);
    /*新增商品到购物车*/
    Integer addCartPro(CartItem cartItem);

    /*加入购物车前查询用户购物车信息*/
    CartItem selectMemberCart(CartItem cartItem);

    /**
     * 修改购物车 和库存
     */
    Integer updateCart(CartItem cartItem);
    /**
     * 删除购物车
     */
    Integer deleteCart(CartItem cartItem);

    /**
     * 通过会员id和商品id查询购物车信息
     */
    CartItem selectCartByMemberIdAndProductId(@Param(MEMBER_ID) Long memberId,@Param(PRODUCT_ID) Long productId);

}