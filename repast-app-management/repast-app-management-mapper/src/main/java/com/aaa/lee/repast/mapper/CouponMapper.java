package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Coupon;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CouponMapper extends Mapper<Coupon> {
    List<Coupon>  selectCount();
    Integer selectNum(@Param("id") Long id);
    Integer updateReceiveCountByID(@Param("id") Long id);
    Map selectCouponVoToProduct(@Param("id") Long id);
    Map selectCouponVoToCategory(@Param("id") Long id);
}