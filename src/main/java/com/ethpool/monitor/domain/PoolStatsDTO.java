package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PoolStatsDTO {

    @JsonProperty("hashRate")
    private Long hashRate;
    @JsonProperty("miners")
    private Long miners;
    @JsonProperty("workers")
    private Long workers;
    @JsonProperty("blocksPerHour")
    private Double blocksPerHour;

    public PoolStatsDTO() {
    }

    public PoolStatsDTO(Long hashRate, Long miners, Long workers, Double blocksPerHour) {
        this.hashRate = hashRate;
        this.miners = miners;
        this.workers = workers;
        this.blocksPerHour = blocksPerHour;
    }

    public Long getHashRate() {
        return hashRate;
    }

    public Long getMiners() {
        return miners;
    }

    public Long getWorkers() {
        return workers;
    }

    public Double getBlocksPerHour() {
        return blocksPerHour;
    }
}
