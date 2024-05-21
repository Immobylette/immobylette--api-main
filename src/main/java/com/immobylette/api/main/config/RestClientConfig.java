package com.immobylette.api.main.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
@AllArgsConstructor
public class RestClientConfig {
    private final PhotoConfig photoConfig;
    private final AuthConfig authConfig;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
            .requestFactory(new HttpComponentsClientHttpRequestFactory())
            .baseUrl(this.photoConfig.getUrl())
                .defaultHeader("X-Api-Key", authConfig.getApiKey())
            .build();
    }

}
