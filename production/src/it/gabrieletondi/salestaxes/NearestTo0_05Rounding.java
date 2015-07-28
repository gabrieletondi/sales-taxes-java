package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public class NearestTo0_05Rounding extends Rounding {
    public BigDecimal round(BigDecimal value) {
        double roundedValue = Math.ceil(value.doubleValue() * 20) / 20;
        return new BigDecimal(roundedValue).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
