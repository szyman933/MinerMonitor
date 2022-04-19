package com.ethpool.monitor.repository;

import com.ethpool.monitor.domain.Alarm;
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

    @Query
    List<Alarm> pendingAlarms();


    @Query
    List<Alarm> alarmExist(@Param("SERVERTIME") LocalDateTime serverTime, @Param("ALARMNAME") String alarmName);


}
