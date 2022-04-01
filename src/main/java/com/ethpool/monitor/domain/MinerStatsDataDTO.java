package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public class MinerStatsDataDTO {


    @JsonProperty("time")
    private Long serverTime;
    @JsonProperty("lastSeen")
    private Long rigLastSeen;
    @JsonProperty("reportedHashrate")
    private Long reportedHashrate;
    @JsonProperty("currentHashrate")
    private Long currentHashrate;
    @JsonProperty("validShares")
    private Integer validShares;
    @JsonProperty("invalidShares")
    private Integer invalidShares;
    @JsonProperty("staleShares")
    private Integer staleShares;
    @JsonProperty("activeWorkers")
    private Integer activeWorkers;
    @JsonProperty("averageHashrate")
    private Double averageHashrate;
    @JsonProperty("unpaid")
    private Long unpaidBallance;
    @JsonProperty("coinsPerMin")
    private Double coinsPerMin;
    @JsonProperty("usdPerMin")
    private Double usdPerMin;
    @JsonProperty("btcPerMin")
    private Double btcPerMin;


    public MinerStatsDataDTO() {
    }

    public MinerStatsDataDTO(Long serverTime, Long rigLastSeen, Long reportedHashrate, Long currentHashrate, Integer validShares, Integer invalidShares, Integer staleShares, Integer activeWorkers, Double averageHashrate, Long unpaidBallance, Double coinsPerMin, Double usdPerMin, Double btcPerMin) {
        this.serverTime = serverTime;
        this.rigLastSeen = rigLastSeen;
        this.reportedHashrate = reportedHashrate;
        this.currentHashrate = currentHashrate;
        this.validShares = validShares;
        this.invalidShares = invalidShares;
        this.staleShares = staleShares;
        this.activeWorkers = activeWorkers;
        this.averageHashrate = averageHashrate;
        this.unpaidBallance = unpaidBallance;
        this.coinsPerMin = coinsPerMin;
        this.usdPerMin = usdPerMin;
        this.btcPerMin = btcPerMin;
    }

    public Long getServerTime() {
        return serverTime;
    }

    public Long getRigLastSeen() {
        return rigLastSeen;
    }

    public Long getReportedHashrate() {
        return reportedHashrate;
    }

    public Long getCurrentHashrate() {
        return currentHashrate;
    }

    public Integer getValidShares() {
        return validShares;
    }

    public Integer getInvalidShares() {
        return invalidShares;
    }

    public Integer getStaleShares() {
        return staleShares;
    }

    public Integer getActiveWorkers() {
        return activeWorkers;
    }

    public Double getAverageHashrate() {
        return averageHashrate;
    }

    public Long getUnpaidBallance() {
        return unpaidBallance;
    }

    public Double getCoinsPerMin() {
        return coinsPerMin;
    }

    public Double getUsdPerMin() {
        return usdPerMin;
    }

    public Double getBtcPerMin() {
        return btcPerMin;
    }
}
