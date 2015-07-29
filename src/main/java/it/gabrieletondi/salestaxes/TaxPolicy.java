package it.gabrieletondi.salestaxes;

import java.util.Arrays;
import java.util.List;

public class TaxPolicy {
    private final Tax defaultTax;
    private final Tax importedTax;
    private final List<Category> exemptCategories;

    public TaxPolicy(Tax defaultTax, Tax importedTax, Category...exemptCategories) {
        this.defaultTax = defaultTax;
        this.importedTax = importedTax;
        this.exemptCategories = Arrays.asList(exemptCategories);
    }

    public Tax forItem(ShelfItem item) {
        Tax tax = forCategory(item.getCategory());

        if (!item.isImported())
            return tax;

        return new CompositeTax(tax, importedTax);
    }

    private Tax forCategory(Category category) {
        if (exemptCategories.contains(category))
            return PercentageTax.EXEMPT;

        return defaultTax;
    }
}
