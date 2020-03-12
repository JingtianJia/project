package com.aaa.lee.repast.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/12 12:34
 * @Description
 *      获取spring容器工具类
 *      适用于项目一启动需要去加载自己的配置类(不是这些配置类redis,es)
 *          给springboot多写的配置类
 **/
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        Lock lock = READ_WRITE_LOCK.readLock();
        lock.lock();
        try {
            if(null != applicationContext) {
                return applicationContext;
            } else {
                return null;
            }
        } finally {
            lock.unlock();
        }
    }

}
