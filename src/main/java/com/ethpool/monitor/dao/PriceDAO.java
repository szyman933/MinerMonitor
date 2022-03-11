package com.ethpool.monitor.dao;

import com.ethpool.monitor.domain.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface PriceDAO extends CrudRepository<Price, Integer> {

    List<Price> findById(int id);

}
