package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.OrderItem;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 订单商品详细mapper
 */
public interface OrderItemMapper extends Mapper<OrderItem> {
    //根据SN查询所有的商品明细
    List<OrderItem> selectOrder(String OrderSn);
    //查询所有的SN
    List<OrderItem> selectOrderItemSn();
}