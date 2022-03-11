package com.ethpool.monitor.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreConfig {

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Value("${ethpool.api.endpoint}")
    private String ethPoolEndpoint;


    public String getEthPoolEndpoint() {
        return ethPoolEndpoint;
    }
}
