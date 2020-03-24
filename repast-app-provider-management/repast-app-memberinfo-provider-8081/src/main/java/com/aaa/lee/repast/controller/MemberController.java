package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseService;
import com.aaa.lee.repast.base.CommonController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/10 10:53
 * @Description
 **/
@RestController
public class MemberController extends CommonController<Member> {

    @Autowired
    private MemberService memberService;

    @Override
    public BaseService<Member> getBaseService() {
        return memberService;
    }

    /**
    * @Author Gotta
    * @Description 登录方法
    * @Date 15:55 2020/3/24
    * @Param member
    * @return java.lang.Boolean
    **/
    @PostMapping("/doLogin")
    public Boolean doLogin(@RequestBody Member member) {
        return memberService.doLogin(member);
    }

    /**
    * @Author Gotta
    * @Description 检查token是否可用
    * @Date 13:02 2020/3/24 
    * @Param token
    * @return java.lang.Boolean
    **/
    @PostMapping("/checkToken")
    public Boolean checkToken(@RequestParam("token") String token) {
        return memberService.checkToken(token);
    }
    
    /**
     * 根据id修改用户信息
     * @param memberId
     * @return
     */
    @PostMapping ("/updateMember")
    public ResultData updateMember(@RequestParam("token")String token, @RequestBody Member member){
        return memberService.update(token,member);
    }
}
