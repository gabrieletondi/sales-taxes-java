package it.gabrieletondi.salestaxes.tax.rounding;

import java.math.BigDecimal;

public class NearestToFiveCentsRounding extends Rounding {
    public BigDecimal round(BigDecimal value) {
        double roundedValue = Math.ceil(value.doubleValue() * 20) / 20;
        return new BigDecimal(roundedValue).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
