package it.gabrieletondi.salestaxes.tax;

import it.gabrieletondi.salestaxes.catalog.CartItem;
import it.gabrieletondi.salestaxes.catalog.Category;

import java.util.Arrays;
import java.util.List;

public class ByCategoryAndImportTaxPolicy implements TaxPolicy {
    private final Tax defaultTax;
    private final Tax importedTax;
    private final List<Category> exemptCategories;

    public ByCategoryAndImportTaxPolicy(Tax defaultTax, Tax importedTax, Category... exemptCategories) {
        this.defaultTax = defaultTax;
        this.importedTax = importedTax;
        this.exemptCategories = Arrays.asList(exemptCategories);
    }

    public Tax forItem(CartItem item) {
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
