package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.LoginLog;
import com.aaa.lee.repast.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/12 12:01
 * @Description
 **/
@RestController
public class LoginLogController extends CommonController<LoginLog> {

    @Autowired
    private LoginLogService loginLogService;

    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }

    /**
     * @author Seven Lee
     * @description
     *      新增操作
     * @param [map]
     * @date 2020/3/12
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
    **/
    @PostMapping("/add")
    ResultData saveLog(@RequestBody Map map) {
        return super.add(map);
    }
}
