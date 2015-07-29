package it.gabrieletondi.salestaxes.doubles;

import it.gabrieletondi.salestaxes.Rounding;

import java.math.BigDecimal;

public class FixedValueRounding extends Rounding {
    private final int fixedValue;

    public FixedValueRounding(int fixedValue) {
        super();
        this.fixedValue = fixedValue;
    }

    @Override
    public BigDecimal round(BigDecimal value) {
        return new BigDecimal(fixedValue);
    }
}
