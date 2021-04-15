package com.dtx.lcn.payment;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.util.UUID;

@EnableFeignClients
@SpringBootApplication
@EnableDistributedTransaction
public class PaymentApplication {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        SpringApplication springApplication = new SpringApplication(PaymentApplication.class);
        springApplication.setBanner((environment, sourceClass, out) -> {
            out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            out.println("+++++++++++++++++++ hello world +++++++++++++++++++++++");
            out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        });
        springApplication.setBannerMode(Banner.Mode.LOG);
        springApplication.run(args);
    }
}
