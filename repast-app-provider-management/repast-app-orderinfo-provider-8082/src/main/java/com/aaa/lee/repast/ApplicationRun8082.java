package com.aaa.lee.repast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Company
 * @Author Gotta
 * @Date 2020/3/25
 * @Description
 **/
@SpringBootApplication
@MapperScan("com.aaa.lee.repast.mapper")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ApplicationRun8082 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8082.class, args);
    }
}
