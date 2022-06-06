package com.ethpool.monitor.mappers;

import com.ethpool.monitor.domain.MinerStatsData;
import com.ethpool.monitor.domain.MinerStatsDataDTO;
import org.springframework.stereotype.Component;
import static com.ethpool.monitor.utilities.Converters.LocalDateTimeToMillis;
import static com.ethpool.monitor.utilities.Converters.convertsUnixTimestampToLocalDateTime;


@Component
public class MinerStatsDataMapper {


    public MinerStatsData mapToMinerStatsData(MinerStatsDataDTO minerStatsDataDTO) {


        return new MinerStatsData(
                convertsUnixTimestampToLocalDateTime(minerStatsDataDTO.getServerTime()),
                convertsUnixTimestampToLocalDateTime(minerStatsDataDTO.getRigLastSeen()),
                minerStatsDataDTO.getReportedHashrate(), minerStatsDataDTO.getCurrentHashrate(),
                minerStatsDataDTO.getValidShares(),
                minerStatsDataDTO.getInvalidShares(),
                minerStatsDataDTO.getStaleShares(),
                minerStatsDataDTO.getActiveWorkers(),
                minerStatsDataDTO.getAverageHashrate(),
                minerStatsDataDTO.getUnpaidBallance(),
                minerStatsDataDTO.getCoinsPerMin(),
                minerStatsDataDTO.getUsdPerMin(),
                minerStatsDataDTO.getBtcPerMin()
        );
    }


    public MinerStatsDataDTO mapToMinerStatsDataDTO(MinerStatsData minerStatsData) {


        return new MinerStatsDataDTO(
                LocalDateTimeToMillis(minerStatsData.getServerTime()),
                LocalDateTimeToMillis(minerStatsData.getRigLastSeen()),
                minerStatsData.getReportedHashrate(),
                minerStatsData.getCurrentHashrate(),
                minerStatsData.getValidShares(),
                minerStatsData.getInvalidShares(),
                minerStatsData.getStaleShares(),
                minerStatsData.getActiveWorkers(),
                minerStatsData.getAverageHashrate(),
                minerStatsData.getUnpaidBallance(),
                minerStatsData.getCoinsPerMin(),
                minerStatsData.getUsdPerMin(),
                minerStatsData.getBtcPerMin()
        );
    }


}
