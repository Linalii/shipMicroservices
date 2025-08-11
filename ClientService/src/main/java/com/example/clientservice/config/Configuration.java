package com.example.clientservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8181/api/ships")
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("X-API-KEY", "HBkjbid328udb23bUHBUHBD");
                }).build();
    }
}
