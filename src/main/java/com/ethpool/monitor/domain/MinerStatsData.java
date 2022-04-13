package com.ethpool.monitor.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "MINER_STATS_DATA")
@Setter
@Getter
public class MinerStatsData {

        public MinerStatsData() {
        }

        public MinerStatsData(LocalDateTime serverTime, LocalDateTime rigLastSeen, Long reportedHashrate, Long currentHashrate, Integer validShares, Integer invalidShares, Integer staleShares, Integer activeWorkers, Double averageHashrate, Long unpaidBallance, Double coinsPerMin, Double usdPerMin, Double btcPerMin) {
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

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NonNull
        @Column(name = "ID", unique = true)
        private int id;
        @Column(name = "SERVER_TIME")
        private LocalDateTime serverTime;
        @Column(name = "RIG_LAST_SEEN")
        private LocalDateTime rigLastSeen;
        @Column(name ="REPORTED_HASHRATE")
        private Long reportedHashrate;
        @Column(name ="CURRENT_HASHRATE")
        private Long currentHashrate;
        @Column(name ="VALID_SHARES")
        private Integer validShares;
        @Column(name ="INVALID_SHARES")
        private Integer invalidShares;
        @Column(name ="STALE_SHARES")
        private Integer staleShares;
        @Column(name ="ACTIVE_WORKERS")
        private Integer activeWorkers;
        @Column(name ="AVARAGE_HASHRATE")
        private Double averageHashrate;
        @Column(name ="UNPAID_BALLANCE")
        private Long unpaidBallance;
        @Column(name ="COINS_PER_MINUTE")
        private Double coinsPerMin;
        @Column(name ="USD_PER_MINUTE")
        private Double usdPerMin;
        @Column(name ="BTC_PER_MINUTE")
        private Double btcPerMin;

        public int getId() {
                return id;
        }

        public LocalDateTime getServerTime() {
                return serverTime;
        }

        public LocalDateTime getRigLastSeen() {
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
