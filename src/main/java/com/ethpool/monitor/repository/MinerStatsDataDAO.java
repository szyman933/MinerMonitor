package com.ethpool.monitor.repository;

import com.ethpool.monitor.domain.MinerStatsData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
@Repository
public interface MinerStatsDataDAO extends CrudRepository<MinerStatsData, Integer> {

    List<MinerStatsData> findById(int id);

    MinerStatsData save(MinerStatsData minerStatsData);

    boolean existsByServerTime(LocalDateTime localDateTime);

}
