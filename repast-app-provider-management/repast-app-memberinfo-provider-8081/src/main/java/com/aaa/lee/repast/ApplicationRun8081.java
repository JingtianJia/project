package com.aaa.lee.repast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/10 10:51
 * @Description
 *      @MapperScan:必须要给我添加成tk.mybatis包中的注解
 *      否则项目中所使用的通用mapper无法注入，直接调用报错
 **/
@SpringBootApplication
@MapperScan("com.aaa.lee.repast.mapper")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ApplicationRun8081 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8081.class, args);
    }

}
