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
    
    /**
    * @Author Gotta
    * @Description 根据id查询优惠券
    * @Date 21:59 2020/3/22 
    * @Param coupon
    * @return com.aaa.lee.repast.model.Coupon
    **/
    public Coupon selectById(Coupon coupon){
        return couponMapper.selectOne(coupon);
    }

    /**
    * @Author Gotta
    * @Description 查询全部可领取优惠券
    * @Date 20:47 2020/3/22
    * @Param
    * @return java.util.List<com.aaa.lee.repast.model.Coupon>
    **/
    public List<Coupon> selectCoupon() {
        return couponMapper.selectCount();

    }

    /**
    * @Author Gotta
    * @Description 通过优惠券id查询优惠券是否可领取
    * @Date 20:45 2020/3/22
    * @Param id
    * @return boolean
    **/
    public Boolean selectNum(Long id){
        if (couponMapper.selectNum(id) > 0) {
            return true;
        }
        return false;
    }

    /**
    * @Author Gotta
    * @Description 通过id更新优惠券剩余数量
    * @Date 20:50 2020/3/22
    * @Param id
    * @return java.lang.Boolean
    **/
    public Boolean updateReceiveCountByID(Long id) {
        if (couponMapper.updateReceiveCountByID(id) > 0) {
            return true;
        }
        return false;
    }

}
