package com.ethpool.monitor.client;

import com.ethpool.monitor.configuration.CoreConfig;
import com.ethpool.monitor.domain.MinerStatsDTO;
import com.ethpool.monitor.domain.StatsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class EthPoolClient {

    @Autowired
    CoreConfig coreConfig;

    @Autowired
    private RestTemplate restTemplate;


    public StatsResponseDTO getStatsResponse() {

        StatsResponseDTO receivedPoolStats = restTemplate.getForObject(coreConfig.getEthPoolEndpoint() + "/poolStats", StatsResponseDTO.class);

        if (receivedPoolStats != null) {
            return receivedPoolStats;
        }
        return new StatsResponseDTO();
    }


    public MinerStatsDTO getMinerStats() {

        MinerStatsDTO receivedMinerStats = restTemplate.getForObject(coreConfig.getEthPoolEndpoint() + "/miner/" + coreConfig.getEthAdress() + "/currentStats", MinerStatsDTO.class);

        if (receivedMinerStats != null) {
            return receivedMinerStats;
        }
        return new MinerStatsDTO();
    }

}
