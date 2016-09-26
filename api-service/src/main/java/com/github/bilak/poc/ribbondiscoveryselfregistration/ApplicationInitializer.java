package com.github.bilak.poc.ribbondiscoveryselfregistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

/**
 * Created by lvasek on 26/09/2016.
 */
@Component
public class ApplicationInitializer implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

	private RestOperations restTemplate;

	@Autowired
	public void setRestTemplate(RestOperations restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		ResponseEntity<String> helloResponse = restTemplate.getForEntity("http://api-service/hello-controller/{name}", String.class, "my friend");
		logger.debug("Response from controller is {}", helloResponse.getBody());
	}
}