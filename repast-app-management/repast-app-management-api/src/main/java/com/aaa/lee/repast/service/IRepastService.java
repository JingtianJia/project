package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.ResultData;

import com.aaa.lee.repast.model.Address;
import com.aaa.lee.repast.model.CouponHistory;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.model.PmsComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.aaa.lee.repast.staticstatus.RequestProperties.TOKEN;

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
     * 查询历史积分
     * @param map
     * @return
     */
    @GetMapping("/queryIntegration")
    ResultData queryIntegration(@RequestBody Map map);

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
    /**
     *  执行通过商品id查询评论
     * @param shopid
     * @param token
     * @return
     */
    @GetMapping("/byIdSelectComment")
    ResultData byIdSelectComment(@RequestParam("token") String token,@RequestParam("shopid") int shopid);


    /**
     *  执行通过订单id查询评论
     * @param shopid
     * @param token
     * @return
     */
    @GetMapping("/byOrderIdSelectComment")
    ResultData byOrderIdSelectComment(@RequestParam("token") String token,@RequestParam("orderId") int orderId);

    /**
     *  执行增加评论
     * @param pmsComment
     * @param token
     * @return
     */
    @PostMapping("/insertComment")
    ResultData insertComment(@RequestParam(value = "token") String token, @RequestBody PmsComment pmsComment);


    /**
     *  执行删除评论
     * @param pmsComment
     * @param token
     * @return
     */
    @PostMapping("/deleteComment")
    ResultData deleteComment(@RequestParam(value = "token") String token, @RequestBody PmsComment pmsComment);

    /**
    * @Author 贾敬田
    * @Description 查询可用优惠券
    * @Date 0:43 2020/3/17
    * @Param []
    * @return com.aaa.lee.repast.base.ResultData
    **/
    @PostMapping("/selectCoupon")
    ResultData selectCoupon();

    /**
    * @Author 贾敬田
    * @Description 查询我领取过的所有优惠券
    * @Date 0:44 2020/3/17
    * @Param [couponHistory]
    * @return com.aaa.lee.repast.base.ResultData
    **/
    @PostMapping("/selectCouponHistory")
    ResultData selectCouponHistory(CouponHistory couponHistory);

    /**
    * @Author 贾敬田
    * @Description 领取优惠券
    * @Date 0:45 2020/3/17
    * @Param [couponHistory]
    * @return com.aaa.lee.repast.base.ResultData
    **/
    @PostMapping("/addCouponHistory")
    ResultData addCouponHistory(CouponHistory couponHistory);

    /**
     *  查询个人信息
     * @param member
     * @return
     */
    @PostMapping("/selectMember")
    ResultData selectMember(@RequestBody Member member);

    /**
     * @author Seven Lee
     * @description
     *      ftp文件上传
     *      file的参数格式是multipart-file/form-data
     *      普通的form表单格式:application/json
     * @param [file, token]
     * @date 2020/3/17
     * @return java.lang.Boolean
     * @throws
     **/
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(@RequestBody MultipartFile file, @RequestParam(TOKEN) String token);

    /**
     *  执行增加收藏
     * @param shopId
     * @param productId
     * @param token
     * @return
     */
    @PostMapping("/insertUmsCollect")
    ResultData insertUmsCollect(@RequestParam(value = "token") String token,@RequestParam(value = "shopId") Long shopId,@RequestParam(value = "productId") Long productId);

    /**
     *  执行查询收藏店铺
     * @param token
     * @return
     */
    @GetMapping("/selectUmsCollectShopId")
    ResultData selectUmsCollectShopId(@RequestParam(value = "token") String token);
    /**
     *  执行查询收藏商品
     * @param token
     * @return
     */
    @GetMapping("/selectUmsCollectProductId")
    ResultData selectUmsCollectProductId(@RequestParam(value = "token") String token);

    /**
     *  执行删除收藏店铺
     * @param token
     * @return
     */
    @PostMapping("/deleteUmsCollectShopId")
    ResultData deleteUmsCollectShopId(@RequestParam(value = "token") String token,@RequestParam(value = "shopId") Long shopId);

    /**
     *  执行删除收藏商品
     * @param token
     * @return
     */
    @PostMapping("/deleteUmsCollectProductId")
    ResultData deleteUmsCollectProductId(@RequestParam(value = "token") String token,@RequestParam(value = "shopId") Long shopId,@RequestParam(value = "productId") Long productId);

}
