package it.gabrieletondi.salestaxes.tax;

import it.gabrieletondi.salestaxes.tax.rounding.Rounding;

import java.math.BigDecimal;

public class NoRounding extends Rounding {
    public BigDecimal round(BigDecimal value) {
        return value;
    }
}
