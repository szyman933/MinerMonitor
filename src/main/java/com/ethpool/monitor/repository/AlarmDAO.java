package com.ethpool.monitor.repository;

import com.ethpool.monitor.domain.Alarm;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public interface AlarmDAO extends CrudRepository<Alarm, Integer> {


    List<Alarm> findById(int id);

    Alarm save(Alarm alarm);

    List<Alarm> findAll();

    void  updateByAlarm(Alarm alarm);

    @Query
    List<Alarm> pendingAlarms();


    @Query
    List<Alarm> alarmExist(@Param("SERVERTIME") LocalDateTime serverTime, @Param("ALARMNAME") String alarmName);

//TODO Test hibernate update and eplicite query update
    @Modifying
    @Query("update Alarm a set a.alarmDurationSeconds = :duration where a.id = :id")
    void updatePhone(@Param(value = "id") int id, @Param(value = "duration") Long duration);


}
