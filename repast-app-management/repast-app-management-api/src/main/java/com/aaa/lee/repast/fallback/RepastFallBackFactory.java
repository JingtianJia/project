package com.aaa.lee.repast.fallback;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Address;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.model.PmsComment;
import com.aaa.lee.repast.service.IRepastService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
            public ResultData queryIntegration(String token) {
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
            public ResultData byidselectcomment(String token, int shopid) {
                System.out.println("熔断根据商品查询评论");
                return null;
            }

            @Override
            public ResultData byorderidselectcomment(String token, int orderid) {
                System.out.println("熔断根据订单查询评论");
                return null;
            }

            @Override
            public ResultData insertcomment(String token, PmsComment pmsComment) {
                System.out.println("熔断新增评论");
                return null;
            }

            @Override
            public ResultData deletecomment(String token, PmsComment pmsComment) {
                System.out.println("熔断删除评论");
                return null;
            }
        };
        return repastService;
    }

}
