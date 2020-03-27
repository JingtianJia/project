package com.aaa.lee.repast.config;

import com.aaa.lee.repast.service.IMemberService;
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
    private IMemberService repastService;

    /**
     *      路由过滤器类型
     *          一共只有四个值:
     *              1.pre:到达路由之前所需要执行
     *              2.routing:在到达路由的时候执行
     *              3.post:在路由之后执行
     *              4.error:发生异常的时候执行(通常使用统一异常处理)
    **/
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器优先级，从0开始，数字越大优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否启用过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        // 1.获取request的上下文对象(不要去new对象，因为new出来的对象一定是空的，无论什么时候都必须从当前线程中获取当前的上下文)

        RequestContext rcx = RequestContext.getCurrentContext();
        // 2.获取HTTPServletRequest对象
        HttpServletRequest request = rcx.getRequest();
        // 3.获取客户端的url访问地址
        String url = request.getRequestURL().toString();
        // 4.判断客户端访问路径中是否包含了"http://"和"/doLogin"
        JSONObject paramsObject = new JSONObject();
        if(url.contains(DO_LOGIN_URL) && (url.contains(HTTP_URL) || url.contains(HTTPS_URL))) {
            // 说明请求是登录请求，不需要过滤，直接放行
            // 获取客户端传递过来的对象信息--->再把这个对象信息返回给目标controller
            // 5.判断客户端的请求方式(POST和GET)(if和else if不能使用else)
            String method = request.getMethod().toUpperCase();// 请求方式(GET/POST)
            if(method.equals(GET_URL)) {
                // 以get的请求方式获取参数
                paramsObject = GetParamsUtil.getParams(request);

            } else if(method.equals(POST_URL)) {
                // 以POST的形式获取请求参数(Post不像Get，直接追加在地址栏中，Post以对象文本域流的形式来进行发送)
                // 序列化的作用就是把实体类能够转换二进制流--->在http协议中以POST的形式进行传输
                // 如果要获取到POST请求参数就必须要拿到流(输入流)对象--->这个流中就有需要的参数
                // 通过输入流再进一步转换--->字节数组--->json对象
                paramsObject = PostParamsUtil.getPrams(rcx);
            }

            // 6.把参数发送到controller中
            SendParams2ControllerUtil.sendParams(paramsObject, rcx, request);
            return false;
        }
        return true;
    }

    /**
     * @author Seven Lee
     * @description
     *      路由过滤器的具体业务
     *      也就是说实现了验证token
     *      redis实现分布式锁！
     *      set(key,value,nx,xx,seconds,millsecond);
     *      整合核心业务逻辑就是主要来判断token的
     * @param []
     * @date 2020/3/17
     * @return java.lang.Object
     * @throws
    **/
    @Override
    public Object run() throws ZuulException {
        /**
         * 因为在shouldFilter中已经判断是否为登录操作(如果为登录操作--->直接在shouldFilter中就已经return false)
         * 不需要经过过滤器判断
         * run方法中所写的内容和登录就没有任何关系了!!!
         * 第一种情况:
         *      从客户端有两个参数传递过来
         *      Order order, String token
         * @PostMapping
         * public String saveOrder(Order order, String token) {
         *
         * }
         * 第二种情况:
         *      全部封装在一个类型中传递过来
         * @PostMapping
         * public String saveOrder(Map map) {
         *
         * }
         *
         * (前后端分离)--->在前端和后端进行对接的时候，需要定义接口文档规范(前端传递的参数是一个还是两个或者多个，后端给前端所返回的类型...)
         *
         *  必须要以Post的形式来进行传参(不能把token暴露给客户)
         *
         *  zuulFilter，是无法默认获取Post的请求参数(PostParamUtil.java)
         *
         */
        RequestContext rcx = RequestContext.getCurrentContext();
        HttpServletRequest request = rcx.getRequest();
        // 1.通过前端所传递过来的参数获取接收参数(按照规定，必须要包含一个token值)
        JSONObject params = PostParamsUtil.getPrams(rcx);// 其实在这相当于获取的就是Map
        // mamber对象，一个token值
        // 2.在参数中取出token值(如果token存在，如果token存在，证明已经登录过，如果token不存在，就说明是一个未登录用户)
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
