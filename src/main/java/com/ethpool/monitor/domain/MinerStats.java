package com.ethpool.monitor.domain;

public class MinerStats {

    private String status;
    private MinerStatsData minerStatsData;


    public MinerStats() {
    }

    public MinerStats(String status, MinerStatsData minerStatsData) {
        this.status = status;
        this.minerStatsData = minerStatsData;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MinerStatsData getMinerStatsData() {
        return minerStatsData;
    }

    public void setMinerStatsData(MinerStatsData minerStatsData) {
        this.minerStatsData = minerStatsData;
    }
}
