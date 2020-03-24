package com.aaa.lee.repast.utils;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/18 11:28
 * @Description
 *      把参数发送给目标controller
 **/
public class SendParams2ControllerUtil {

    public static void sendParams(JSONObject paramsObject, RequestContext rcx, HttpServletRequest request) {
        String body = paramsObject.toString();
        final byte[] bodyBytes = body.getBytes();
        rcx.setRequest(new HttpServletRequestWrapper(request){
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new ServletInputStreamWrapper(bodyBytes);
            }
            //判断String长度
            @Override
            public int getContentLength() {
                return bodyBytes.length;
            }

            @Override
            public long getContentLengthLong() {
                return bodyBytes.length;
            }
        });
    }

}
