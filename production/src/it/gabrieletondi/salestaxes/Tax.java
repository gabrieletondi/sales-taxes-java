package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public class Tax {

    private final int rate;
    private final Rounding rounding;

    private Tax(int rate, Rounding rounding) {
        this.rate = rate;
        this.rounding = rounding;
    }

    public static Tax withRate(int rate, Rounding rounding) {
        return new Tax(rate, rounding);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tax tax = (Tax) o;

        return rate == tax.rate;
    }

    @Override
    public int hashCode() {
        return rate;
    }

    public BigDecimal dutyAmount(BigDecimal netPrice) {
        BigDecimal rawTaxAmount = netPrice.divide(new BigDecimal("100")).multiply(new BigDecimal(rate));
        return rounding.round(rawTaxAmount);
    }
}
