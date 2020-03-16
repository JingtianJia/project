package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.annotation.LoginLogAnnotation;
import com.aaa.lee.repast.annotation.TokenAnnotation;
import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.PmsComment;
import com.aaa.lee.repast.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "查询此商品所有评论", tags = "商品评论接口(提供用户所欲有关操作)")
public class PmsCommentController  extends BaseController {
    @Autowired
    private IRepastService iRepastService;
    @TokenAnnotation
    @GetMapping("/byidselectcomment")
    @ApiOperation(value = "根据商品查评论", notes = "查询此商品所有评论(token，shopid)")
    ResultData byidselectcomment(@RequestParam("token") String token, @RequestParam("shopid") int shopid){

            return iRepastService.byidselectcomment(token, shopid);
        }

        @TokenAnnotation
        @ApiOperation(value = "根据订单id查评论", notes = "查询此商品所有评论(token，orderid)")
        @GetMapping("/byorderidselectcomment")
        ResultData byorderidselectcomment(@RequestParam("token") String token,@RequestParam("orderid") int orderid){
            return iRepastService.byorderidselectcomment(token,orderid);
        }


        @TokenAnnotation
        @PostMapping("/insertcomment")
        @ApiOperation(value = "新增评论", notes = "新增商品评论(token，pmsComment（实体类中时间不用）)")
        ResultData insertcomment(@RequestParam("token") String token,PmsComment pmsComment){
            return iRepastService.insertcomment(token,pmsComment);
        }






        @TokenAnnotation
        @ApiOperation(value = "根据订单id删除评论", notes = "根据订单id删除评论(token，pmsComment)")
        @PostMapping("/deletecomment")
        ResultData deletecomment(@RequestParam("token") String token, PmsComment pmsComment){
            return iRepastService.deletecomment(token,pmsComment);
        }
}
