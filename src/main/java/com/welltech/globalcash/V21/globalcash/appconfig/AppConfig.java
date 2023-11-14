package com.welltech.globalcash.V21.globalcash.appconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig{

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}

