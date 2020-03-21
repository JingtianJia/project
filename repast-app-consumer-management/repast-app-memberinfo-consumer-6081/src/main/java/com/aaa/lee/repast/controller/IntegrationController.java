package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.service.IRepastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    @GetMapping("/queryIntegration")
    ResultData queryIntegration(@RequestBody Map map){
        return repastService.queryIntegration(map);
    }
}
