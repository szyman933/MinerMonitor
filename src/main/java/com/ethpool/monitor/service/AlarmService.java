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

import static com.ethpool.monitor.utilities.Converters.LocalDateTimeToSeconds;


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

        return minerStatsData.getActiveWorkers() == 0 || minerStatsData.getCurrentHashrate() < hashRateThreshold || minerStatsData.getReportedHashrate() == 0L;
    }

    int getAlarmReason(MinerStatsData minerStatsData) {

        if (minerStatsData.getActiveWorkers() == 0 || minerStatsData.getReportedHashrate() == 0L) {
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
        return Pair.of(level, alarmName);
    }

    public void updateAllPendingAlarms() {

        List<Alarm> pending = getPendingAlarms();

        if (!pending.isEmpty()) {
            log.info("Updating pending alarms duration time");

            pending.stream().filter(a -> a.getAlarmRegistration() != null).forEach(a -> {

                long difference = LocalDateTimeToSeconds(LocalDateTime.now()) - LocalDateTimeToSeconds(a.getAlarmRegistration());

                a.setAlarmDurationSeconds(difference);

                dbService.updateAlarmDuration(a);

            });

        } else {

            log.info("No pending alarms to update");
        }


    }

    List<Alarm> alarmExist(String name) {

        return dbService.alarmExist(name);
    }

    List<Alarm> getPendingAlarms() {

        return dbService.getPendingAlarms();
    }

    public void process(MinerStatsDTO minerStatsDTO) {
        log.debug("# Starting process data, looking for alarms ..");

        MinerStatsData minerStatsData = minerStatsDataMapper.mapToMinerStatsData(minerStatsDTO.getMinerStatsDataDTO());

        boolean anyAlarm = checkAlarm(minerStatsData);

        if (anyAlarm) {

            log.info("Alarm detected!");

            int reason = getAlarmReason(minerStatsData);

            Pair<Integer, String> alarmDetails = getAlarmNameAndLevel(reason);

            log.info("Alarm details : level -> {} , name -> {}  ", alarmDetails.getFirst(), alarmDetails.getSecond());

            List<Alarm> existingAlarm = alarmExist(alarmDetails.getSecond());

            if (existingAlarm.isEmpty()) {

                log.info("Alarm doesn't exist in database, saving alarm !");

                Alarm newAlarm = new Alarm(LocalDateTime.now(), 0L, null, minerStatsData.getServerTime(), alarmDetails.getSecond(), alarmDetails.getFirst());

                log.info("Alarm entity : {}", newAlarm);

                dbService.saveAlarm(newAlarm);

            } else {

                log.info("Alarm exist and is pending. {} ", existingAlarm);

            }

        } else {
            log.info("No alarms detected !");
        }

        //   updateAllPendingAlarms();

        log.debug("# Processing data finished ");
    }

}
