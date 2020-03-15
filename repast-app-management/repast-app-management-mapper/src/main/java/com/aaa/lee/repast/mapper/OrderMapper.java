package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Order;
import tk.mybatis.mapper.common.Mapper;

/**
 *  订单mapper
 */
public interface OrderMapper extends Mapper<Order> {
    //根据店铺Id查询店铺信息
    Order SelectOrderByPayAmount(int order);
}