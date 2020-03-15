package com.aaa.lee.repast.service;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.mapper.AddressMapper;
import com.aaa.lee.repast.mapper.MemberMapper;
import com.aaa.lee.repast.model.Address;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aaa.lee.repast.staticstatus.StaticCode.*;

/**
 * @Author 丁平达
 * @Date 2020/3/15 15:22
 *      地址相关操作
 */
@Service
public class AddressService  extends BaseService<Address> {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private   AddressMapper addressMapper;

    /**
     * @description
     * 新增地址
     * @date 2020/3/12 16:47
     * @author Dpd
     */
    public ResultData addAddress(Address address,String token) {
        ResultData resultData=new ResultData();
        try {
            if (null!=address && null!=token &&
                    !"".equals(token) && StringUtil.isNotEmpty(token)){
                //通过token值获取当前用户对象
                Member member = memberMapper.selectMemberByToken(token);
                address.setMemberId(member.getId());
                //逻辑删除字段让他默认不为删除
                address.setDelStatus(ZERO);
                //不为默认地址
                address.setDefaultStatus(ONE);
                Integer i=add(address);
                if (i>ZERO){
                    resultData.setData(address);
                }else {
                    resultData.setData(null);
                }
            }
            return resultData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @description
     * 删除地址
     * @date 2020/3/12 16:49
     * @author Dpd
     */
    public ResultData delAddressInId(Address address,String token){
        ResultData resultData=new ResultData();

        try {
            //前台传过来的ID不为null
            if (null!=address && null!=token &&
                    !"".equals(token) && StringUtil.isNotEmpty(token)) {
                //增加状态值
                address.setDelStatus(ONE);
                //判断增加状态值成功
                if (ONE == address.getDelStatus() && null != address.getId()) {
                  //修改状态
                    Integer i=addressMapper.updateByPrimaryKey(address);
                    if (i > ZERO) {
                        resultData.setData(address);
                    }else {
                        return null;
                    }
                }
                return resultData;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @description
     * 修改地址
     * @date 2020/3/12 16:52
     * @author DPD
     */
    public ResultData upAddress(Address address,String token){
        ResultData resultData=new ResultData();
        try {
            //判断是否登录
            if (null!=token && !"".equals(token) &&StringUtil.isNotEmpty(token)) {
                //通过token查询当前用户
                Member member = memberMapper.selectMemberByToken(token);
                //把当前用户的id给地址对象
                address.setMemberId(member.getId());
                //通过地址对象修改数据
                Integer integer=addressMapper.updateByPrimaryKey(address);
                if (integer>ZERO){
                    resultData.setData(address);
                }else {
                    return null;
                }
            }
            return resultData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @description
     * 显示所有地址
     * @date 2020/3/12 17:05
     * @author Dpd
     * @return
     */
    public ResultData selectAddressAll(String token) {
        ResultData resultData=new ResultData();
        try {
            //判断是否登录
            if (null!=token && !"".equals(token) && StringUtil.isNotEmpty(token)) {
                //通过token查询当前用户
                Member member = memberMapper.selectMemberByToken(token);
                //获取用户所有的地址
                List<Address> list = addressMapper.selAddress(member.getId());
                //判断查询的是否为空
                if (list.size()>ZERO&&null!=list){
                    resultData.setData(list);
                }else {
                    return null;
                }

            }else {
                return null;
            }
            return resultData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
