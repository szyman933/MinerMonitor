package com.ethpool.monitor.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "POOL_STATS")
@Getter
@Setter
public class PoolStats {

    public PoolStats() {}


    public PoolStats(Long hashRate, Long miners, Long workers, Double blocksPerHour) {
        this.hashRate = hashRate;
        this.miners = miners;
        this.workers = workers;
        this.blocksPerHour = blocksPerHour;
        this.data = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    private int id;

    @Column(name = "HASH_RATE")
    private Long hashRate;

    @Column(name = "MINERS")
    private Long miners;

    @Column(name = "WORKERS")
    private Long workers;

    @Column(name = "BLOCK_PER_HOUR")
    private Double blocksPerHour;

    @Column(name = "SAVED_AT")
    private Date data;


    public int getId() {
        return id;
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

    public Date getData() {
        return data;
    }
}
