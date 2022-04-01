package com.ethpool.monitor.repository;

import com.ethpool.monitor.domain.MinerStatsData;
import com.ethpool.monitor.domain.MinerStatsDataDTO;
import com.ethpool.monitor.mappers.MinerStatsDataMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MinerStatsDataDAOTest {

    @Autowired
    MinerStatsDataDAO minerStatsDataDAO;

    @Autowired
    MinerStatsDataMapper minerStatsDataMapper;

    @Test
    void shouldSaveMinerStatsData() {

        MinerStatsDataDTO minerStatsDataDTO = new MinerStatsDataDTO(
                1648830600L,
                1648830541L,
                21823117L,
                23861294L,
                20,
                0,
                0,
                1,
                21485426.8844086,
                11038265548343538L,
                1.9508378157457795e-7,
                0.0006676293769794707,
                1.4376623024888305e-8);

        MinerStatsData minerStatsData = minerStatsDataMapper.mapToMinerStatsData(minerStatsDataDTO);

        minerStatsDataDAO.save(minerStatsData);

        int ident = minerStatsData.getId();

        List<MinerStatsData> readMinerStatsData = minerStatsDataDAO.findById(ident);

        assertEquals(1,readMinerStatsData.size());

        minerStatsDataDAO.deleteById(ident);
    }

}