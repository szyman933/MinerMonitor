package com.ethpool.monitor.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "PRICE")
@Setter
@Getter
public class Price {

    public Price() {
    }

    public Price(String serverTime, Double usdPrice,Double btcPrice, Double euroPrice ) {

        this.usdPrice = usdPrice;
        this.euroPrice = euroPrice;
        this.btcPrice = btcPrice;
        this.serverTime = serverTime;
        this.data = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name = "SERVER_TIME")
    private String serverTime;
    @Column(name = "USD_PRICE")
    private Double usdPrice;
    @Column(name = "BTC_PRICE")
    private Double btcPrice;
    @Column(name = "EURO_PRICE")
    private Double euroPrice;
    @Column(name = "SAVED_AT")
    private Date data;


    Date parseServerTimeToDate(String time){

        LocalDateTime dateTime = LocalDateTime.parse(time);

        return java.sql.Date.valueOf(dateTime.toLocalDate());

    }

    public int getId() {
        return id;
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

    public Date getData() {
        return data;
    }
}
