package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.config.FeignMultiPartConfig;
import com.aaa.lee.repast.model.CartItem;
import com.aaa.lee.repast.model.Order;
import com.aaa.lee.repast.model.OrderItem;
import com.aaa.lee.repast.model.OrderReturnApply;
import com.aaa.lee.repast.page.PageInfos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static com.aaa.lee.repast.staticstatus.RequestProperties.*;
import static com.aaa.lee.repast.staticstatus.RequestProperties.TOKEN;

@FeignClient(value = "orderinfo-interface", configuration = FeignMultiPartConfig.class)
public interface IOrderService {
    /**
     *      查询单个要退款的订单
     * @param order
     * @param token
     * @return
     */
    @PostMapping("/selectOrderReturnApply")
    OrderReturnApply selectOrderReturnApply(@RequestBody Order order,
                                            @RequestParam(value = TOKEN) String token);

    /**
     *  退货申请：未发货退款
     * @param orderReturnApply
     * @param token
     * @return
     */
    @PostMapping(value = "/InsertOrderReturnApplyOne",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean InsertOrderReturnApplyOne(@RequestParam(value = TOKEN) String token,
                                      @RequestBody OrderReturnApply orderReturnApply);
    /**
     *  图片上传退款
     * @param file
     * @param token
     * @param shopId
     * @param orderId
     * @param companyAddressId
     * @param orderSn
     * @param memberUsername
     * @param returnName
     * @param returnPhone
     * @param reason
     * @param description
     * @return
     */
    @PostMapping(value = "/InsertOrderReturnApplyPic",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean InsertOrderReturnApplyPic(@RequestBody MultipartFile file,
                                      @RequestParam(value = TOKEN) String token,
                                      @RequestParam(value = SHOP_ID) Long shopId,
                                      @RequestParam(value = ORDER_ID) Long orderId,
                                      @RequestParam(value = COMPANY_ADDRESS_ID) Long companyAddressId,
                                      @RequestParam(value = ORDER_SN) String  orderSn,
                                      @RequestParam(value = MEMBER_USERNAME) String memberUsername,
                                      @RequestParam(value = RETURN_NAME) String returnName,
                                      @RequestParam(value = RETURN_PHONE) String returnPhone,
                                      @RequestParam(value = REASON) String reason,
                                      @RequestParam(value = DESCRIPTION) String description);

    /**
     *  查询当前店铺下的所有SN
     * @param pageInfos
     * @param token
     * @return
     */
    @PostMapping("/selectShopOrderReturnApply")
    List<OrderReturnApply> selectShopOrderReturnApply(@RequestBody PageInfos pageInfos,
                                                      @RequestParam(value = TOKEN) String token);

    /**
     * 查询当前订单下的所有商品
     * @param orderReturnApply
     * @param token
     * @return
     */
    @PostMapping("/selectOrderReturnApplyByOrderSn")
    List<OrderItem> selectOrderReturnApplyByOrderSn(@RequestBody OrderReturnApply orderReturnApply,
                                                    @RequestParam(value = TOKEN) String token);

    /**
     *  审批退单申请
     * @param orderReturnApply
     * @param token
     * @return
     */
    @PostMapping("/updateOrderReturnApplyByOrderSn")
    Boolean updateOrderReturnApplyByOrderSn(@RequestBody OrderReturnApply orderReturnApply,
                                            @RequestParam(value = TOKEN) String token,
                                            @RequestParam(value = NAME) String name);

    /**
     *  审批退单申请
     * @param cartItems
     * @param token
     * @return
     */
    @PostMapping("/xiaDan")
    ResultData xiaDan(@RequestParam("token") String token, @RequestBody List<CartItem> cartItems);

    /**
     * 删除订单方法

     */
    @PostMapping("/deleteProductFromCart")
    Map<String,Object> deleteProductFromCart(@RequestParam(PRODUCT_ID) Long productId, @RequestParam(TOKEN) String token);

    @PostMapping("/selectCartByToken")
    Map<String,Object> selectCartByToken(@RequestParam(TOKEN) String token);

    /**
     * 添加食堂商品到购物车
     */
    @PostMapping("/canteenAddProductToCart")
    Map<String,Object> canteenAddProductToCart(@RequestParam(TOKEN) String token, @RequestParam(PRODUCT_ID) Long productId);
    /**
     * 添加商品到购物车
     **/
    @PostMapping("/addCart")
    Boolean addCart(@RequestBody Map<String, Object> data, @RequestParam(TOKEN) String token);
}
