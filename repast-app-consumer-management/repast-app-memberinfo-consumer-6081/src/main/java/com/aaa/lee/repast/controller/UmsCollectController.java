package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "收藏相关接口", tags = "收藏接口(提供用户所有有关操作)")
public class UmsCollectController extends BaseController {
    @Autowired
    private IRepastService iRepastService;



    /**
     * 收藏一个新的店铺或产品
     * */
    @ApiOperation(value = "新增收藏", notes = "新增收藏(token，shopId，productId)")
    @PostMapping("/insertUmsCollect")
    ResultData insertUmsCollect(@RequestParam("token") String token, @RequestParam("shopId") Long shopId, @RequestParam("productId") Long productId){
        return iRepastService.insertUmsCollect(token, shopId, productId);
    }
    /**
     * 查询收藏的店铺
     * */
    @ApiOperation(value = "查询收藏的店铺", notes = "查询收藏的店铺(token)")
    @GetMapping("/selectUmsCollectShopId")
    ResultData selectUmsCollectShopId(@RequestParam("token") String token){
        return iRepastService.selectUmsCollectShopId(token);
    }
    /**
     * 查询收藏的商品
     * */
    @ApiOperation(value = "查询收藏的商品", notes = "查询收藏的商品(token)")
    @GetMapping("/selectUmsCollectProductId")
    ResultData selectUmsCollectProductId(@RequestParam("token") String token){
        return iRepastService.selectUmsCollectProductId(token);
    }
    /**
     * 删除收藏的店铺
     * */
    @ApiOperation(value = "删除收藏的店铺", notes = "删除收藏的店铺(token，shopId)")
    @PostMapping("/deleteUmsCollectShopId")
    ResultData deleteUmsCollectShopId(@RequestParam("token") String token, @RequestParam("shopId") Long shopId){
        return iRepastService.deleteUmsCollectShopId(token, shopId);
    }
    /**
     * 查询收藏的店铺
     * */
    @ApiOperation(value = "删除收藏的商品", notes = "删除收藏的商品(token，shopId，productId)")
    @PostMapping("/deleteUmsCollectProductId")
    ResultData deleteUmsCollectProductId(@RequestParam("token") String token, @RequestParam("shopId") Long shopId,@RequestParam("productId") Long productId){
        return iRepastService.deleteUmsCollectProductId(token, shopId,productId);
    }
}
