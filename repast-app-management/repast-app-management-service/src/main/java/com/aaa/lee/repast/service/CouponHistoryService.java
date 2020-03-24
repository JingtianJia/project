package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.mapper.CouponHistoryMapper;
import com.aaa.lee.repast.mapper.CouponMapper;
import com.aaa.lee.repast.model.*;
import com.aaa.lee.repast.utils.CurrentTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class CouponHistoryService extends BaseService<CouponHistory> {
    @Autowired
    private CouponHistoryMapper couponHistoryMapper;
    @Autowired
    private CouponMapper couponMapper;


    /**
     * 用户领取优惠券方法
     * @param couponHistory
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Boolean addCouponHistory(CouponHistory couponHistory) {
        //判断是否已经领取过该优惠券
        if (couponHistoryMapper.selectCount(couponHistory) == 0) {
            if (couponMapper.selectNum(couponHistory.getCouponId())>0) {
                synchronized (CouponHistoryService.class){
                    if (couponMapper.selectNum(couponHistory.getCouponId())>0) {
                        if (couponMapper.updateReceiveCountByID(couponHistory.getCouponId())>0) {
                            couponHistory.setCreateTime(CurrentTimeUtil.currentTime());
                            if (add(couponHistory) > 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 用户使用优惠券
     * @param couponHistory
     * @return
     */
    public Boolean updateCouponHistory(CouponHistory couponHistory){
        //使用后将优惠券状态设置为2
        couponHistory.setUseStatus(2);
        Integer update = update(couponHistory);
        if(null!=update&&update>0){
            return true;
        }
        return false;
    }

    /**
     *
     * @Author 贾敬田
     * @Description 更新失效优惠券
     * @Date 0:25 2020/3/17
     * @Param
     * @return
     **/
    public Boolean updateCouponHistoryTime(){
        CouponHistory couponHistory = new CouponHistory();
        Coupon coupon = new Coupon();
        //设置使用状态为0
        couponHistory.setUseStatus(0);
        //选择未使用的优惠券
        List<CouponHistory> couponHistories = queryList(couponHistory);
        if(null!=couponHistories){
            for (CouponHistory c: couponHistories) {
                coupon.setId(c.getCouponId());
                //根据优惠券id找到未使用但是即将过期的优惠券
                coupon = couponMapper.selectOne(coupon);
                if(coupon.getEndTime().getTime()<System.currentTimeMillis()){
                    c.setUseStatus(2);
                    update(c);
                }

            }
            return true;
        }
        return false;
    }

    /**
     * @Author Gotta
     * @Description 根据优惠券历史对象返回用户全部已领取优惠券，必须要有memberId
     * 缺省是查询所有
     * 使用状态：0->未使用；1->已使用；2->已过期
     * @Date 18:24 2020/3/22
     * @Param couponHistory
     * @return java.util.List<com.aaa.lee.repast.model.CouponHistory>
     **/
    public List<Coupon> selectCouponHistoryByMemberId(CouponHistory couponHistory) {
        List<CouponHistory> couponHistoryList = couponHistoryMapper.select(couponHistory);
        if(null != couponHistoryList && couponHistoryList.size()>0) {
            List<Coupon> list = new ArrayList<>();
            for (CouponHistory history : couponHistoryList) {
                list.add(couponMapper.selectOne(new Coupon().setId(history.getCouponId())));
            }
            return list;
        }
        return null;
    }

}
