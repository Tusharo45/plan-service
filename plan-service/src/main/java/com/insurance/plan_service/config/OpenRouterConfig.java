package com.insurance.plan_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class OpenRouterConfig {

    @Bean
    public RestClient restClient() {

        return RestClient.builder()
                .baseUrl("https://openrouter.ai/api/v1")
                .build();
    }

}