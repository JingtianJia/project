package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Integration;
import com.aaa.lee.repast.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationController extends CommonController<Integration> {
    @Autowired
    private IntegrationService integrationService;
    @Override
    public BaseService<Integration> getBaseService() {
        return integrationService;
    }

    /**
     * 查询积分接口
     * @param token
     * @return
     */
    @GetMapping("/queryIntegration")
    ResultData queryIntegration(@RequestParam("token") String token){
        return integrationService.queryIntegration(token);
    }

}
