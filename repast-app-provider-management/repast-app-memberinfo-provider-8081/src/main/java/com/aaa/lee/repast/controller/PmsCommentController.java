package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.PmsComment;
import com.aaa.lee.repast.service.PmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PmsCommentController extends CommonController<PmsComment> {
    @Autowired
    private PmsCommentService pmsCommentService;
    @Override
    public BaseService<PmsComment> getBaseService() {
        return pmsCommentService;
    }

    @GetMapping("/byidselectcomment")
    ResultData byidselectcomment(@RequestParam("token") String token,int shopid){
        return pmsCommentService.byshopidselectpms(token,shopid);
    }

}
