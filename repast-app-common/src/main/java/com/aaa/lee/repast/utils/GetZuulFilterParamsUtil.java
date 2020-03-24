package com.aaa.lee.repast.utils;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import static com.aaa.lee.repast.staticstatus.RequestProperties.UTF8_ENCODING;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/18 11:43
 * @Description
 *      controller接收zuulFilter中所传递的参数
 **/
public class GetZuulFilterParamsUtil {

    public static Map getParams(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            String jsonString = StreamUtils.copyToString(inputStream, Charset.forName(UTF8_ENCODING));
            Map map = JSONUtil.toObject(jsonString, Map.class);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
