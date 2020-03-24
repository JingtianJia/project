package com.aaa.lee.repast.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

@Component
public class DynamicTimedTask {
      private static final Logger logger = LoggerFactory.getLogger(DynamicTimedTask.class);

      //利用创建好的调度类统一管理
      //@Autowired
      //@Qualifier("myThreadPoolTaskScheduler")
      //private ThreadPoolTaskScheduler myThreadPoolTaskScheduler;


      //接受任务的返回结果
      private ScheduledFuture<?> future;

      @Autowired
      private ThreadPoolTaskScheduler threadPoolTaskScheduler;



      //实例化一个线程池任务调度类,可以使用自定义的ThreadPoolTaskScheduler
      @Bean
      public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
          ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
          return new ThreadPoolTaskScheduler();
      }


      /**
       * 启动定时任务
       * @return
       */
      public ScheduledFuture startCron(String cron , Runnable runnable) {
          return threadPoolTaskScheduler.schedule(runnable, new CronTrigger(cron));
      }

      /**
       * 停止定时任务
       * @return
       */
      public boolean stopCron(ScheduledFuture future) {
          boolean flag = false;
          if (future != null) {
              boolean cancel = future.cancel(true);
              if (cancel){
                  flag = true;
                  logger.info("定时任务停止成功！！！");
              }else {
                  logger.info("定时任务停止失败！！！");
              }
          }else {
              flag = true;
              logger.info("定时任务已经停止！！！");
          }
          return flag;
      }

  }