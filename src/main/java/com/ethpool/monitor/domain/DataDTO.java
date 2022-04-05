package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDTO {


    @JsonProperty("poolStats")
    PoolStatsDTO poolStatsDTO;

    @JsonProperty("price")
    PriceDTO priceDTO;

    public PoolStatsDTO getPoolStatsDTO() {
        return poolStatsDTO;
    }

    public PriceDTO getPriceDTO() {
        return priceDTO;
    }


    public DataDTO() {
    }

    public DataDTO(PoolStatsDTO poolStatsDTO, PriceDTO priceDTO) {
        this.poolStatsDTO = poolStatsDTO;
        this.priceDTO = priceDTO;
    }
}
