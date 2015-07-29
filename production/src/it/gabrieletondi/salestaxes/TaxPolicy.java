package it.gabrieletondi.salestaxes;

import java.util.Map;

public class TaxPolicy {
    private final PercentageTax defaultTax;
    private final Map<String, PercentageTax> specificRules;
    private final PercentageTax importedTax;

    public TaxPolicy(PercentageTax defaultTax, Map<String, PercentageTax> specificRules, PercentageTax importedTax) {
        this.defaultTax = defaultTax;
        this.specificRules = specificRules;
        this.importedTax = importedTax;
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

        return new CompositeTax(tax, importedTax);
    }
}
