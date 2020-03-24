package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.mapper.CouponProductRelationMapper;
import com.aaa.lee.repast.model.Coupon;
import com.aaa.lee.repast.model.CouponProductRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Company
 * @Author Gotta
 * @Date 2020/3/22
 * @Description
 **/
@Service
public class CouponProductRelationService extends BaseService<CouponProductRelation> {
    @Autowired
    private CouponProductRelationMapper couponProductRelationMapper;

}
