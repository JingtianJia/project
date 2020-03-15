package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.annotation.LoginLogAnnotation;
import com.aaa.lee.repast.annotation.TokenAnnotation;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "查询此商品所有评论", tags = "查询商品评论接口(提供用户所欲有关操作)")
public class PmsCommentController {
    @Autowired
    private IRepastService iRepastService;
    @TokenAnnotation
    @GetMapping("byidselectcomment")
    @ApiOperation(value = "查评论", notes = "查询此商品所有评论(token，shopid)")
    @LoginLogAnnotation(operationType = "查询操作", operationName = "查询此商品所有评论")
    ResultData byidselectcomment(@RequestParam("token") String token, int shopid){
        return iRepastService.byidselectcomment(token, shopid);
    }
}
