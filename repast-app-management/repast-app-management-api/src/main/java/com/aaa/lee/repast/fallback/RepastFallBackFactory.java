package com.aaa.lee.repast.fallback;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Address;
import com.aaa.lee.repast.model.CouponHistory;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.model.PmsComment;
import com.aaa.lee.repast.service.IRepastService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/10 10:17
 * @Description
 **/
@Component
public class RepastFallBackFactory implements FallbackFactory<IRepastService> {

    @Override
    public IRepastService create(Throwable throwable) {
        IRepastService repastService = new IRepastService() {
            @Override
            public Boolean doLogin(Member member) {
                System.out.println("熔断登录方法！");
                return null;
            }

            @Override
            public ResultData saveLog(Map map) {
                System.out.println("熔断日志方法！");
                return null;
            }

            @Override
            public ResultData queryIntegration(Map map) {
                System.out.println("熔断积分方法");
                return null;
            }

            @Override
            public ResultData selectAddressAll(String token) {
                System.out.println("熔断查询所有地址方法！");
                return null;
            }

            @Override
            public ResultData addAddress(Address address, String token) {
                System.out.println("熔断新增地址方法！");
                return null;
            }

            @Override
            public ResultData delAddressInId(Address address, String token) {
                System.out.println("熔断删除地址方法！");
                return null;
            }

            @Override
            public ResultData upAddress(Address address, String token) {
                System.out.println("熔断修改地址方法！");
                return null;
            }

            @Override
            public ResultData byIdSelectComment(String token, int shopId) {
                System.out.println("熔断根据商品查询评论");
                return null;
            }

            @Override
            public ResultData byOrderIdSelectComment(String token, int order) {
                System.out.println("熔断根据订单查询评论");
                return null;
            }

            @Override
            public ResultData insertComment(String token, PmsComment pmsComment) {
                System.out.println("熔断新增评论");
                return null;
            }


            @Override
            public ResultData deleteComment(String token, PmsComment pmsComment) {
                System.out.println("熔断删除评论");
                return null;
            }

            @Override
            public ResultData selectCoupon() {
                System.out.println("熔断查询通用优惠券");
                return null;
            }

            @Override
            public ResultData selectCouponHistory(CouponHistory couponHistory) {
                System.out.println("熔断查询优惠历史通用方法");
                return null;
            }

            @Override
            public ResultData addCouponHistory(CouponHistory couponHistory) {
                System.out.println("熔断新增优惠券方法");
                return null;
            }

            @Override
            public ResultData selectMember(Member member) {
                System.out.println("熔断查询个人信息方法");
                return null;
            }
            @Override
            public Boolean uploadFile(MultipartFile file, String token) {
                System.out.println("熔断文件上传方法！");
                return null;
            }

            @Override
            public ResultData insertUmsCollect(String token, Long shopId, Long productId) {
                System.out.println("熔断新增收藏");
                return null;
            }

            @Override
            public ResultData selectUmsCollectShopId(String token) {
                System.out.println("熔断查询收藏店铺");
                return null;
            }

            @Override
            public ResultData selectUmsCollectProductId(String token) {
                System.out.println("熔断查询收藏商品");
                return null;
            }

            @Override
            public ResultData deleteUmsCollectShopId(String token, Long shopId) {
                System.out.println("熔断删除收藏店铺");
                return null;
            }

            @Override
            public ResultData deleteUmsCollectProductId(String token, Long shopId, Long productId) {
                System.out.println("熔断删除收藏商品");
                return null;
            }


        };
        return repastService;
    }
}
