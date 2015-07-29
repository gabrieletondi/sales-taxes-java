package it.gabrieletondi.salestaxes;

import java.util.HashMap;
import java.util.Map;

public class NeverlandTaxPolicyFactory {
    private static final PercentageTax IMPORTED_TAX = PercentageTax.withRate(5, new NearestToFiveCentsRounding());
    private static final PercentageTax DEFAULT_TAX = PercentageTax.withRate(10, new NearestToFiveCentsRounding());

    public static TaxPolicy build() {
        Map<String, PercentageTax> specificRules = new HashMap<String, PercentageTax>();
        specificRules.put("chocolate bar", PercentageTax.EXEMPT);
        specificRules.put("box of chocolates", PercentageTax.EXEMPT);
        specificRules.put("book", PercentageTax.EXEMPT);
        specificRules.put("packet of headache pills", PercentageTax.EXEMPT);

        return new TaxPolicy(DEFAULT_TAX, specificRules, IMPORTED_TAX);
    }
}
