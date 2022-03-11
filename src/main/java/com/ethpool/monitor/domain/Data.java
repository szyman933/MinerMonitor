package com.ethpool.monitor.domain;


public class Data {

    private PoolStats poolStats;

    private  Price price;

    public Data() {
    }

    public Data(PoolStats poolStats, Price price) {
        this.poolStats = poolStats;
        this.price = price;
    }

    public PoolStats getPoolStats() {
        return poolStats;
    }

    public Price getPrice() {
        return price;
    }

    public void setPoolStats(PoolStats poolStats) {
        this.poolStats = poolStats;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
