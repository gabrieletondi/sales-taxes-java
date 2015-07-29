package it.gabrieletondi.salestaxes.tax;

import it.gabrieletondi.salestaxes.catalog.ShelfItem;

public interface TaxPolicy {
    Tax forItem(ShelfItem item);
}
