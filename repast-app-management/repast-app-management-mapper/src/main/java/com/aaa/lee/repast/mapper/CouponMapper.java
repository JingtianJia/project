package com.aaa.lee.repast.mapper;

import com.aaa.lee.repast.model.Coupon;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CouponMapper extends Mapper<Coupon> {
    List<Coupon>  selectCount();
    Integer selectNum(@Param("id") Long id);
    Integer updateReceiveCountByID(@Param("id") Long id);
}