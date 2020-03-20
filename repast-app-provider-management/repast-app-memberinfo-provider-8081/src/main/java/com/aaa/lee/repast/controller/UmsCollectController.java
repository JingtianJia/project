package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.UmsCollect;
import com.aaa.lee.repast.service.UmsCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UmsCollectController extends CommonController<UmsCollect> {
    @Autowired
    private UmsCollectService umsCollectService;
    @Override
    public BaseService<UmsCollect> getBaseService() {
        return umsCollectService;
    }
    /**
     * 收藏一个新的店铺或产品
     * */
    @PostMapping("/insertUmsCollect")
    ResultData insertUmsCollect(@RequestParam("token") String token,@RequestParam("shopId") Long shopId,@RequestParam("productId") Long productId){
        return umsCollectService.insertUmsCollect(token, shopId, productId);
    }
    /**
     * 查询收藏的店铺
     * */
    @GetMapping("/selectUmsCollectShopId")
    ResultData selectUmsCollectShopId(@RequestParam("token") String token){
        return umsCollectService.selectUmsCollectShopIp(token);
    }
    /**
     * 查询收藏的产品
     * */
    @GetMapping("/selectUmsCollectProductId")
    ResultData selectUmsCollectProductId(@RequestParam("token") String token){
        return umsCollectService.selectUmsCollectProductId(token);
    }
    /**
     * 删除收藏的店铺
     * */
    @PostMapping("/deleteUmsCollectShopId")
    ResultData deleteUmsCollectShopId(@RequestParam("token") String token,@RequestParam("shopId") Long shopId){
        return umsCollectService.deleteUmsCollectShopId(token,shopId);
    }
    /**
     * 删除收藏的商品
     * */
    @PostMapping("/deleteUmsCollectProductId")
    ResultData deleteUmsCollectProductId(@RequestParam("token") String token,@RequestParam("shopId") Long shopId,@RequestParam("productId") Long productId){
        return umsCollectService.deleteUmsCollectProductId(token, shopId, productId);
    }
}
