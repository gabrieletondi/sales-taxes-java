package it.gabrieletondi.salestaxes.tax;

import it.gabrieletondi.salestaxes.catalog.CartItem;

public interface TaxPolicy {
    Tax forItem(CartItem item);
}
