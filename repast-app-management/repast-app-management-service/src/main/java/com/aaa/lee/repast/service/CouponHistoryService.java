package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.mapper.CouponHistoryMapper;
import com.aaa.lee.repast.mapper.CouponMapper;
import com.aaa.lee.repast.model.*;
import com.aaa.lee.repast.utils.CurrentTimeUtil;
import com.aaa.lee.repast.utils.Map2BeanUtil;
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
     * 设置事务的隔离级别为可重复读
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Boolean addCouponHistory(CouponHistory couponHistory) {
        //判断是否超出限领数量
         if (couponHistoryMapper.selectCount(couponHistory) <= couponMapper.selectOne(new Coupon().setId(couponHistory.getCouponId())).getPerLimit()) {
            synchronized (CouponHistoryService.class){
                //mysql的行级锁 FOR UPDATE
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
     * @Description 根据优惠券历史对象返回用户全部已领取优惠券+优惠券使用场景
     * 必须要有memberId
     * 缺省是查询所有
     * 使用状态：0->未使用；1->已使用；2->已过期
     * @Date 18:24 2020/3/22
     * @Param couponHistory
     * @return java.util.List<com.aaa.lee.repast.model.CouponHistory>
     **/
    public List<Coupon> selectCouponHistoryByMemberId(CouponHistory couponHistory) {
        //如果查询未使用的优惠券，前端传过来的数据中使用状态是0
        List<CouponHistory> couponHistoryList = couponHistoryMapper.select(couponHistory);
        List<Coupon> couponList = new ArrayList<>();
        //根据已领取的优惠券拿到用户所有优惠券，为了拿到优惠券使用类别
        Coupon coupon = new Coupon();
        for (CouponHistory history : couponHistoryList) {
            coupon = couponMapper.selectOne(coupon.setId(history.getCouponId()));
            Integer useType = coupon.getUseType();
            Long id = coupon.getId();
            if (useType == 1) {
                coupon = Map2BeanUtil.map2Bean(couponMapper.selectCouponVoToProduct(id),Coupon.class);
            } else if (useType == 2) {
                coupon = Map2BeanUtil.map2Bean(couponMapper.selectCouponVoToProduct(id),Coupon.class);
            }
            couponList.add(coupon);
        }
        return couponList;
    }
}
