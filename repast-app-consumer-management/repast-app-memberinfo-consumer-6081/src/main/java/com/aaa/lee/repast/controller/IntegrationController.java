package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "积分信息", tags = "积分信息接口")
@RestController
public class IntegrationController {
    @Autowired
    private IRepastService repastService;
    /**
    * @Author 贾敬田
    * @Description 查询历史积分
    * @Date 1:02 2020/3/17
    * @Param [token]
    * @return com.aaa.lee.repast.base.ResultData
    **/
    @PostMapping("/queryIntegration")
    @ApiOperation(value = "查询积分", notes = "用户查询全部积分")
    ResultData queryIntegration(@RequestBody Map map){
        return repastService.queryIntegration(map);
    }
}
