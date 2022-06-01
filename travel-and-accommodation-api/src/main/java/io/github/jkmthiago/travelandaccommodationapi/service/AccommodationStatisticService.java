package io.github.jkmthiago.travelandaccommodationapi.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.jkmthiago.travelandaccommodationapi.model.AccommodationStatistic;
import io.github.jkmthiago.travelandaccommodationapi.model.Accommodation;

@Service

public class AccommodationStatisticService {
    public AccommodationStatistic create(List<Accommodation> accommodations) {

        var statistics = new AccommodationStatistic();
        
        statistics.setCount(accommodations.stream().count());
		statistics.setAvg(BigDecimal.valueOf(accommodations.stream().mapToDouble(t -> t.getValorDasDiarias().doubleValue()).average().orElse(0.0)).setScale(2, RoundingMode.HALF_UP));
		statistics.setMin(BigDecimal.valueOf(accommodations.stream().mapToDouble(t -> t.getValorDasDiarias().doubleValue()).min().orElse(0.0)).setScale(2, RoundingMode.HALF_UP));
		statistics.setMax(BigDecimal.valueOf(accommodations.stream().mapToDouble(t -> t.getValorDasDiarias().doubleValue()).max().orElse(0.0)).setScale(2, RoundingMode.HALF_UP));
		statistics.setSum(BigDecimal.valueOf(accommodations.stream().mapToDouble(t -> t.getValorDasDiarias().doubleValue()).sum()).setScale(2, RoundingMode.HALF_UP));

        return statistics;
    }

}
