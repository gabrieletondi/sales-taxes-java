package it.gabrieletondi.salestaxes.doubles;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.tax.Tax;
import it.gabrieletondi.salestaxes.tax.TaxPolicy;

import java.math.BigDecimal;

public class FixedTaxAmountPolicy implements TaxPolicy {

    private final BigDecimal taxAmount;

    public FixedTaxAmountPolicy(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Tax forItem(CartItem item) {
        return new FixedTaxAmount(taxAmount);
    }
}
