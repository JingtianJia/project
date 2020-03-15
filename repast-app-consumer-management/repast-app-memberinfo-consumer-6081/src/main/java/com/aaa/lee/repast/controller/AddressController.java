package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Address;
import com.aaa.lee.repast.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.aaa.lee.repast.staticstatus.StaticCode.*;

/**
 * @Author 丁平达
 * @Date 2020/3/15 19:05
 */
@RestController
@Api(value = "用户地址信息", tags = "用户地址信息接口(提供用户地址信息有关操作)")
public class AddressController extends BaseController {
    @Autowired
    private IRepastService iRepastService;

    /**
     *  查询用户所有地址
     * @param token
     * @return
     */
    @PostMapping("/selectAddressAll")
    @ApiOperation(value = "查询地址", notes = "用户执行查询地址操作(接收app端传递数据)")
    public ResultData selectAddressAll(@RequestParam(value = "token") String token){
        ResultData resultData = iRepastService.selectAddressAll(token);
        if(null!=resultData){
            return resultData;
        }
        return super.operationSuccess(OPERATION_FAILURE);
    }

    /**
     *  执行新增地址操作
     * @param address
     * @param token
     * @return
     */
    @PostMapping("/addAddress")
    @ApiOperation(value = "新增地址", notes = "用户执行新增地址操作(接收app端传递数据)")
    public ResultData  addAddress(@RequestBody Address address,
                                  @RequestParam(value = "token") String token){
        ResultData resultData = iRepastService.addAddress(address, token);
        if(null!=resultData){
            return resultData;
        }
        return super.operationSuccess(OPERATION_FAILURE);
    }

    /**
     *  执行通过token删除地址（逻辑删除）
     * @param address
     * @param token
     * @return
     */
    @PostMapping("/delAddressInId")
    @ApiOperation(value = "删除地址", notes = "用户执行删除地址操作(接收app端传递数据)")
    public ResultData delAddressInId(@RequestBody Address address,
                                     @RequestParam(value = "token") String token){
        ResultData resultData = iRepastService.delAddressInId(address, token);
        if(null!=resultData){
            return resultData;
        }
        return super.operationSuccess(OPERATION_FAILURE);
    }
    /**
     *  执行通过token删除地址（逻辑删除）
     * @param address
     * @param token
     * @return
     */
    @PostMapping("/upAddress")
    @ApiOperation(value = "修改地址", notes = "用户执行修改地址操作(接收app端传递数据)")
    public ResultData upAddress(@RequestBody Address address,
                                @RequestParam(value = "token") String token){
        ResultData resultData = iRepastService.upAddress(address, token);
        if(null!=resultData){
            return resultData;
        }
        return super.operationSuccess(OPERATION_FAILURE);
    }
}
