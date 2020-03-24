package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Integration;
import com.aaa.lee.repast.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class IntegrationController extends CommonController<Integration> {
    @Autowired
    private IntegrationService integrationService;
    @Override
    public BaseService<Integration> getBaseService() {
        return integrationService;
    }

    /**
     * 查询历史积分
     * @param map
     * @return
     */
    @PostMapping("/queryIntegration")
    ResultData queryIntegration(@RequestBody Map map){
        List<Integration> integrations = integrationService.queryIntegration(map);
//        return super.operationSuccess("查询积分成功", integrations);
        return super.selcet(map);
    }

}
