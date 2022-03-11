package com.ethpool.monitor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDTO {

    @JsonProperty("time")
    private Date serverTime;
    @JsonProperty("usd")
    private Double usdPrice;
    @JsonProperty("btc")
    private Double btcPrice;
    @JsonProperty("eur")
    private Double euroPrice;

    public Date getServerTime() {
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
