package com.aaa.lee.repast.util;

import com.aaa.lee.repast.service.CouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务执行类
 */
@EnableScheduling
@Configuration
public class TimeUtil {
    @Autowired
    private CouponHistoryService couponHistoryService;
    @Scheduled(cron="0 0 23 * * ?")
    public void taskCouponHistory(){
        Boolean aBoolean = couponHistoryService.updateCouponHistoryTime();
        if(aBoolean==false){
            couponHistoryService.updateCouponHistoryTime();
        }
    }

}
