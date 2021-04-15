package com.learn.mqtx;

import com.learn.mqtx.service.MQConsumeService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.dtx.lcn.payment.dao")
public class MqtxApplication {

    public static void main(String[] args) throws MQClientException {
        SpringApplication springApplication = new SpringApplication(MqtxApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext run = springApplication.run();
        MQConsumeService bean = run.getBean(MQConsumeService.class);
        bean.msgListener();
    }

}
