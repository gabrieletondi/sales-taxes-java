package it.gabrieletondi.salestaxes;

import java.util.HashMap;
import java.util.Map;

public class NeverlandTaxPolicyFactory {
    public static InMemoryWithDefaultTaxPolicy build() {
        PercentageTax defaultTax = PercentageTax.withRate(10, new NearestToFiveCentsRounding());
        Map<String, PercentageTax> specificRules = new HashMap<String, PercentageTax>();
        specificRules.put("chocolate bar", PercentageTax.EXEMPT);
        specificRules.put("box of chocolates", PercentageTax.EXEMPT);
        specificRules.put("book", PercentageTax.EXEMPT);
        specificRules.put("packet of headache pills", PercentageTax.EXEMPT);

        return new InMemoryWithDefaultTaxPolicy(defaultTax, specificRules);
    }
}
