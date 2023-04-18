package com.xxxwork.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.xxxwork.demo"})
@EnableDiscoveryClient
@SpringBootApplication
public class YyyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YyyServiceApplication.class, args);
	}

}