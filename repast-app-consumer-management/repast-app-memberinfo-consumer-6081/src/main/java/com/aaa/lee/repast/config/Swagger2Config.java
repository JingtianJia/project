package com.aaa.lee.repast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/10 11:26
 * @Description
 *      配置config的配置类，并没有实际意义
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 描述该网站的信息
                .select() // 查询对外所要提供的接口都是什么(consumer项目的Controller)
                .apis(RequestHandlerSelectors.basePackage("com.aaa.lee.repast.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("订单项目 服务接口")
                .description("提供了项目中所有接口信息")
                .contact(new Contact("Seven Lee", "http://www.seven.com", "sevenLee@Gmail.com"))
                .version("1.0 beta")
                .build();
    }

}
