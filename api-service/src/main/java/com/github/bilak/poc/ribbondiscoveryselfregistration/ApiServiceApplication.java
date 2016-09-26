package com.github.bilak.poc.ribbondiscoveryselfregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by lvasek on 26/09/2016.
 */
@SpringBootApplication
@EnableEurekaClient
public class ApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServiceApplication.class, args);
	}
}
