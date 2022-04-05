package com.ethpool.monitor.client;

import com.ethpool.monitor.configuration.CoreConfig;
import com.ethpool.monitor.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EthPoolClientTest {


    @InjectMocks
    private EthPoolClient ethPoolClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CoreConfig coreConfig;


    @BeforeEach
    void init() {
        Mockito.when(coreConfig.getEthPoolEndpoint()).thenReturn("http://test.com");
    }


    @Test
    void shouldFetchMinerStatsData() throws URISyntaxException {
        //given
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

        URI url = new URI("http://test.com/miner/0x0000000000000/currentStats");

        MinerStatsDTO minerStatsDTO = new MinerStatsDTO("OK", minerStatsDataDTO);

        Mockito.when(coreConfig.getEthAdress()).thenReturn("0x0000000000000");
        Mockito.when(restTemplate.getForObject(url, MinerStatsDTO.class)).thenReturn(minerStatsDTO);
        //when
        MinerStatsDTO fetchedMinerStats = ethPoolClient.getMinerStats();
        //then
        assertEquals(20, fetchedMinerStats.getMinerStatsDataDTO().getValidShares());
        assertEquals(1, fetchedMinerStats.getMinerStatsDataDTO().getActiveWorkers());

    }

    @Test
    void shouldFetchEmptyMinerStatsData() throws URISyntaxException {

        URI url = new URI("http://test.com/miner/0x0000000000000/currentStats");

        Mockito.when(coreConfig.getEthAdress()).thenReturn("0x0000000000000");
        Mockito.when(restTemplate.getForObject(url, MinerStatsDTO.class)).thenReturn(null);

        MinerStatsDTO fetchedMinerStats = ethPoolClient.getMinerStats();

        assertNotNull(fetchedMinerStats);
    }

    @Test
    void shouldFetchStatusResponse() throws URISyntaxException {
        //given
        PoolStatsDTO poolStatsDTO = new PoolStatsDTO(282981168054272L, 320762L, 1389641L, 76.25);

        PriceDTO priceDTO = new PriceDTO("2022-04-05T15:57:49.000Z", 3461.6101074219, 0.0756006092, 3169.4799804688);

        DataDTO dataDTO = new DataDTO(poolStatsDTO, priceDTO);

        StatsResponseDTO statsResponseDTO = new StatsResponseDTO("OK", dataDTO);

        URI url = new URI("http://test.com/poolStats");


        Mockito.when(restTemplate.getForObject(url, StatsResponseDTO.class)).thenReturn(statsResponseDTO);
        //when
        StatsResponseDTO fetchedStatsResponseDTO = ethPoolClient.getStatsResponse();
        //then
        assertEquals(fetchedStatsResponseDTO.getStatus(), "OK");
        assertEquals(fetchedStatsResponseDTO.getDataDTO().getPoolStatsDTO().getMiners(), 320762L);
        assertEquals(fetchedStatsResponseDTO.getDataDTO().getPriceDTO().getEuroPrice(), 3169.4799804688);

    }

    @Test
    void shouldFetchEmptyStatusResponse() throws URISyntaxException {
        //given
        URI url = new URI("http://test.com/poolStats");

        Mockito.when(restTemplate.getForObject(url, StatsResponseDTO.class)).thenReturn(null);
        //when
        StatsResponseDTO fetchedStatsResponseDTO = ethPoolClient.getStatsResponse();
        //then
        assertNotNull(fetchedStatsResponseDTO);
    }
}