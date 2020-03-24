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
        StringBuilder params = new StringBuilder("?");
        Enumeration<String> names = request.getParameterNames();
        if(request.getMethod().toUpperCase().equals(GET_URL)) {
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                params.append(name);
                params.append("=");
                params.append(request.getParameter(name));
                params.append("&");
            }
        }
        params.delete(params.length() - 1, params.length());
        return JSON.parseObject(params.toString());
    }

}
