package com.ethpool.monitor.client;

import com.ethpool.monitor.configuration.CoreConfig;
import com.ethpool.monitor.domain.MinerStatsDTO;
import com.ethpool.monitor.domain.StatsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Component
public class EthPoolClient {

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
        return new StatsResponseDTO();
    }


    public MinerStatsDTO getMinerStats() {

        URI url = UriComponentsBuilder.fromHttpUrl(coreConfig.getEthPoolEndpoint() + "/miner/" + coreConfig.getEthAdress() + "/currentStats")
                .build().encode().toUri();

        MinerStatsDTO receivedMinerStats = restTemplate.getForObject(url, MinerStatsDTO.class);

        if (receivedMinerStats != null) {
            return receivedMinerStats;
        }
        return new MinerStatsDTO();
    }

}
