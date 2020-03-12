package com.aaa.lee.repast.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/11 10:22
 * @Description
 *      自定义登录日志注解
 *      因为在java中，有属性，有类，有方法....
 *      注解如果想要正常使用，则必须要配置该注解实现的地方
 *      @Target--->这个就是自定义注解所要使用的范围目标(值可以有多个)
 *
 *      @Value("${redis_key}")
 *      private String redisKey;
 *
 *      @Id
 *      @Column
 *      都是标识属性的
 *
 * @Retention:注解在什么时候会被加载
 *      标识在项目运行的时候就会被加载
 *
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginLogAnnotation {

    /**
     * @author Seven Lee
     * @description
     *      要执行的操作类型:
     *          eg: 删除操作，下单操作，登录操作...
     * @param []
     * @date 2020/3/11
     * @return java.lang.String
     * @throws
    **/
    String operationType() default ""; //default:叫做默认值，如果传递operationType的时候，直接使用的是默认值

    /**
     * @author Seven Lee
     * @description
     *      具体要执行的操作:
     *          eg:删除操作--->删除用户，清空购物车，删除商品...
     * @param []
     * @date 2020/3/11
     * @return java.lang.String
     * @throws
    **/
    String operationName() default "";

}
