package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public class Tax {

    private final int rate;

    private Tax(int rate) {
        this.rate = rate;
    }

    public static Tax withRate(int rate) {
        return new Tax(rate);
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
        return netPrice.divide(new BigDecimal("100")).multiply(new BigDecimal(rate));
    }
}
