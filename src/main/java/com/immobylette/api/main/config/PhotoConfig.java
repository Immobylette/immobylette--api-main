package com.immobylette.api.main.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "photo")
@Getter
@Setter
public class PhotoConfig {
    private String url;
}
