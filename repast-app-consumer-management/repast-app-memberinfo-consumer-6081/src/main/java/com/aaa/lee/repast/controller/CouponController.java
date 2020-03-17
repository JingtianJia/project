package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.CouponHistory;
import com.aaa.lee.repast.service.IRepastService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company
 * @Author Gotta
 * @Date 2020/3/17
 * @Description
 **/
@RestController
@Api(value = "优惠券服务", tags = "优惠券服务接口(提供优惠券有关操作)")
public class CouponController extends BaseController {
    @Autowired
    private IRepastService repastService;
    /**
     * @Author 贾敬田
     * @Description 查询可用优惠券
     * @Date 0:43 2020/3/17
     * @Param []
     * @return com.aaa.lee.repast.base.ResultData
     **/
    @PostMapping("/selectCoupon")
    ResultData selectCoupon(){
        return repastService.selectCoupon();
    }

    /**
     * @Author 贾敬田
     * @Description 查询我领取过的所有优惠券
     * @Date 0:44 2020/3/17
     * @Param [couponHistory]
     * @return com.aaa.lee.repast.base.ResultData
     **/
    @PostMapping("/selcetCouponHistory")
    ResultData selcetCouponHistoty(CouponHistory couponHistory){
        return repastService.selcetCouponHistoty(couponHistory);
    }

    /**
     * @Author 贾敬田
     * @Description 领取优惠券
     * @Date 0:45 2020/3/17
     * @Param [couponHistory]
     * @return com.aaa.lee.repast.base.ResultData
     **/
    @PostMapping("/addCouponHistory")
    ResultData addCouponHistoty(CouponHistory couponHistory){
        return repastService.addCouponHistoty(couponHistory);
    }
}
