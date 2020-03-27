package com.aaa.lee.repast.fallback;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.CartItem;
import com.aaa.lee.repast.model.Order;
import com.aaa.lee.repast.model.OrderItem;
import com.aaa.lee.repast.model.OrderReturnApply;
import com.aaa.lee.repast.page.PageInfos;
import com.aaa.lee.repast.service.IOrderService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author Gotta
 * @Date 2020/3/25
 * @Description
 **/
@Component
public class OrderFallBackFactory implements FallbackFactory<IOrderService> {
    @Override
    public IOrderService create(Throwable throwable) {
        IOrderService repastService=new IOrderService() {
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
