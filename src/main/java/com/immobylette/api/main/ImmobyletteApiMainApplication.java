package com.immobylette.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class ImmobyletteApiMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImmobyletteApiMainApplication.class, args);
	}

}
