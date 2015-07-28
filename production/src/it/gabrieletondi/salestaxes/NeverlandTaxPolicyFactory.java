package it.gabrieletondi.salestaxes;

import java.util.HashMap;
import java.util.Map;

public class NeverlandTaxPolicyFactory {
    public static InMemoryWithDefaultTaxPolicy build() {
        Tax defaultTax = Tax.withRate(10, new NearestTo0_05Rounding());
        Map<String, Tax> specificRules = new HashMap<String, Tax>();
        specificRules.put("chocolate bar", Tax.EXEMPT);
        specificRules.put("box of chocolates", Tax.EXEMPT);
        specificRules.put("book", Tax.EXEMPT);
        specificRules.put("headache pills", Tax.EXEMPT);

        return new InMemoryWithDefaultTaxPolicy(defaultTax, specificRules);
    }
}
