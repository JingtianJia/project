package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.PmsComment;
import com.aaa.lee.repast.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "查询此商品所有评论", tags = "商品评论接口")
public class PmsCommentController  extends BaseController {
    @Autowired
    private IMemberService iMemberService;
    @GetMapping("/byIdSelectComment")
    @ApiOperation(value = "根据商品查评论", notes = "查询此商品所有评论(token，shopId)")
    ResultData byIdSelectComment(@RequestParam("token") String token, @RequestParam("shopId") int shopId){

            return iMemberService.byIdSelectComment(token, shopId);
        }

        @ApiOperation(value = "根据订单id查评论", notes = "查询此商品所有评论(token，orderid)")
        @GetMapping("/byOrderIdSelectComment")
        ResultData byOrderIdSelectComment(@RequestParam("token") String token,@RequestParam("orderId") int orderId){
            return iMemberService.byOrderIdSelectComment(token,orderId);
        }


        @PostMapping("/insertComment")
        @ApiOperation(value = "新增评论", notes = "新增商品评论(token，pmsComment（实体类中时间不用）)")
        ResultData insertComment(@RequestParam("token") String token,PmsComment pmsComment){
            return iMemberService.insertComment(token,pmsComment);
        }






        @ApiOperation(value = "根据订单id删除评论", notes = "根据订单id删除评论(token，pmsComment)")
        @PostMapping("/deleteComment")
        ResultData deleteComment(@RequestParam("token") String token, PmsComment pmsComment){
            return iMemberService.deleteComment(token,pmsComment);
        }
}
