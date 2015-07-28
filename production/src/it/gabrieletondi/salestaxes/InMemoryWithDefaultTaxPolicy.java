package it.gabrieletondi.salestaxes;

import java.util.Map;

public class InMemoryWithDefaultTaxPolicy {
    private final Tax defaultTax;
    private final Map<String, Tax> specificRules;

    public InMemoryWithDefaultTaxPolicy(Tax defaultTax, Map<String, Tax> specificRules) {
        this.defaultTax = defaultTax;
        this.specificRules = specificRules;
    }

    public Tax forItemName(String itemName) {
        if (specificRules.containsKey(itemName))
            return specificRules.get(itemName);

        return defaultTax;
    }
}
