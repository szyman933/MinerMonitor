package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
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
}
