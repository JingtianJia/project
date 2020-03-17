package com.aaa.lee.repast.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/17 10:52
 * @Description
 *      让spring知道需要修改请求头，把请求头即可以接收文件类型，也可以接收普通表单类型
 **/
@Configuration
public class FeignMultiPartConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

}
