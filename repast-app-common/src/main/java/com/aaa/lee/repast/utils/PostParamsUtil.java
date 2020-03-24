package com.aaa.lee.repast.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.IOUtils;

import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/18 11:13
 * @Description
 *      以POST请求方式获取参数
 **/
public class PostParamsUtil {

    public static JSONObject getPrams(RequestContext rcx) {
        String params = null;
        if(!rcx.isChunkedRequestBody()) {// isChunkedRequestBody--->是否request对象中的内容分块
            ServletInputStream inp;
            try {
                inp = rcx.getRequest().getInputStream();// 这就是以POST形式发送过来的数据流
                if(null != inp) {
                    // 传参了
                    params = IOUtils.toString(inp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSON.parseObject(params);
    }

}
