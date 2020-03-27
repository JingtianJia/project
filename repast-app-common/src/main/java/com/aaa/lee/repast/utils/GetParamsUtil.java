package com.aaa.lee.repast.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

import static com.aaa.lee.repast.staticstatus.RequestProperties.GET_URL;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/18 10:44
 * @Description
 *      获取以GET请求方式的参数
 **/
public class GetParamsUtil {

    /**
     * @author Seven Lee
     * @description
     *      获取参数方法
     * @param [request]
     * @date 2020/3/18
     * @return java.lang.String
     * @throws
    **/
    public static JSONObject getParams(HttpServletRequest request) {
        // 创建StringBuilder来存放所传递过来的参数--->String url = "http://localhost:4081/aaa/seven/xxx?"
        StringBuilder params = new StringBuilder("?");
        // ?username=zhangsan&age=20&xxx
        Enumeration<String> names = request.getParameterNames();// 其实这里的names就是所有参数的key值
        if(request.getMethod().toUpperCase().equals(GET_URL)) {
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                params.append(name);// ?username
                params.append("=");// ?username=
                params.append(request.getParameter(name));// ?username=zhangsan
                params.append("&");// ?username=zhangsan&
            }
        }
        // 最终参数的最后会多出来一个&符号
        // 如果地址没有参数-->返回到controller的时候--->带?号的参数--->都不为""
        // 如果有参截取&符号，如果没有参则截取?，方便于controller判断
        if(params.length() > 0) {// 可以不用判断，直接截取
            // params.substring();// 是不合适的，虽然也可以实现截取，转换了两步
            // 第一步prams(StringBuilder-->String)
            // 第二步然后再截取--->返回String类型
            params.delete(params.length() - 1, params.length());
            // delete和substring是一样的(delete的截取效率比subString效率会高很多)，只是delete适合于SpringBuilder
        }
        return JSON.parseObject(params.toString());
    }

}
