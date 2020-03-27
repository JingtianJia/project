package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Order;
import com.aaa.lee.repast.model.OrderItem;
import com.aaa.lee.repast.model.OrderReturnApply;
import com.aaa.lee.repast.page.PageInfos;
import com.aaa.lee.repast.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.aaa.lee.repast.staticstatus.RequestProperties.*;
import static com.aaa.lee.repast.staticstatus.StaticCode.FILE;

/**
 * @Author 丁平达
 * @Date 2020/3/18 21:20
 */
@RestController
@Api(value = "用户退款信息", tags = "用户退款信息接口")
public class OrderReturnApplyController extends BaseController {
    @Autowired
    private IOrderService iMemberService;
    /**
     *      查询单个要退款的订单
     * @param order
     * @param token
     * @return
     */
    @PostMapping("/selectOrderReturnApply")
    @ApiOperation(value = "查询个人单个订单退款", notes = "用户执行查询退款操作(接收app端传递数据)")
    public OrderReturnApply selectOrderReturnApply(@RequestBody Order order,
                                                   @RequestParam(value = TOKEN) String token){
        OrderReturnApply orderReturnApply = iMemberService.selectOrderReturnApply(order, token);
        if(null!=orderReturnApply){
            return orderReturnApply;
        }
        return null;
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
    @ApiOperation(value = "新增退款申请(备用)", notes = "用户执行新增退款申请操作(接收app端传递数据)")
    public ResultData InsertOrderReturnApplyOne(@RequestParam(value = TOKEN) String token,
                                                @RequestBody OrderReturnApply orderReturnApply){
        Boolean aBoolean = iMemberService.InsertOrderReturnApplyOne(token,orderReturnApply);
        if(aBoolean) {
            return super.operationSuccess();
        }
        return super.operationFailed();
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
    @ApiOperation(value = "新增退款+图片申请", notes = "用户执行新增退款申请操作(接收app端传递数据)")
    public ResultData InsertOrderReturnApplyPic(@RequestParam(value = FILE) MultipartFile file,
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
        Boolean aBoolean = iMemberService.InsertOrderReturnApplyPic(file, token, shopId, orderId, companyAddressId, orderSn,
                memberUsername, returnName, returnPhone, reason, description);
        if(aBoolean) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     *  查询当前店铺下的所有SN
     * @param pageInfos
     * @param token
     * @return
     */
    @PostMapping("/selectShopOrderReturnApply")
    @ApiOperation(value = "新增查询店铺下的sn详情", notes = "用户执行新增查询店铺下的sn操作(接收app端传递数据)")
    public  List<OrderReturnApply> selectShopOrderReturnApply(PageInfos pageInfos,
                                                              @RequestParam(value = TOKEN) String token){
        List<OrderReturnApply> stringPageInfo = iMemberService.selectShopOrderReturnApply(pageInfos, token);
        if (null!=stringPageInfo){
            return stringPageInfo;
        }
        return null;
    }

    /**
     * 查询当前订单下的所有商品
     * @param orderReturnApply
     * @param token
     * @return
     */
    @PostMapping("/selectOrderReturnApplyByOrderSn")
    @ApiOperation(value = "新增查询SN下的商品详情", notes = "用户执行新增查询查询SN下的商品操作(接收app端传递数据)")
    List<OrderItem> selectOrderReturnApplyByOrderSn(OrderReturnApply orderReturnApply,
                                                    @RequestParam(value = TOKEN) String token){
     return    iMemberService.selectOrderReturnApplyByOrderSn(orderReturnApply,token);
    }
    /**
     *  审批退单申请
     * @param orderReturnApply
     * @param token
     * @return
     */
    @PostMapping("/updateOrderReturnApplyByOrderSn")
    @ApiOperation(value = "新增审批退单详情", notes = "用户执行新增审批退单操作(接收app端传递数据)")
    Boolean updateOrderReturnApplyByOrderSn(@RequestBody OrderReturnApply orderReturnApply,
                                            @RequestParam(value = TOKEN) String token,
                                            @RequestParam(value = NAME) String name){
        Boolean aBoolean = iMemberService.updateOrderReturnApplyByOrderSn(orderReturnApply, token, name);
        return    aBoolean ;
    }
}
