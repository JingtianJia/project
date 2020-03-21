package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.model.OrderReturnReason;
import org.springframework.stereotype.Service;

import static com.aaa.lee.repast.staticstatus.StaticCode.ZERO;

/**
 * @Author 丁平达
 * @Date 2020/3/20 17:54
 */
@Service
public class OrderReturnReasonService extends BaseService<OrderReturnReason> {

    /**
     * 新增退货原因数据
     * @param orderReturnReason
     * @return
     */
    public Boolean insertOrderReturnReason(OrderReturnReason orderReturnReason){
        if (null!=orderReturnReason){
            Integer add = super.add(orderReturnReason);
        if (add>ZERO){
            return true;
        }
        return false;
        }
        return false;
    }
}
