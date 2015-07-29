package it.gabrieletondi.salestaxes.doubles;

import it.gabrieletondi.salestaxes.catalog.ShelfItem;
import it.gabrieletondi.salestaxes.tax.PercentageTax;
import it.gabrieletondi.salestaxes.tax.Tax;
import it.gabrieletondi.salestaxes.tax.TaxPolicy;

public class NoTaxPolicy implements TaxPolicy {
    public Tax forItem(ShelfItem item) {
        return PercentageTax.EXEMPT;
    }
}
