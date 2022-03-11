package com.ethpool.monitor.mappers;

import com.ethpool.monitor.domain.Price;
import com.ethpool.monitor.domain.PriceDTO;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {


    public Price mapToPrice(final PriceDTO priceDTO) {

        return new Price(priceDTO.getServerTime(),
                priceDTO.getUsdPrice(),
                priceDTO.getBtcPrice(),
                priceDTO.getEuroPrice());

    }


    public PriceDTO mapToPriceDTO (final Price price){

        return new PriceDTO(price.getServerTime(),
                price.getUsdPrice(),
                price.getBtcPrice(),
                price.getEuroPrice());

    }



}
