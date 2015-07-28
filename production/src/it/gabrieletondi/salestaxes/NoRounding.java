package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public class NoRounding extends Rounding {
    public BigDecimal round(BigDecimal value) {
        return value;
    }
}
