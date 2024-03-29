package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class Hystrix_Dashboard {
    public static void main(String[] args) {
        SpringApplication.run(Hystrix_Dashboard.class, args);
    }
}
