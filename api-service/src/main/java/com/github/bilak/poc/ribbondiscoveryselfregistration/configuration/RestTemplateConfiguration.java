package com.github.bilak.poc.ribbondiscoveryselfregistration.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lvasek on 26/09/2016.
 */
@Configuration
public class RestTemplateConfiguration {

	@LoadBalanced
	@Bean
	RestOperations restTemplate() {
		return new RestTemplate();
	}
}
