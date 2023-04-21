package com.xxxwork.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages = {"com.xxxwork.demo"})
@EnableFeignClients
@EnableDiscoveryClient
@EnableAsync
@SpringBootApplication
public class XxxServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(XxxServiceApplication.class, args);
	}

}
