package com.ethpool.monitor.service;

import com.ethpool.monitor.domain.*;
import com.ethpool.monitor.mappers.MinerStatsDataMapper;
import com.ethpool.monitor.mappers.PoolStatsMapper;
import com.ethpool.monitor.mappers.PriceMapper;
import com.ethpool.monitor.repository.AlarmDAO;
import com.ethpool.monitor.repository.MinerStatsDataDAO;
import com.ethpool.monitor.repository.PoolStatsDAO;
import com.ethpool.monitor.repository.PriceDAO;
import com.ethpool.monitor.utilities.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DBService {


    @Autowired
    private PoolStatsDAO poolStatsDAO;

    @Autowired
    private PriceDAO priceDAO;

    @Autowired
    private MinerStatsDataDAO minerStatsDataDAO;

    @Autowired
    private PoolStatsMapper poolStatsMapper;

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private MinerStatsDataMapper minerStatsDataMapper;

    @Autowired
    private AlarmDAO alarmDAO;


    public PoolStats savePoolStats(final PoolStatsDTO poolStatsDto) {

        return poolStatsDAO.save(poolStatsMapper.mapToPoolStats(poolStatsDto));

    }

    public Price savePrice(final PriceDTO priceDto) {

        return priceDAO.save(priceMapper.mapToPrice(priceDto));

    }

    public MinerStatsData saveMinerStatsData(final MinerStatsDataDTO minerStatsDataDTO) {

        return minerStatsDataDAO.save(minerStatsDataMapper.mapToMinerStatsData(minerStatsDataDTO));
    }

    public boolean existsMinerStatsDataByServerTime(final MinerStatsDataDTO minerStatsDataDTO) {

        return minerStatsDataDAO.existsByServerTime(Converters.convertsUnixTimestampToLocalDateTime(minerStatsDataDTO.getServerTime()));
    }

    public Alarm saveAlarm(Alarm alarm) {

        return alarmDAO.save(alarm);
    }

    public List<Alarm> getPendingAlarms() {
        return alarmDAO.pendingAlarms();
    }

    public List<Alarm> alarmExist(String name) {
        return alarmDAO.alarmExist(name);
    }

    public void updateAlarmDuration(Alarm alarm) {
        alarmDAO.updateAlarmDuration(alarm.getId(), alarm.getAlarmDurationSeconds());
    }
}
