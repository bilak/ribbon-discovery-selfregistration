package com.github.bilak.poc.ribbondiscoveryselfregistration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lvasek on 26/09/2016.
 */
@RestController
@RequestMapping("/hello-controller")
public class HelloController {

	@GetMapping(value = "/{name}")
	ResponseEntity<String> hello(@PathVariable String name) {
		return ResponseEntity.ok(String.format("Hello %s", name));
	}
}
