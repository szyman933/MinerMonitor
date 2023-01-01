package com.ethpool.monitor.controller;


import com.ethpool.monitor.client.EthPoolClient;

import com.ethpool.monitor.scheduler.StatsResponseScheduler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@CrossOrigin()
@RestController
@RequestMapping("/v1/poolStats")
public class PoolStatsController {

    private static final Logger log = LoggerFactory.getLogger(PoolStatsController.class);

    @Autowired
    EthPoolClient ethPoolClient;

    @Autowired
    StatsResponseScheduler statsResponseScheduler;

    @GetMapping(value = "getPoolStats")
    void getPoolStats() {
        log.info("Request from MM REST API, checking PoolStats");
        statsResponseScheduler.getStatsResponseAndSave();


    }


}
