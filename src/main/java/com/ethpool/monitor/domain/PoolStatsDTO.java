package com.ethpool.monitor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PoolStatsDTO {

    private Long hashRate;
    private Long miners;
    private Long workers;
    private Double blocksPerHour;


}
