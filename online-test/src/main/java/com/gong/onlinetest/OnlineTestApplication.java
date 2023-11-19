package com.gong.onlinetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class OnlineTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineTestApplication.class, args);
    }

}
