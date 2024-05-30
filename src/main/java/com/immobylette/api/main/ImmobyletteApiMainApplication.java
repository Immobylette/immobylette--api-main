package com.immobylette.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class ImmobyletteApiMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImmobyletteApiMainApplication.class, args);
	}

}
