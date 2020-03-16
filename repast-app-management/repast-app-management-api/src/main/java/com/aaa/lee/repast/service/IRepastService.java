package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.ResultData;

import com.aaa.lee.repast.model.Address;
import com.aaa.lee.repast.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/10 10:16
 * @Description
 *      springcloud2.x之后，在feign中只能出现一次
 *      value的值非常重要:
 *          这个value的值，就取决于是否可以调用到provider中的值
 *              !!!! value的值指向的就是provider项目中application.properties文件中所配置的spring.application.name !!!!
 *              !!!! api中所写的接口，一定要和provider的Controller中的方法一模一样 !!!!
 **/
@FeignClient(value = "memberinfo-interface")
//@FeignClient(value = "memberinfo-interface", fallbackFactory = RepastFallBackFactory.class)
public interface IRepastService {

    /**
     * @author Seven Lee
     * @description
     *      执行登录操作
     * @param [member]
     * @date 2020/3/10
     * @return java.lang.Boolean
     * @throws 
    **/
    @PostMapping("/doLogin")
    Boolean doLogin(@RequestBody Member member);

    /**
     * @author Seven Lee
     * @description
     *      登录日志保存
     * @param map
     * @date 2020/3/11
     * @return java.lang.Boolean
     * @throws
    **/
    @PostMapping("/add")
    ResultData saveLog(@RequestBody Map map);

    /**
     * 查询积分
     * @param token
     * @return
     */
    @GetMapping("/queryIntegration")
    ResultData queryIntegration(@RequestParam("token") String token);

    /**
     *  执行查询地址操作
     * @param token
     * @return
     */
    @PostMapping("/selectAddressAll")
    ResultData selectAddressAll(@RequestParam(value = "token") String token);

    /**
     *  执行新增地址操作
     * @param address
     * @param token
     * @return
     */
    @PostMapping("/addAddress")
     ResultData  addAddress(@RequestBody Address address,
                                  @RequestParam(value = "token") String token);

    /**
     *  执行通过token删除地址（逻辑删除）
     * @param address
     * @param token
     * @return
     */
     @PostMapping("/delAddressInId")
     ResultData delAddressInId(@RequestBody Address address, @RequestParam(value = "token") String token);

    /**
     *  执行修改地址操作
     * @param address
     * @param token
     * @return
     */
    @PostMapping("/upAddress")
    ResultData upAddress(@RequestBody Address address, @RequestParam(value = "token") String token);
}
