package com.ethpool.monitor.scheduler;


import com.ethpool.monitor.client.EthPoolClient;
import com.ethpool.monitor.configuration.CoreConfig;
import com.ethpool.monitor.domain.StatsResponseDTO;
import com.ethpool.monitor.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StatsResponseScheduler {

    @Autowired
     CoreConfig coreConfig;

    @Autowired
     EthPoolClient ethPoolClient;

    @Autowired
     DBService dbService;


    @Scheduled(fixedDelayString = "${minermonitor.accdata.interval}")
    void getStatsResponseAndSave(){

        StatsResponseDTO poolStats = ethPoolClient.getStatsResponse();

        dbService.savePoolStats(poolStats.getDataDTO().getPoolStatsDTO());

        dbService.savePrice(poolStats.getDataDTO().getPriceDTO());


        System.out.println(new Date());

        System.out.println(poolStats.getStatus());

    }


}
