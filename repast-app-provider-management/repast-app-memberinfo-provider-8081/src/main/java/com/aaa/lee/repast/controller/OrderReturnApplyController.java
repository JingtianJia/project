package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.model.Order;
import com.aaa.lee.repast.model.OrderItem;
import com.aaa.lee.repast.model.OrderReturnApply;
import com.aaa.lee.repast.page.PageInfos;
import com.aaa.lee.repast.service.OrderReturnApplyService;
import com.aaa.lee.repast.service.OrderReturnReasonService;
import com.aaa.lee.repast.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.aaa.lee.repast.staticstatus.RequestProperties.*;

/**
 * @Author 丁平达
 * @Date 2020/3/18 21:02
 */
@RestController
public class OrderReturnApplyController {
    @Autowired
    private OrderReturnApplyService orderReturnApplyService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private OrderReturnReasonService returnReasonService;
    /**
     *      查询单个要退款的订单
     * @param order
     * @param token
     * @return
     */
    @PostMapping("/selectOrderReturnApply")
    public OrderReturnApply selectOrderReturnApply(@RequestBody Order order,
                                                   @RequestParam(value = TOKEN) String token){
        return orderReturnApplyService.selectOrderReturnApply(order,token);
    }

    /**
     *  退货申请：未发货退款
     * @param orderReturnApply
     * @param token
     * @return
     */

    @PostMapping(value = "/InsertOrderReturnApplyOne",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean InsertOrderReturnApplyOne(
                                             @RequestParam(value = TOKEN) String token,
                                             @RequestBody OrderReturnApply orderReturnApply){
        return orderReturnApplyService.InsertOrderReturnApplyOne(orderReturnApply,token);
    }

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
    public Boolean InsertOrderReturnApplyPic(@RequestBody MultipartFile file,
                                             @RequestParam(value = TOKEN) String token,
                                             @RequestParam(value = SHOP_ID) Long shopId,
                                             @RequestParam(value = ORDER_ID) Long orderId,
                                             @RequestParam(value = COMPANY_ADDRESS_ID) Long companyAddressId,
                                             @RequestParam(value = ORDER_SN) String  orderSn,
                                             @RequestParam(value = MEMBER_USERNAME) String memberUsername,
                                             @RequestParam(value = RETURN_NAME) String returnName,
                                             @RequestParam(value = RETURN_PHONE) String returnPhone,
                                             @RequestParam(value = REASON) String reason,
                                             @RequestParam(value = DESCRIPTION) String description){
        if (file!=null){
            return orderReturnApplyService.insetOrderReturnApplyPic
                    (token,file,uploadService,shopId,orderId,companyAddressId,orderSn,
                            memberUsername,returnName,returnPhone,reason,description);
        }
        OrderReturnApply orderReturnApply = new OrderReturnApply();
        orderReturnApply.setId(shopId).setOrderId(orderId).setCompanyAddressId(companyAddressId)
                .setOrderSn(orderSn).setMemberUsername(memberUsername).setReturnName(returnName)
                .setReturnPhone(returnPhone).setReason(reason).setDescription(description);
        return orderReturnApplyService.InsertOrderReturnApplyOne(orderReturnApply,token);
    }

    /**
     *  查询当前店铺下的所有SN
     * @param pageInfos
     * @param token
     * @return
     */
    @PostMapping("/selectShopOrderReturnApply")
    public  List<OrderReturnApply> selectShopOrderReturnApply(@RequestBody PageInfos pageInfos,
                                                              @RequestParam(value = TOKEN) String token){
        return orderReturnApplyService.selectShopOrderReturnApply(pageInfos,token);
    }

    /**
     * 查询当前订单下的所有商品
     * @param orderReturnApply
     * @param token
     * @return
     */
    @PostMapping("/selectOrderReturnApplyByOrderSn")
    public List<OrderItem> selectOrderReturnApplyByOrderSn
            (@RequestBody OrderReturnApply orderReturnApply,
             @RequestParam(value = TOKEN) String token){
        return orderReturnApplyService.selectOrderReturnApplyByOrderSn(orderReturnApply,token);
    }

    /**
     *  审批退单申请
     * @param orderReturnApply
     * @param token
     * @return
     */
    @PostMapping("/updateOrderReturnApplyByOrderSn")
    public Boolean updateOrderReturnApplyByOrderSn(@RequestBody OrderReturnApply orderReturnApply,
                                                   @RequestParam(value = TOKEN) String token,
                                                    @RequestParam(value = NAME) String name){
        return orderReturnApplyService.updateOrderReturnApplyByOrderSn(orderReturnApply,token
        ,name,returnReasonService);
    }
}


