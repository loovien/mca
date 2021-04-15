package com.dtx.lcn.wealth;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDistributedTransaction
public class WealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(WealthApplication.class, args);
    }

}
