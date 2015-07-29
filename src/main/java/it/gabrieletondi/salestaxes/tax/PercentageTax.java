package it.gabrieletondi.salestaxes.tax;

import it.gabrieletondi.salestaxes.tax.rounding.Rounding;

import java.math.BigDecimal;

public class PercentageTax implements Tax {

    public static PercentageTax EXEMPT = PercentageTax.withRate(0, Rounding.NONE);

    private final int rate;
    private final Rounding rounding;

    private PercentageTax(int rate, Rounding rounding) {
        this.rate = rate;
        this.rounding = rounding;
    }

    public static PercentageTax withRate(int rate, Rounding rounding) {
        return new PercentageTax(rate, rounding);
    }

    public BigDecimal dutyAmount(BigDecimal netPrice) {
        BigDecimal rawTaxAmount = netPrice.divide(new BigDecimal("100")).multiply(new BigDecimal(rate));
        return rounding.round(rawTaxAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PercentageTax tax = (PercentageTax) o;

        if (rate != tax.rate)
            return false;

        return rounding.getClass().equals(tax.rounding.getClass());
    }

    @Override
    public int hashCode() {
        return rate;
    }

    @Override
    public String toString() {
        return "Tax{rate=" + rate + ", rounding=" + rounding + "}";
    }
}
