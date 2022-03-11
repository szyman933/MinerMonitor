package com.ethpool.monitor.controller;


import com.ethpool.monitor.client.EthPoolClient;
import com.ethpool.monitor.domain.DataDTO;
import com.ethpool.monitor.domain.StatsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin()
@RestController
@RequestMapping("/v1/poolStats")
public class PoolStatsController {


    @Autowired
    EthPoolClient ethPoolClient;

    @GetMapping(value = "getPoolStats")
    void getPoolStats() {

        StatsResponseDTO poolStats = ethPoolClient.getStatsResponse();

        DataDTO dataDTO = poolStats.getDataDTO();


    }


}
