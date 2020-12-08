package com.merkq.frontmarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.merkq.frontmarket")
public class AppConfig {

	RestTemplate template;
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
