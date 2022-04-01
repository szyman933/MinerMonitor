package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
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
}
