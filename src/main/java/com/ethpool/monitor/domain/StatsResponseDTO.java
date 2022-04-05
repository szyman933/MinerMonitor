package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



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


    public StatsResponseDTO() {
    }

    public StatsResponseDTO(String status, DataDTO dataDTO) {

        this.status = status;
        this.dataDTO = dataDTO;
    }
}
