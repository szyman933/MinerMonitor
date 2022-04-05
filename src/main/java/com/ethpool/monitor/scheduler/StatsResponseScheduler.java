package com.ethpool.monitor.scheduler;


import com.ethpool.monitor.client.EthPoolClient;
import com.ethpool.monitor.configuration.CoreConfig;
import com.ethpool.monitor.domain.MinerStatsDTO;
import com.ethpool.monitor.domain.StatsResponseDTO;
import com.ethpool.monitor.service.DBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class StatsResponseScheduler {

    public static Logger log = LoggerFactory.getLogger(StatsResponseScheduler.class);

    @Autowired
    CoreConfig coreConfig;

    @Autowired
    EthPoolClient ethPoolClient;

    @Autowired
    DBService dbService;


    @Scheduled(fixedDelayString = "${minermonitor.accdata.interval}")
    void getStatsResponseAndSave() {

        StatsResponseDTO poolStats = ethPoolClient.getStatsResponse();

        log.info("Status pobierania statystyk EthPool : {}", poolStats.getStatus());

        dbService.savePoolStats(poolStats.getDataDTO().getPoolStatsDTO());

        dbService.savePrice(poolStats.getDataDTO().getPriceDTO());




        MinerStatsDTO minerStatsDTO = ethPoolClient.getMinerStats();

        log.info("Status pobierania statystyk Minera : {} , aktywne koparki : {} , sredni hashrate : {}", minerStatsDTO.getStatus(), minerStatsDTO.getMinerStatsDataDTO().getActiveWorkers(), minerStatsDTO.getMinerStatsDataDTO().getAverageHashrate());

        if (dbService.existsMinerStatsDataByServerTime(minerStatsDTO.getMinerStatsDataDTO())) {
            log.warn("Duplicate MinerStatsData, skipping !");
        } else {
            log.info("Fresh MinerStatsData, saving data");
            dbService.saveMinerStatsData(minerStatsDTO.getMinerStatsDataDTO());
        }


    }


}
