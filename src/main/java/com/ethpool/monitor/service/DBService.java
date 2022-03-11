package com.ethpool.monitor.service;

import com.ethpool.monitor.domain.PoolStats;
import com.ethpool.monitor.domain.PoolStatsDTO;
import com.ethpool.monitor.domain.Price;
import com.ethpool.monitor.domain.PriceDTO;
import com.ethpool.monitor.mappers.PoolStatsMapper;
import com.ethpool.monitor.mappers.PriceMapper;
import com.ethpool.monitor.repository.PoolStatsDAO;
import com.ethpool.monitor.repository.PriceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {


    @Autowired
    private PoolStatsDAO poolStatsDAO;

    @Autowired
    private PriceDAO priceDAO;

    @Autowired
    private PoolStatsMapper poolStatsMapper;

    @Autowired
    private PriceMapper priceMapper;


    public PoolStats savePoolStats(final PoolStatsDTO poolStatsDto) {

        return poolStatsDAO.save(poolStatsMapper.mapToPoolStats(poolStatsDto));

    }

    public Price savePrice(final PriceDTO priceDto) {

        return priceDAO.save(priceMapper.mapToPrice(priceDto));

    }


}
