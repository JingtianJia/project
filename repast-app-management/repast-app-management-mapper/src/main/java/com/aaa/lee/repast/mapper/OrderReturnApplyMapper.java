package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.OrderReturnApply;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *  订单退货申请mapper
 */
public interface OrderReturnApplyMapper extends Mapper<OrderReturnApply> {
    //根据店铺Id查询店铺下的所有的Sn
    List<OrderReturnApply> selectOrderReturnApplyShopId(Long ShopId);
    //改变申请退款订单的状态（同意订单退款）
    Integer updateOrderReturnApplyByOrderSn(OrderReturnApply orderReturnApply);
    //改变申请退款订单的状态（已成功收到货物）
    Integer OrderReturnApplyByOrderSnByStatus(OrderReturnApply orderReturnApply);
}