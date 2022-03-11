package com.ethpool.monitor.domain;

public class StatsResponse {

    private String status;
    private Data data;

    public StatsResponse() {
    }

    public StatsResponse(String status, Data data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
