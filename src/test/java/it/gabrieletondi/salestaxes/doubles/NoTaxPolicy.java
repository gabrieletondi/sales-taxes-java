package it.gabrieletondi.salestaxes.doubles;

import it.gabrieletondi.salestaxes.PercentageTax;
import it.gabrieletondi.salestaxes.ShelfItem;
import it.gabrieletondi.salestaxes.Tax;
import it.gabrieletondi.salestaxes.TaxPolicy;

public class NoTaxPolicy implements TaxPolicy {
    public Tax forItem(ShelfItem item) {
        return PercentageTax.EXEMPT;
    }
}
