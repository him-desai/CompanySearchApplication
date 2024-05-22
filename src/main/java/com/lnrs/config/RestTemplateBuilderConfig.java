package com.lnrs.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateBuilderConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // You can customize the RestTemplate here
        return builder.build();
    }
}
