package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.annotation.LoginLogAnnotation;
import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.model.Member;
import com.aaa.lee.repast.service.IMemberService;
import com.aaa.lee.repast.utils.GetZuulFilterParamsUtil;
import com.aaa.lee.repast.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/10 10:13
 * @Description
 **/
@RestController
@Api(value = "用户信息", tags = "用户信息接口")
public class MemberController extends BaseController {

    @Autowired
    private IMemberService repastService;

    /**
     * @author Seven Lee
     * @description
     * @param [member]
     * @date 2020/3/10
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
    **/
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录", notes = "用户执行登录操作(接收微信端传递数据)")
    @LoginLogAnnotation(operationType = "登录操作", operationName = "普通用户登录")
    public ResultData doLogin(HttpServletRequest request) {
        // 既然要接收参数必须使用getInputSteam();--->通过流来进行转换
        Map params = GetZuulFilterParamsUtil.getParams(request);
        // 需要调用api工程(feign)
        return repastService.doLogin(params);
    }
    /**
     * 根据id修改用户信息
     * @param member
     * @return
     */
    @PostMapping ("/updateMember")
    public ResultData updateMemberById(@RequestParam("token")String token, @RequestBody Member member){
        return repastService.updateMember(token,member);
    }
}
