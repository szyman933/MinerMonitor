package com.ethpool.monitor.scheduler;


import com.ethpool.monitor.client.EthPoolClient;
import com.ethpool.monitor.domain.MinerStatsDTO;
import com.ethpool.monitor.domain.StatsResponseDTO;
import com.ethpool.monitor.service.AlarmService;
import com.ethpool.monitor.service.DBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


@Component
public class StatsResponseScheduler {

    public static final Logger log = LoggerFactory.getLogger(StatsResponseScheduler.class);


    @Autowired
    EthPoolClient ethPoolClient;

    @Autowired
    DBService dbService;

    @Autowired
    AlarmService alarmService;


    @Scheduled(fixedDelayString = "${minermonitor.getpoolstats.interval}")
    public void getStatsResponseAndSave() {

        StatsResponseDTO poolStats = ethPoolClient.getStatsResponse();

        log.info("Status pobierania statystyk EthPool : {}", poolStats.getStatus());

        dbService.savePoolStats(poolStats.getDataDTO().getPoolStatsDTO());

        dbService.savePrice(poolStats.getDataDTO().getPriceDTO());

    }


    @Scheduled(fixedDelayString = "${minermonitor.getminerstats.interval}")
    public void getMinerStatsAndSave() {

        MinerStatsDTO minerStatsDTO = ethPoolClient.getMinerStats();


        if(minerStatsDTO.getStatus() != null && minerStatsDTO.getMinerStatsDataDTO()!=null){

            log.info("Status pobierania statystyk Minera : {} , aktywne koparki : {} , sredni hashrate : {}", minerStatsDTO.getStatus(), minerStatsDTO.getMinerStatsDataDTO().getActiveWorkers(), minerStatsDTO.getMinerStatsDataDTO().getAverageHashrate());


            if (dbService.existsMinerStatsDataByServerTime(minerStatsDTO.getMinerStatsDataDTO())) {

                log.warn("Duplicate MinerStatsData, skipping !");

            } else {
                log.info("Fresh MinerStatsData, saving data");

                dbService.saveMinerStatsData(minerStatsDTO.getMinerStatsDataDTO());

                alarmService.process(minerStatsDTO);
            }

        }else {

            log.warn("No data in miner stats !");

        }


        alarmService.updateAllPendingAlarms();
    }


}
