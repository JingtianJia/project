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
    private  PmsCommentService pmsCommentService;
    @Override
    public BaseService<PmsComment> getBaseService() {
        return pmsCommentService;
    }

    @GetMapping("/byIdSelectComment")
    ResultData byIdSelectComment(@RequestParam("token") String token, @RequestParam("shopId") int shopId){

            return pmsCommentService.byShopIdSelectPms(token,shopId);
    }

    @GetMapping("/byOrderIdSelectComment")
    ResultData byOrderIdSelectComment(@RequestParam("token") String token,@RequestParam("orderId") int orderId){
            return pmsCommentService.byOrderIdSelectPms(token,orderId);
    }

        @PostMapping("/insertComment")
        ResultData insertComment(@RequestParam("token") String token,@RequestBody PmsComment pmsComment){
            return pmsCommentService.insertComment(token,pmsComment);
        }


        @PostMapping("/deleteComment")
        ResultData deleteComment(@RequestParam("token") String token,@RequestBody PmsComment pmsComment){
            return pmsCommentService.deleteComment(token,pmsComment);
        }
}
