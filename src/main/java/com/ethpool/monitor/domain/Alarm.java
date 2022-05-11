package com.ethpool.monitor.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = "Alarm.pendingAlarms", query = "FROM Alarm WHERE alarmStop is null")

@NamedQuery(name = "Alarm.alarmExist", query = "FROM Alarm WHERE serverTime = :SERVERTIME and alarmName = :ALARMNAME and alarmStop is null")

@Entity
@Setter
@Getter
@Table(name = "ALARM")
public class Alarm {

    public Alarm() {
    }

    public Alarm(LocalDateTime alarmRegistration, Long alarmDuration, LocalDateTime alarmStop, LocalDateTime serverTime, String alarmName, int alarmLevel) {
        this.alarmRegistration = alarmRegistration;
        this.alarmDurationSeconds = alarmDuration;
        this.alarmStop = alarmStop;
        this.serverTime = serverTime;
        this.alarmName = alarmName;
        this.alarmLevel = alarmLevel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    private int id;

    @Column(name = "ALARM_REG_TIME")
    private LocalDateTime alarmRegistration;

    @Column(name = "ALARM_DURATION_SECONDS")
    private Long alarmDurationSeconds;

    @Column(name = "ALARM_END_TIME")
    private LocalDateTime alarmStop;

    @Column(name = "SERVER_TIME")
    private LocalDateTime serverTime;

    @Column(name = "ALARM_NAME")
    private String alarmName;

    @Column(name = "ALARM_LEVEL")
    private int alarmLevel;


    public int getId() {
        return id;
    }

    public LocalDateTime getAlarmRegistration() {
        return alarmRegistration;
    }

    public Long getAlarmDurationSeconds() {
        return alarmDurationSeconds;
    }

    public LocalDateTime getAlarmStop() {
        return alarmStop;
    }

    public LocalDateTime getServerTime() {
        return serverTime;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }


    public void setAlarmDurationSeconds(Long alarmDurationSeconds) {
        this.alarmDurationSeconds = alarmDurationSeconds;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "alarmRegistration=" + alarmRegistration +
                ", alarmDurationSeconds=" + alarmDurationSeconds +
                ", alarmStop=" + alarmStop +
                ", serverTime=" + serverTime +
                ", alarmName='" + alarmName + '\'' +
                ", alarmLevel=" + alarmLevel +
                ", ID=" + id +
                '}';
    }
}
