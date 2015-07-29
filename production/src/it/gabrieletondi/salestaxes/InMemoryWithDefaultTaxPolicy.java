package it.gabrieletondi.salestaxes;

import java.util.Map;

public class InMemoryWithDefaultTaxPolicy {
    private final PercentageTax defaultTax;
    private final Map<String, PercentageTax> specificRules;

    public InMemoryWithDefaultTaxPolicy(PercentageTax defaultTax, Map<String, PercentageTax> specificRules) {
        this.defaultTax = defaultTax;
        this.specificRules = specificRules;
    }

    private PercentageTax forItemName(String itemName) {
        if (specificRules.containsKey(itemName))
            return specificRules.get(itemName);

        return defaultTax;
    }

    public Tax forItem(SaleItem item) {
        Tax tax = forItemName(item.getProductName());

        if (!item.isImported())
            return tax;

        return new CompositeTax(tax, PercentageTax.withRate(5, new NearestToFiveCentsRounding()));
    }
}
