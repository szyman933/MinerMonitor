package com.ethpool.monitor.service;


import com.ethpool.monitor.domain.Alarm;
import com.ethpool.monitor.domain.MinerStatsDTO;
import com.ethpool.monitor.domain.MinerStatsData;
import com.ethpool.monitor.mappers.MinerStatsDataMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AlarmService {

    private static final Logger log = LoggerFactory.getLogger(AlarmService.class);

    @Autowired
    DBService dbService;

    @Autowired
    MinerStatsDataMapper minerStatsDataMapper;

    private static final int WORKER_OFFLINE = 1;
    private static final int LOW_PERFORMANCE = 2;
    private static final int NO_DATA_FROM_POOL = 3;
    private static final int UNKNOWN = 99;


    private static final int INFO = 1;
    private static final int MEDIUM = 2;
    private static final int HIGH = 3;

    private Long hashRateThreshold = 10000000L;

    boolean checkAlarm(MinerStatsData minerStatsData) {

        return minerStatsData.getActiveWorkers() == 0 || minerStatsData.getCurrentHashrate() < hashRateThreshold;
    }

    int getAlarmReason(MinerStatsData minerStatsData) {

        if (minerStatsData.getActiveWorkers() == 0) {
            return WORKER_OFFLINE;
        } else if (minerStatsData.getCurrentHashrate() < hashRateThreshold) {
            return LOW_PERFORMANCE;
        } else {
            return UNKNOWN;
        }

    }

    public Pair<Integer, String> getAlarmNameAndLevel(int reason) {
        String alarmName;
        int level;
        switch (reason) {

            case WORKER_OFFLINE -> {
                alarmName = "Worker offline";
                level = HIGH;
            }

            case LOW_PERFORMANCE -> {
                alarmName = "Low hashrate, poor performance";
                level = INFO;
            }

            case NO_DATA_FROM_POOL -> {
                alarmName = "No fresh data from pool";
                level = MEDIUM;
            }

            case UNKNOWN -> {
                alarmName = "Unknown for now";
                level = HIGH;
            }

            default -> throw new IllegalArgumentException("Unrecognized alarm reason !");

        }
        return Pair.of(level,alarmName);
    }


    void updateAlarm() {
        //TODO
    }

    boolean alarmExist(MinerStatsData minerStatsData, String name) {
        List<Alarm> theList = dbService.alarmExist(minerStatsData.getServerTime(), name);

        return !theList.isEmpty();
    }


    List<Alarm> getPendingAlarms() {

        return dbService.getPendingAlarms();
    }

    void process(MinerStatsDTO minerStatsDTO) {
        log.debug("# Starting process data, looking for alarms ..");
        MinerStatsData minerStatsData = minerStatsDataMapper.mapToMinerStatsData(minerStatsDTO.getMinerStatsDataDTO());

        boolean anyAlarm = checkAlarm(minerStatsData);

        if (anyAlarm) {

            log.info("Alarm detected!");

            int reason = getAlarmReason(minerStatsData);

            Pair<Integer, String> alarmDetails = getAlarmNameAndLevel(reason);

            log.info("Alarm details : level {} , name {}  ",  alarmDetails.getFirst(), alarmDetails.getSecond());

            boolean existingAlarm = alarmExist(minerStatsData, alarmDetails.getSecond());

            if (!existingAlarm) {

                log.info("Alarm doesn't exist in database, saving alarm !");

                Alarm newAlarm = new Alarm(LocalDateTime.now(), 0L, null, minerStatsData.getServerTime(), alarmDetails.getSecond(), alarmDetails.getFirst());

                log.info("Alarm entity : {}", newAlarm);

                dbService.saveAlarm(newAlarm);

            } else {
                log.info("Alarm exist and is pending");
                //TODO update alarm when exist
            }

        }
        log.debug("# Processing data finished ");
    }
}
