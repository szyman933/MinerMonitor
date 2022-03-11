package com.ethpool.monitor.mappers;

import com.ethpool.monitor.domain.PoolStats;
import com.ethpool.monitor.domain.PoolStatsDTO;
import org.springframework.stereotype.Component;

@Component
public class PoolStatsMapper {


    public PoolStats mapToPoolStats(final PoolStatsDTO poolStatsDTO) {

        return new PoolStats(poolStatsDTO.getHashRate(),
                poolStatsDTO.getMiners(),
                poolStatsDTO.getWorkers(),
                poolStatsDTO.getBlocksPerHour());

    }


    public PoolStatsDTO mapToPoolStatsDTO(final PoolStats poolStats) {

        return new PoolStatsDTO(poolStats.getHashRate(),
                poolStats.getMiners(),
                poolStats.getWorkers(),
                poolStats.getBlocksPerHour());

    }
}
