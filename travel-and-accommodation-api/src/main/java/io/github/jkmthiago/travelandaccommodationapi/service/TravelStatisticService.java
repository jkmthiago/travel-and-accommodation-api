package io.github.jkmthiago.travelandaccommodationapi.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.jkmthiago.travelandaccommodationapi.model.TravelStatistic;
import io.github.jkmthiago.travelandaccommodationapi.model.Travel;

@Service

public class TravelStatisticService {
    public TravelStatistic create(List<Travel> travels) {

        var statistics = new TravelStatistic();
        
        statistics.setCount(travels.stream().count());
		statistics.setAvg(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getValorDaPassagem().doubleValue()).average().orElse(0.0)).setScale(2, RoundingMode.HALF_UP));
		statistics.setMin(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getValorDaPassagem().doubleValue()).min().orElse(0.0)).setScale(2, RoundingMode.HALF_UP));
		statistics.setMax(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getValorDaPassagem().doubleValue()).max().orElse(0.0)).setScale(2, RoundingMode.HALF_UP));
		statistics.setSum(BigDecimal.valueOf(travels.stream().mapToDouble(t -> t.getValorDaPassagem().doubleValue()).sum()).setScale(2, RoundingMode.HALF_UP));

        return statistics;
    }
}
