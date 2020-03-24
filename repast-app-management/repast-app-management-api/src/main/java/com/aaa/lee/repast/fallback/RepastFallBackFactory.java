package com.aaa.lee.repast.fallback;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.*;
import com.aaa.lee.repast.page.PageInfos;
import com.aaa.lee.repast.service.IRepastService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
            public Boolean checkToken(String token) {
                System.out.println("熔断token验证！");
                return null;
            }

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
            public ResultData updateMember(String token, Member member) {
                System.out.println("熔断更新用户信息方法");
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

            @Override
            public OrderReturnApply selectOrderReturnApply(Order order, String token) {
                System.out.println("熔断查询个人退款方法！");
                return null;
            }

            @Override
            public Boolean InsertOrderReturnApplyOne(String token,OrderReturnApply orderReturnApply) {
                System.out.println("熔断退款方法！");
                return null;
            }

            @Override
            public Boolean InsertOrderReturnApplyPic(MultipartFile file, String token, Long shopId, Long orderId, Long companyAddressId, String orderSn, String memberUsername, String returnName, String returnPhone, String reason, String description) {
                System.out.println("熔断退款+上传图片方法！");
                return null;
            }

            @Override
            public List<OrderReturnApply> selectShopOrderReturnApply(PageInfos pageInfos, String token) {
                System.out.println("熔断查询店铺下的所有SN方法！");
                return null;
            }

            @Override
            public List<OrderItem> selectOrderReturnApplyByOrderSn(OrderReturnApply orderReturnApply, String token) {
                System.out.println("熔断查询店铺下的SN下的所有商品方法！");
                return null;
            }

            @Override
            public Boolean updateOrderReturnApplyByOrderSn(OrderReturnApply orderReturnApply, String token, String name) {
                System.out.println("熔断退单审批方法！");
                return null;
            }

            @Override
            public ResultData xiaDan(String token, List<CartItem> cartItems) {
                System.out.println("熔断下单方法");
                return null;
            }

            @Override
            public Map<String, Object> deleteProductFromCart(Long productId, String token) {
                System.out.println("熔断清空购物车的方法！");
                return null;
            }

            @Override
            public Map<String, Object> selectCartByToken(String token) {
                System.out.println("熔断查询购物车的方法！");
                return null;
            }

            @Override
            public Map<String, Object> canteenAddProductToCart(String token, Long productId) {
                System.out.println("熔断添加食堂商品到购物车");
                return null;
            }

            @Override
            public Boolean addCart(Map<String, Object> data, String token) {
                System.out.println("熔断添加商品到购物车的方法！");
                return null;
            }
        };
        return repastService;
    }
}
