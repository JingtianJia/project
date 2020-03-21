package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Address;
import com.aaa.lee.repast.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.aaa.lee.repast.staticstatus.RequestProperties.TOKEN;

/**
 * @Author 丁平达
 * @Date 2020/3/15 18:53
 */
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     *  执行查询所有地址信息
     * @param token
     * @return
     */
    @PostMapping("/selectAddressAll")
    public ResultData  selectAddressAll(@RequestParam(value = TOKEN) String token){
            return  addressService.selectAddressAll(token);
    }

    /**
     *  执行新增地址操作
     * @param address
     * @param token
     * @return
     */
    @PostMapping("/addAddress")
    public ResultData  addAddress(@RequestBody Address address,
                                 @RequestParam(value = TOKEN) String token){
      return addressService.addAddress(address,token);
   }

    /**
     *  执行通过token删除地址（逻辑删除）
     * @param address
     * @param token
     * @return
     */
   @PostMapping("/delAddressInId")
   public ResultData delAddressInId(@RequestBody Address address,
                                    @RequestParam(value = TOKEN) String token){
      return addressService.delAddressInId(address,token);
   }
    /**
     *  执行通过token删除地址（逻辑删除）
     * @param address
     * @param token
     * @return
     */
    @PostMapping("/upAddress")
   public ResultData upAddress(@RequestBody Address address,
                               @RequestParam(value = TOKEN) String token){
      return   addressService.upAddress(address,token);
   }
}
