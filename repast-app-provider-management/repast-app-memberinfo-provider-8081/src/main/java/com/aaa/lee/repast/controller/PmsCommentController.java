package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.PmsComment;
import com.aaa.lee.repast.service.PmsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PmsCommentController extends CommonController<PmsComment> {
    @Autowired
    private PmsCommentService pmsCommentService;
    @Override
    public BaseService<PmsComment> getBaseService() {
        return pmsCommentService;
    }

    @GetMapping("/byidselectcomment")
    ResultData byidselectcomment(@RequestParam("token") String token,@RequestParam("shopid") int shopid){
        return pmsCommentService.byshopidselectpms(token,shopid);
    }
    @GetMapping("/byorderidselectcomment")
    ResultData byorderidselectcomment(@RequestParam("token") String token,@RequestParam("orderid") int orderid){
        return pmsCommentService.byorderidselectpms(token,orderid);
    }

    @PostMapping("/insertcomment")
    ResultData insertcomment(@RequestParam("token") String token,@RequestBody PmsComment pmsComment){
        return pmsCommentService.insertcomment(token,pmsComment);
    }


    @PostMapping("/deletecomment")
    ResultData deletecomment(@RequestParam("token") String token,@RequestBody PmsComment pmsComment){
        return pmsCommentService.deletecomment(token,pmsComment);
    }
}
