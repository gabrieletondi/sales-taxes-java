package it.gabrieletondi.salestaxes;

import java.math.BigDecimal;

public class Tax {

    public static Tax EXEMPT = Tax.withRate(0, Rounding.NONE);

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

        if (rate != tax.rate)
            return false;

        return rounding.getClass().equals(tax.rounding.getClass());
    }

    @Override
    public int hashCode() {
        return rate;
    }

    public BigDecimal dutyAmount(BigDecimal netPrice) {
        BigDecimal rawTaxAmount = netPrice.divide(new BigDecimal("100")).multiply(new BigDecimal(rate));
        return rounding.round(rawTaxAmount);
    }

    @Override
    public String toString() {
        return "Tax{rate=" + rate + ", rounding=" + rounding + "}";
    }
}
