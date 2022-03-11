package com.ethpool.monitor.repository;

import com.ethpool.monitor.domain.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PriceDAOTest {

    @Autowired
    PriceDAO priceDAO;


    @Test
    void shouldSavePriceStatistics() {

        //given
        Price price = new Price("2022-03-10T18:42:34.000Z", 2598.419921875, 0.0663655028, 2364.2600097656);

        priceDAO.save(price);

        //Then
        int id = price.getId();
        List<Price> readPrice = priceDAO.findById(id);
        assertEquals(1, readPrice.size());

        //CleanUp
        priceDAO.deleteById(id);

    }

}