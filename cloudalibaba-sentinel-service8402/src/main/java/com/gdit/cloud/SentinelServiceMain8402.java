package com.gdit.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelServiceMain8402 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelServiceMain8402.class,args);
    }

}
