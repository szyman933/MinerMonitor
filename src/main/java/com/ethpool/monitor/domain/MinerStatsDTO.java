package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MinerStatsDTO {

    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private MinerStatsDataDTO minerStatsDataDTO;


    public String getStatus() {
        return status;
    }

    public MinerStatsDataDTO getMinerStatsDataDTO() {
        return minerStatsDataDTO;
    }


    public MinerStatsDTO(String status, MinerStatsDataDTO minerStatsDataDTO) {
        this.status = status;
        this.minerStatsDataDTO = minerStatsDataDTO;
    }

    public MinerStatsDTO() {
    }
}
