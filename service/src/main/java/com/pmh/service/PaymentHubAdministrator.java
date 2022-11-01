package com.pmh.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PaymentHubAdministrator {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHubAdministrator.class, args);
    }
}
