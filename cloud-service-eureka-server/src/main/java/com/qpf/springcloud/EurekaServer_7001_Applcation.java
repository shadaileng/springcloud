package com.qpf.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer_7001_Applcation {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_7001_Applcation.class, args);
    }
}
