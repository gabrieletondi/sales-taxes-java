package it.gabrieletondi.salestaxes.tax.rounding;

import it.gabrieletondi.salestaxes.tax.NoRounding;

import java.math.BigDecimal;

public abstract class Rounding {
    public static Rounding NONE = new NoRounding();
    public abstract BigDecimal round(BigDecimal value);
}
