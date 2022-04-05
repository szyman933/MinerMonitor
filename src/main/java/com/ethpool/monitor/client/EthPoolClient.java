package com.ethpool.monitor.client;

import com.ethpool.monitor.configuration.CoreConfig;
import com.ethpool.monitor.domain.MinerStatsDTO;
import com.ethpool.monitor.domain.StatsResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Component
public class EthPoolClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EthPoolClient.class);

    @Autowired
    CoreConfig coreConfig;

    @Autowired
    private RestTemplate restTemplate;


    public StatsResponseDTO getStatsResponse() {

        URI url = UriComponentsBuilder.fromHttpUrl(coreConfig.getEthPoolEndpoint() + "/poolStats")
                .build().encode().toUri();

        StatsResponseDTO receivedPoolStats = restTemplate.getForObject(url, StatsResponseDTO.class);

        if (receivedPoolStats != null) {
            return receivedPoolStats;
        }
        LOGGER.warn("StatsResponse is null, creating empty statsResponse");
        return new StatsResponseDTO();
    }


    public MinerStatsDTO getMinerStats() {

        URI url = UriComponentsBuilder.fromHttpUrl(coreConfig.getEthPoolEndpoint() + "/miner/" + coreConfig.getEthAdress() + "/currentStats")
                .build().encode().toUri();

        MinerStatsDTO receivedMinerStats = restTemplate.getForObject(url, MinerStatsDTO.class);

        if (receivedMinerStats != null) {
            return receivedMinerStats;
        }
        LOGGER.warn("MinerStatsDTO is null, creating empty minerStats");
        return new MinerStatsDTO();
    }

}
