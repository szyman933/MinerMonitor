package com.ethpool.monitor.repository;

import com.ethpool.monitor.domain.Alarm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlarmDAOTest {

    @Autowired
    AlarmDAO alarmDAO;


    @Test
    void saveNewAlarmTest() {

        LocalDateTime alarmStart = LocalDateTime.of(2022, 4, 12, 13, 10, 0);
        LocalDateTime alarmEnd = LocalDateTime.of(2022, 4, 12, 13, 30, 0);
        LocalDateTime serverTime = LocalDateTime.of(2022, 4, 12, 13, 20, 0);
        Long duration = Duration.between(alarmStart.toLocalTime(), alarmEnd.toLocalTime()).getSeconds();

        Alarm alarm = new Alarm(alarmStart, duration, alarmEnd, serverTime, "Testowy", 1);

        alarmDAO.save(alarm);

        int id = alarm.getId();

        List<Alarm> fetchedAlarm = alarmDAO.findById(id);

        assertEquals(1, fetchedAlarm.size());

        alarmDAO.deleteById(id);

    }

    @Test
    void checkIfAlarmExistTest() {

        LocalDateTime alarmStart = LocalDateTime.of(2022, 4, 12, 13, 10, 0);
        LocalDateTime serverTime = LocalDateTime.of(2022, 4, 12, 13, 20, 0);

        Alarm alarm = new Alarm(alarmStart, null, null, serverTime, "Testowy", 1);

        alarmDAO.save(alarm);

        int id = alarm.getId();

        List<Alarm> fetchedAlarm = alarmDAO.alarmExist(serverTime, "Testowy");

        assertEquals(1, fetchedAlarm.size());

        alarmDAO.deleteById(id);
    }

}