package com.ethpool.monitor.dao;

import com.ethpool.monitor.domain.PoolStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PoolStatsDAOTest {

    @Autowired
    private PoolStatsDAO poolStatsDAO;

    @Test
    void savePoolStats() {
        //given
        PoolStats poolStats = new PoolStats(308478878941184L, 400249L, 1522905L, 87.21);
        //when
        poolStatsDAO.save(poolStats);
        //Then
        int id = poolStats.getId();
        List<PoolStats> readPoolStats = poolStatsDAO.findById(id);
        assertEquals(1, readPoolStats.size());

        //CleanUp
        poolStatsDAO.deleteById(id);
    }
}