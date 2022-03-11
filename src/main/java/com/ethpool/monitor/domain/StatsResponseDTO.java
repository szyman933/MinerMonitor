package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsResponseDTO {


    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private DataDTO dataDTO;


    public String getStatus() {
        return status;
    }

    public DataDTO getDataDTO() {
        return dataDTO;
    }
}
