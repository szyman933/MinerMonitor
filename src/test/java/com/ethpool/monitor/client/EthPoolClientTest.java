package com.ethpool.monitor.client;

import com.ethpool.monitor.configuration.CoreConfig;
import com.ethpool.monitor.domain.MinerStatsDTO;
import com.ethpool.monitor.domain.MinerStatsDataDTO;
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
    private EthPoolClient ethPoolClient ;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CoreConfig coreConfig;



    @BeforeEach
    void init(){
        Mockito.when(coreConfig.getEthPoolEndpoint()).thenReturn("http://test.com");
        Mockito.when(coreConfig.getEthAdress()).thenReturn("0x0000000000000");
    }


    @Test
    void shouldFetchMinerStatsData() throws URISyntaxException {

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

        MinerStatsDTO minerStatsDTO = new MinerStatsDTO("OK",minerStatsDataDTO);

        Mockito.when(restTemplate.getForObject(url, MinerStatsDTO.class)).thenReturn(minerStatsDTO);

        MinerStatsDTO fetchedMinerStats = ethPoolClient.getMinerStats();

        assertEquals(20,fetchedMinerStats.getMinerStatsDataDTO().getValidShares());
        assertEquals(1,fetchedMinerStats.getMinerStatsDataDTO().getActiveWorkers());


    }

//TODO
    //test EthPoolClient getStatsResponse method


}