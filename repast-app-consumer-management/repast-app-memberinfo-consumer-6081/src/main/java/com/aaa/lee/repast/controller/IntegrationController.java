package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.annotation.TokenAnnotation;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.service.IRepastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationController {
    @Autowired
    private IRepastService repastService;
    /**
     * 查询积分
     * @param token
     * @return
     */
    @TokenAnnotation
    @GetMapping("/queryIntegration")
    ResultData queryIntegration(@RequestParam("token") String token){
        return repastService.queryIntegration(token);
    }
}
