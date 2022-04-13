package com.ethpool.monitor.domain;

import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@org.hibernate.annotations.NamedQuery(name = "Alarm.pendingAlarms", query = "FROM Alarm WHERE alarmStop is null")

@Entity
@Table(name = "ALARM")
public class Alarm {

    public Alarm() {
    }

    public Alarm(LocalDateTime alarmRegistration, LocalDateTime alarmDuration, LocalDateTime alarmStop, LocalDateTime serverTime, String alarmName, int alarmLevel) {
        this.alarmRegistration = alarmRegistration;
        this.alarmDuration = alarmDuration;
        this.alarmStop = alarmStop;
        this.serverTime = serverTime;
        this.alarmName = alarmName;
        this.alarmLevel = alarmLevel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID",unique = true)
    private int id;

    @Column(name = "ALARM_REG_TIME")
    private LocalDateTime alarmRegistration;

    @Column(name = "ALARM_DURATION")
    private LocalDateTime alarmDuration;

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

    public LocalDateTime getAlarmDuration() {
        return alarmDuration;
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
}
