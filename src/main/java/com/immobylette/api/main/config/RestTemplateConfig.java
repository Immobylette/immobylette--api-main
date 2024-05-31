package com.immobylette.api.main.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class RestTemplateConfig {
    private final PhotoConfig photoConfig;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(this.photoConfig.getUrl())
                .defaultHeader("X-Api-Key", photoConfig.getApiKey())
                .build();
    }
}