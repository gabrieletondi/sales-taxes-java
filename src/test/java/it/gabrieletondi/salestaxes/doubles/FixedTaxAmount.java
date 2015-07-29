package it.gabrieletondi.salestaxes.doubles;

import it.gabrieletondi.salestaxes.tax.Tax;

import java.math.BigDecimal;

public class FixedTaxAmount implements Tax {
    private final BigDecimal taxAmount;

    public FixedTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal dutyAmount(BigDecimal netPrice) {
        return taxAmount;
    }
}
