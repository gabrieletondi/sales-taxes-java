package it.gabrieletondi.salestaxes;

public interface TaxPolicy {
    Tax forItem(ShelfItem item);
}
