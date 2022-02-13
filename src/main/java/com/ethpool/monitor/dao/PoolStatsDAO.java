package com.ethpool.monitor.dao;

import com.ethpool.monitor.domain.PoolStats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PoolStatsDAO extends CrudRepository<PoolStats,Integer> {

    List<PoolStats> findById(int id);



}
