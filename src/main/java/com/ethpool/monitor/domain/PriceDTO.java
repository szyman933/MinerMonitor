package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDTO {

    @JsonProperty("time")
    private String serverTime;
    @JsonProperty("usd")
    private Double usdPrice;
    @JsonProperty("btc")
    private Double btcPrice;
    @JsonProperty("eur")
    private Double euroPrice;

    public PriceDTO(String serverTime, Double usdPrice, Double btcPrice, Double euroPrice) {
        this.serverTime = serverTime;
        this.usdPrice = usdPrice;
        this.btcPrice = btcPrice;
        this.euroPrice = euroPrice;
    }

    public PriceDTO() {
    }

    public String getServerTime() {
        return serverTime;
    }

    public Double getUsdPrice() {
        return usdPrice;
    }

    public Double getBtcPrice() {
        return btcPrice;
    }

    public Double getEuroPrice() {
        return euroPrice;
    }
}
