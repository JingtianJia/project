package com.aaa.lee.repast.config;

import com.aaa.lee.repast.service.IRepastService;
import com.aaa.lee.repast.utils.GetParamsUtil;
import com.aaa.lee.repast.utils.PostParamsUtil;
import com.aaa.lee.repast.utils.SendParams2ControllerUtil;
import com.aaa.lee.repast.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.aaa.lee.repast.staticstatus.RequestProperties.*;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/17 11:50
 * @Description
 *      路由过滤器
 *      SendErrorFilter:实现了路由的统一异常处理
 **/
@Component
public class TokenFilter extends ZuulFilter {

    @Autowired
    private IRepastService repastService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 1.从当前线程中获取当前的上下文对象
        RequestContext rcx = RequestContext.getCurrentContext();
        // 2.获取HTTPServletRequest对象
        HttpServletRequest request = rcx.getRequest();
        // 3.获取客户端的url访问地址
        String url = request.getRequestURL().toString();
        // 4.判断客户端访问路径中是否包含了"http://"和"/doLogin"
        JSONObject paramsObject = new JSONObject();
        if(url.contains(DO_LOGIN_URL) && (url.contains(HTTP_URL) || url.contains(HTTPS_URL))) {
            // 5.判断客户端的请求方式(POST和GET)
            String method = request.getMethod().toUpperCase();
            if(method.equals(GET_URL)) {
                // 以get的请求方式获取参数
                paramsObject = GetParamsUtil.getParams(request);
            } else if(method.equals(POST_URL)) {
                paramsObject = PostParamsUtil.getPrams(rcx);
            }

            // 6.把参数发送到controller中
            SendParams2ControllerUtil.sendParams(paramsObject, rcx, request);
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext rcx = RequestContext.getCurrentContext();
        HttpServletRequest request = rcx.getRequest();
        // 1.通过前端所传递过来的参数获取接收参数(按照规定，必须要包含一个token值)
        //前台除了登录以外全部用post
        JSONObject params = PostParamsUtil.getPrams(rcx);
        // 2.在参数中取出token值
        Object token = params.get(TOKEN);
        if(null == token || StringUtil.isEmpty(token.toString())) {
            // 说明没有登录，需要执行登录操作--->过滤器直接不做了--->直接跳转到目标controller
            params.clear();
        } else {
            /**
             * 第二种情况，如果token存在，需要进行判断token是否OK(从数据库中判断token值)
             *      写一个SQL语句:
             *          select * from ums_member where token = token
             */
            Boolean ifSuccess = repastService.checkToken(token.toString());
            // 判断token是否正确--->true:正确，false:错误
            if(!ifSuccess) {
                params.clear();
            }
        }
        // 3.把数据发送到目标controller
        SendParams2ControllerUtil.sendParams(params, rcx, request);
        return null;// 这里无论怎么return都不会起作用了--->这个return 其实是直接return到前端的
    }
}
