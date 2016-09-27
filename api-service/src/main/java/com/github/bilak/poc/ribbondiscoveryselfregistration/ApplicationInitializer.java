package com.github.bilak.poc.ribbondiscoveryselfregistration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaEvent;
import com.netflix.discovery.EurekaEventListener;
import com.netflix.discovery.StatusChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

/**
 * Created by lvasek on 26/09/2016.
 */
@Component
public class ApplicationInitializer implements ApplicationListener<InstanceRegisteredEvent<?>> {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

	private RestOperations restTemplate;

	@Autowired
	private EurekaClient discoveryClient;

	@Autowired
	public void setRestTemplate(@LoadBalanced RestOperations restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public void onApplicationEvent(InstanceRegisteredEvent<?> event) {
		EurekaEventListener initializerListener = new InitializerListener(discoveryClient, restTemplate);
		discoveryClient.registerEventListener(initializerListener);
	}

	public static class InitializerListener implements EurekaEventListener {

		private EurekaClient eurekaClient;
		private RestOperations restTemplate;

		public InitializerListener(EurekaClient eurekaClient, RestOperations restTemplate) {
			this.eurekaClient = eurekaClient;
			this.restTemplate = restTemplate;
		}

		@Override
		public void onEvent(EurekaEvent event) {
			if (event instanceof StatusChangeEvent) {
				if (((StatusChangeEvent) event).getStatus().equals(InstanceInfo.InstanceStatus.UP)) {
					ResponseEntity<String> helloResponse = restTemplate.getForEntity("http://api-service/hello-controller/{name}", String.class, "my friend");
					logger.debug("Response from controller is {}", helloResponse.getBody());
					eurekaClient.unregisterEventListener(this);
				}
			}
		}
	}

}
