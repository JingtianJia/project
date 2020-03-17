package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.mapper.CouponMapper;
import com.aaa.lee.repast.model.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService extends BaseService<Coupon> {
    @Autowired
    private CouponMapper couponMapper;

    public List<Coupon> selectCoupon() {
        return couponMapper.selectCount();

    }
}
