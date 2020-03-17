package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.CouponHistory;
import com.aaa.lee.repast.service.CouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CouponHistoryController extends CommonController<CouponHistory> {
    @Autowired
    private CouponHistoryService couponHistoryService;
    @Override
    public BaseService<CouponHistory> getBaseService() {
        return couponHistoryService;
    }

    /**
    * @Author 贾敬田
    * @Description 查询我的优惠券通用方法
    * @Date 0:37 2020/3/17
    * @Param [couponHistory]
    * @return com.aaa.lee.repast.base.ResultData
    **/
    @PostMapping("/selcetCouponHistory")
    public ResultData selcetCouponHistoty(Map couponHistory){
        return selcet(couponHistory);
    }

    /**
    * @Author 贾敬田
    * @Description 领取优惠券
    * @Date 0:37 2020/3/17
    * @Param [couponHistory]
    * @return com.aaa.lee.repast.base.ResultData
    **/
    @PostMapping("/addCouponHistory")
    public ResultData addCouponHistoty(CouponHistory couponHistory){
        Boolean aBoolean = couponHistoryService.addCouponHistory(couponHistory);
        if(aBoolean==true){
            return super.operationSuccess();
        }else {
            return super.operationFailed();
        }

    }

    /**
    * @Author 贾敬田
    * @Description 使用优惠券
    * @Date 0:39 2020/3/17
    * @Param [couponHistory]
    * @return com.aaa.lee.repast.base.ResultData
    **/
    @PostMapping("/updateCouponHistory")
    public ResultData updateCouponHistoty(CouponHistory couponHistory){
        Boolean aBoolean = couponHistoryService.updateCouponHistory(couponHistory);
        if(aBoolean==true){
            return super.operationSuccess();
        }else {
            return super.operationFailed();
        }
    }
}