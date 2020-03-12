package com.aaa.lee.repast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/10 11:35
 * @Description
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.aaa.lee.repast"})
public class ApplicationRun6081 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6081.class, args);
    }

}
