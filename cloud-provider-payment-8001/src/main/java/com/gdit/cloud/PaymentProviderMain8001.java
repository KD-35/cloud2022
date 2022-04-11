package com.gdit.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentProviderMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentProviderMain8001.class,args);
    }
}
