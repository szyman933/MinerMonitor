package com.ethpool.monitor.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@Configuration
public class CoreConfig {

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Value("${ethpool.api.endpoint}")
    private String ethPoolEndpoint;

    @Value("${ethpool.api.miner}")
    private String ethAdress;

    @Value("${minermonitor.accdata.interval}")
    private Long interval;




    public String getEthPoolEndpoint() {
        return ethPoolEndpoint;
    }

    public Long getInterval() {
        return interval;
    }

    public String getEthAdress() {
        return ethAdress;
    }
}
