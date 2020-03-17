package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Coupon;
import com.aaa.lee.repast.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CouponController extends CommonController<Coupon> {
    @Autowired
    private CouponService couponService;
    @Override
    public BaseService<Coupon> getBaseService() {
        return couponService;
    }

    /**
     * 查询在可领取时间之前的全部优惠券
     * @return
     * todo 以后传参可以查询店铺优惠券或商品优惠券
     */
    @PostMapping("/selectCoupon")
    public ResultData selectCoupon(@RequestBody Map map){
        return operationSuccess(couponService.selectCoupon());
    }
}
